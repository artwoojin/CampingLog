package com.camp.model;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MainUIDAO {
	/* �α� ��� ķ���� ���� 3��*/
	public Collection<PostVO> getPopularCampingList(int categoryId) {
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		List<PostVO> list = conn.selectList("mainUIMapper.getPopularCampingList", categoryId);
		conn.close();
		return list;
	}
	//����3�� ����ȸ
	
	
	
	/* ���� ķ�ηΰ� ���� 5��*/
	public Collection<MemberVO> getTopMembersByLikes(int i) {
	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    List<MemberVO> List = null;
	    List = conn.selectList("mainUIMapper.getTopMemberRank", 5);
	    conn.close();
	    return List;
	}
	//����5�� ������ ����ȸ

	
	/* �α� ��񸮺� ���� 3��*/
	public Collection<PostVO> getPopularEquList(int categoryId) {
		SqlSession conn = DBCP.getSqlSessionFactory().openSession();
		List<PostVO> list = conn.selectList("mainUIMapper.getPopularEquList", categoryId);
		conn.close();
		return list;
	}
	
	//����3�� ����ȸ
}
