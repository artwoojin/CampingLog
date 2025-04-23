package com.camp.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class TagDAO {

    public List<String> getDetailTag(SqlSession session, int postId) {
        return session.selectList("tagMapper.getTag", postId);
    }
}