package com.camp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.camp.model.PostVO;

public class PostDAO {
	
	// 전체 게시글 페이징 조회
	public List<PostVO> getPostPage(int start, int end) {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        Map<String, Integer> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        List<PostVO> list = conn.selectList("postMapper.getPostsByPage", map);
        conn.close();
        return list;
    }
	
	// 카테고리별 페이징 조회 
	public List<PostVO> getPostPageByCategory(int categoryId, int start, int end) {
		try (SqlSession conn = DBCP.getSqlSessionFactory().openSession()) {
			Map<String, Integer> map = new HashMap<>();
			map.put("categoryId", categoryId);
			map.put("start",      start);
			map.put("end",        end);
			return conn.selectList("postMapper.getPostsByCategoryPage", map);
		}
	}
	
	// 카테고리별 총 게시글 수 조회 
	public int getTotalPostCountByCategory(int categoryId) {
		try (SqlSession conn = DBCP.getSqlSessionFactory().openSession()) {
			return conn.selectOne("postMapper.getTotalPostCountByCategory", categoryId);
		}
	}
	
    // 전체 게시글 수 가져오기
    public int getTotalPostCount() {
        SqlSession conn = DBCP.getSqlSessionFactory().openSession();
        int count = conn.selectOne("postMapper.getTotalPostCount");
        conn.close();
        return count;
    }
    
    public PostVO getDetailContents(SqlSession session, int postId){
        return session.selectOne("postMapper.getContents", postId);
    }

    public List<String> getDetailTag(SqlSession session, int postId) {
        return session.selectList("postMapper.getTag", postId);
    }

    public String getMemberImage(SqlSession session, String memberId) {
        return session.selectOne("commentsMapper.getMemberImage", memberId);
    }
    
    public int insertPost(SqlSession session, PostVO vo) {
		return session.insert("postMapper.insertPost", vo);
	}
    
    public int getLastPostId(SqlSession session) {
		return session.selectOne("postMapper.getLastPostId");
	}
    
    public int insertPostTag(SqlSession session, int postId, int tagId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("postId", postId);
		map.put("tagId", tagId);
		return session.insert("postTagMapper.insertPostTag", map);
	}
    
    public PostVO getPostById(SqlSession session, int postId) {
		return session.selectOne("postMapper.getPostById", postId);
	}
	
	public List<Integer> getSelectedTagIds(SqlSession session, int postId) {
		return session.selectList("postTagMapper.getSelectedTagIds", postId);
	}

	public List<Integer> getTagIdsByPostId(SqlSession session, int postId) {
	    return session.selectList("postTagMapper.getTagIdsByPostId", postId);
	}
	
	public int updatePost(SqlSession session, PostVO vo) {
	    return session.update("postMapper.updatePost", vo);
	}

	public int deleteTagsByPostId(SqlSession session, int postId) {
	    return session.delete("postTagMapper.deleteTagsByPostId", postId);
	}
	
	public int deletePost(SqlSession session, int postId) {
	    return session.delete("postMapper.deletePost", postId);
	}
	
	/* 실시간 좋아요 추가*/
	public int insertLike(int postId, String memberId) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("postId", postId);
	    param.put("memberId", memberId);

	    SqlSession conn = DBCP.getSqlSessionFactory().openSession();
	    int result = conn.insert("insertLike", param);
	    conn.commit();
	    conn.close();
	    return result;
	}

}
