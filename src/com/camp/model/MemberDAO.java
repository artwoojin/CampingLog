package com.camp.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {

	// 회원가입
	public boolean addMember(MemberVO vo){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		// memberImage가 null이거나 공백일 경우 디폴트 이미지 설정
		if (vo.getMemberImage() == null || vo.getMemberImage().isEmpty()) {
			vo.setMemberImage("defaultMemberImage.png");
		}

		try{
			conn.insert("memberMapper.addMember", vo);
			conn.commit();
			result = true;
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return result;
	}



	public List<MemberVO> getMembers(){
		List<MemberVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("memberMapper.getMembers");
		conn.close();
		return list;
	}

	// 내 정보 수정 전 비밀번호 확인
	public boolean pwCheck(MemberVO vo) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try {
			int count = session.selectOne("memberMapper.pwCheck", vo);
			result = (count == 1);
		} finally {
			session.close();
		}
		return result;
	}

	// 내 정보 수정
	public boolean updateProfile(MemberVO vo){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try {
			int updated = session.update("memberMapper.updateProfile", vo);
			if (updated == 1){
				session.commit();
				result = true;
			}
		} catch(Exception e){
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 비밀번호 수정
	public boolean updatePw(String memberId, String pw, String newPw){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;
		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("pw", pw);
		map.put("newPw", newPw);
		try{
			int i = session.update("memberMapper.updatePw", map);
			if (i == 1){
				session.commit();
				result = true;
			}
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	// 프로필 사진 조회
	public String getMemberImage(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		String imageName = null;

		try {
			imageName = session.selectOne("memberMapper.getMemberImage", memberId);
		} finally {
			session.close();
		}
		return imageName;
	}

	// 프로필 사진 등록
	public boolean setMemberImage(String memberId, String memberImage){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberImage", memberImage);

		try{
			int updated = session.update("memberMapper.setMemberImage", map);
			if (updated == 1){
				session.commit();
				result = true;
			}
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	// 프로필 사진 수정 및 기존 이미지 파일 삭제
	public boolean updateMemberImage(String memberId, String newImageFileName, String uploadDirPath) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;
		
		try {
			// 1. 기존 이미지 조회
			String oldImage = session.selectOne("memberMapper.getMemberImage", memberId);

			// 2. DB 변경(새 이미지로)
			HashMap<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("memberImage", newImageFileName);

			int updated = session.update("memberMapper.setMemberImage", map);

			// 3. 성공하면 커밋 + 기존 이미지 삭제
			if (updated == 1) {
				session.commit();
				result = true;

				if (oldImage != null && !oldImage.equals("defaultMemberImage.png")) {
					deleteImageFile(oldImage, uploadDirPath);
				}
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}


	// 프로필 사진 삭제 + 디폴트 이미지 설정
	public boolean deleteAndSetDefaultImage(MemberVO vo, String uploadDirPath) {
		String memberId = vo.getMemberId();

		// 1. 기존 이미지 파일명 추출(VO)
		String oldImage = vo.getMemberImage();

		// 2. DB 업데이트: 디폴트 이미지로 변경
		boolean updated = setDefaultImage(memberId);

		// 3. 기존 이미지 파일 삭제
		if (updated && oldImage != null && !oldImage.equals("defaultMemberImage.png")) {
			deleteImageFile(oldImage, uploadDirPath);

			// 4. memberImage 업데이트(VO)
			vo.setMemberImage("defaultMemberImage.png");
		}

		return updated;
	}

	// 멤버이미지 삭제 내부 메서드1: DB에서 memberImage를 디폴트 이미지로 설정
	private boolean setDefaultImage(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try {
			int updated = session.update("memberMapper.setDefaultImage", memberId);
			if (updated == 1) {
				session.commit();
				result = true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 멤버이미지 삭제 내부 메서드2: 서버에 저장된 기존 이미지 파일 삭제
	private void deleteImageFile(String imageFileName, String uploadDirPath) {
		if (imageFileName == null || imageFileName.isEmpty()) return;

		Path path = Paths.get(uploadDirPath, imageFileName);
		File file = path.toFile();

		if (file.exists()) {
			file.delete();
		}
	}
}