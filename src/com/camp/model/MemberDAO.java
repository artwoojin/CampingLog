package com.camp.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {

	// 회원가입
	public boolean addMember(MemberVO vo){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;

		try{
			conn.insert("memberMapper.addMember",vo);
			conn.commit();
			result = true;
		}catch(Exception e){
			conn.rollback();
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
}
