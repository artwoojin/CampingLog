package com.camp.model;

import org.apache.ibatis.session.SqlSession;

public class LoginDAO {

	public String login(MemberVO vo){
		String nickName = null;
		SqlSession session = DBCP.getSqlSessionFactory().openSession();

		try {
			nickName = session.selectOne("memberMapper.login", vo);
		} finally {
			session.close();
		}
		return nickName;
	}
}