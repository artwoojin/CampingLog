package com.camp.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {

	// �쉶�썝媛��엯
	public boolean addMember(MemberVO vo){
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		// memberImage媛� null�씠嫄곕굹 怨듬갚�씪 寃쎌슦 �뵒�뤃�듃 �씠誘몄� �꽕�젙
		if (vo.getMemberImage() == null || vo.getMemberImage().isEmpty()) {
			vo.setMemberImage("defaultMemberImage.png");
		}

		try{
			session.insert("memberMapper.addMember", vo);
			session.commit();
			result = true;
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	// 가입시 ID 중복 확인
	public boolean isDuplicateId(String value) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			int count = session.selectOne("memberMapper.isDuplicateId", value);
			return count > 0;
		} finally {
			session.close();
		}
	}

	// 가입시 이메일 중복 확인
	public boolean isDuplicateEmail(String email) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			int count = session.selectOne("memberMapper.isDuplicateEmail", email);
			return count > 0;
		} finally {
			session.close();
		}
	}

	// 가입시 닉네임 중복 확인
	public boolean isDuplicateNickName(String nickName) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			int count = session.selectOne("memberMapper.isDuplicateNickName", nickName);
			return count > 0;
		} finally {
			session.close();
		}
	}

	public List<MemberVO> getMembers(){
		List<MemberVO> list = null;
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		list = conn.selectList("memberMapper.getMembers");
		conn.close();
		return list;
	}

	// �궡 �젙蹂� �닔�젙 �쟾 鍮꾨�踰덊샇 �솗�씤
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

	// �궡 �젙蹂� �닔�젙
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

	// 鍮꾨�踰덊샇 �닔�젙
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

	// �봽濡쒗븘 �궗吏� 議고쉶
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

	// �봽濡쒗븘 �궗吏� �벑濡�
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

	// �봽濡쒗븘 �궗吏� �닔�젙 諛� 湲곗〈 �씠誘몄� �뙆�씪 �궘�젣
	public boolean updateMemberImage(String memberId, String newImageFileName, String uploadDirPath) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try {
			// 1. 湲곗〈 �씠誘몄� 議고쉶
			String oldImage = session.selectOne("memberMapper.getMemberImage", memberId);

			// 2. DB 蹂�寃�(�깉 �씠誘몄�濡�)
			HashMap<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("memberImage", newImageFileName);

			int updated = session.update("memberMapper.setMemberImage", map);

			// 3. �꽦怨듯븯硫� 而ㅻ컠 + 湲곗〈 �씠誘몄� �궘�젣
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


	// �봽濡쒗븘 �궗吏� �궘�젣 + �뵒�뤃�듃 �씠誘몄� �꽕�젙
	public boolean deleteAndSetDefaultImage(MemberVO vo, String uploadDirPath) {
		String memberId = vo.getMemberId();

		// 1. 湲곗〈 �씠誘몄� �뙆�씪紐� 異붿텧(VO)
		String oldImage = vo.getMemberImage();

		// 2. DB �뾽�뜲�씠�듃: �뵒�뤃�듃 �씠誘몄�濡� 蹂�寃�
		boolean updated = setDefaultImage(memberId);

		// 3. 湲곗〈 �씠誘몄� �뙆�씪 �궘�젣
		if (updated && oldImage != null && !oldImage.equals("defaultMemberImage.png")) {
			deleteImageFile(oldImage, uploadDirPath);

			// 4. memberImage �뾽�뜲�씠�듃(VO)
			vo.setMemberImage("defaultMemberImage.png");
		}

		return updated;
	}

	// 硫ㅻ쾭�씠誘몄� �궘�젣 �궡遺� 硫붿꽌�뱶1: DB�뿉�꽌 memberImage瑜� �뵒�뤃�듃 �씠誘몄�濡� �꽕�젙
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

	// 硫ㅻ쾭�씠誘몄� �궘�젣 �궡遺� 硫붿꽌�뱶2: �꽌踰꾩뿉 ���옣�맂 湲곗〈 �씠誘몄� �뙆�씪 �궘�젣
	private void deleteImageFile(String imageFileName, String uploadDirPath) {
		if (imageFileName == null || imageFileName.isEmpty()) return;

		Path path = Paths.get(uploadDirPath, imageFileName);
		File file = path.toFile();

		if (file.exists()) {
			file.delete();
		}
	}

	// 留덉씠�럹�씠吏� 議고쉶(�궡 �젙蹂�)
	public MemberVO getMyInfo(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			return session.selectOne("memberMapper.getMyInfo", memberId);
		} finally {
			session.close();
		}
	}

	// �궡媛� �벖 湲� 由ъ뒪�듃 議고쉶
	public List<HashMap<String, Object>> getMyPosts(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			return session.selectList("memberMapper.getMyPosts", memberId);
		} finally {
			session.close();
		}
	}

	// 遺곷쭏�겕�븳 湲� 由ъ뒪�듃 議고쉶
	public List<HashMap<String, Object>> getMyBookmarks(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			return session.selectList("memberMapper.getMyBookmarks", memberId);
		} finally {
			session.close();
		}
	}

	// �긽��諛� 留덉씠 �럹�씠吏� 議고쉶
	public MemberVO getYourInfo(String memberId) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		try {
			return session.selectOne("memberMapper.getYourInfo", memberId);
		} finally {
			session.close();
		}
	}

	// �쉶�썝 �깉�눜(�궘�젣)
	public boolean deleteMember(MemberVO vo) {
		SqlSession session = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try{
			session.delete("memberMapper.deleteMember", vo);
			session.commit();
			result = true;
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}




}