package com.camp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class PostDAO {

	public List<PostVO> getPostPage(int start, int end) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        Map<String, Integer> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        List<PostVO> list = conn.selectList("postMapper.getPostsByPage", map);
        conn.close();
        return list;
    }
	
    // ��ü �Խñ� �� ��������
    public int getTotalPostCount() {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        int count = conn.selectOne("postMapper.getTotalPostCount");
        conn.close();
        return count;
    }
}
