package test.com.camp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.camp.model.DBCP;
import com.camp.model.PostDAO;
import com.camp.model.PostVO;

public class PostDAOTest {
	PostDAO pdao=null;
	SqlSessionFactory factory = null;
	
	@Before
	public void 단위_테스트_시작() throws Exception {
		pdao=new PostDAO();
		factory=DBCP.getSqlSessionFactory();
		
	}

	@Test
    public void 게시글_조회_성공() {
        try (SqlSession session = factory.openSession()) {
            PostVO post = pdao.getDetailContents(session, 1);
            assertEquals(1, post.getPostId());
        }
    }

    @Test
    public void 게시글_조회_실패() {
        try (SqlSession session = factory.openSession()) {
            PostVO post = pdao.getDetailContents(session, 1);
            assertNotEquals(2, post.getPostId());
        }
    }

}
