package com.camp.model;

import org.apache.ibatis.session.SqlSession;

public class MemberDAO {
	
	// 회원가입
	public boolean addMember(MemberVO vo){
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		boolean result = false;
		
		try{
			conn.insert("memberMapper.addMember",vo);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}finally{
			conn.close();
		}
		return result;
	}
	
}
