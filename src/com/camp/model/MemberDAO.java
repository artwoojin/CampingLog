package com.camp.model;

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
}
