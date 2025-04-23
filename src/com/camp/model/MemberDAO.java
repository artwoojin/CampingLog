package com.camp.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {

	// ȸ������
	public boolean addMember(MemberVO vo){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		// memberImage�� null�̰ų� ������ ��� ����Ʈ �̹��� ����
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

	// �� ���� ���� �� ��й�ȣ Ȯ��
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

	// �� ���� ����
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

	// ��й�ȣ ����
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

	// ������ ���� ��ȸ
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

	// ������ ���� ���
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

	// ������ ���� ���� �� ���� �̹��� ���� ����
	public boolean updateMemberImage(String memberId, String newImageFileName, String uploadDirPath) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;
		
		try {
			// 1. ���� �̹��� ��ȸ
			String oldImage = session.selectOne("memberMapper.getMemberImage", memberId);

			// 2. DB ����(�� �̹�����)
			HashMap<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("memberImage", newImageFileName);

			int updated = session.update("memberMapper.setMemberImage", map);

			// 3. �����ϸ� Ŀ�� + ���� �̹��� ����
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


	// ������ ���� ���� + ����Ʈ �̹��� ����
	public boolean deleteAndSetDefaultImage(MemberVO vo, String uploadDirPath) {
		String memberId = vo.getMemberId();

		// 1. ���� �̹��� ���ϸ� ����(VO)
		String oldImage = vo.getMemberImage();

		// 2. DB ������Ʈ: ����Ʈ �̹����� ����
		boolean updated = setDefaultImage(memberId);

		// 3. ���� �̹��� ���� ����
		if (updated && oldImage != null && !oldImage.equals("defaultMemberImage.png")) {
			deleteImageFile(oldImage, uploadDirPath);

			// 4. memberImage ������Ʈ(VO)
			vo.setMemberImage("defaultMemberImage.png");
		}

		return updated;
	}

	// ����̹��� ���� ���� �޼���1: DB���� memberImage�� ����Ʈ �̹����� ����
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

	// ����̹��� ���� ���� �޼���2: ������ ����� ���� �̹��� ���� ����
	private void deleteImageFile(String imageFileName, String uploadDirPath) {
		if (imageFileName == null || imageFileName.isEmpty()) return;

		Path path = Paths.get(uploadDirPath, imageFileName);
		File file = path.toFile();

		if (file.exists()) {
			file.delete();
		}
	}
}