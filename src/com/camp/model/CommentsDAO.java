package com.camp.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class CommentsDAO {

    // ��� ��� ��ȸ
    public List<CommentsVO> getDetailComments(SqlSession session, int postId) {
        return session.selectList("commentsMapper.getComments", postId);
    }

    // ��� ���
    public boolean addComments(SqlSession session, CommentsVO vo) {
        boolean result = false;
        try {
            session.insert("commentsMapper.addComments", vo);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��� ��� �� ���� �߻�: " + e.getMessage());
        }
        return result;
    }

    // ��� �ۼ����� ������ �̹��� ��������
    public String getMemberImage(SqlSession session, String memberId) {
        return session.selectOne("commentsMapper.getMemberImage", memberId);
    }
}
