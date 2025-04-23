package com.camp.model;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MainUIDAO {
	/* 인기 상승 캠핑장 상위 3개*/
	public Collection<PostVO> getPopularCampingList(int categoryId) {
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		List<PostVO> list = conn.selectList("CampingLogMapper.getPopularCampingList", categoryId);
		conn.close();
		return list;
	}
	//상위3개 상세조회
	
	
	
	/* 명예의 캠핑로거 상위 5개*/
	public Collection<MemberVO> getTopMembersByLikes(int i) {
	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    List<MemberVO> List = null;
	    List = conn.selectList("CampingLogMapper.getTopMemberRank", 5);
	    conn.close();
	    return List;
	}
	
	//상위5명 프로필 상세조회

}
