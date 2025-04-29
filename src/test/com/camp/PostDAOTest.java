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
	public void ����_�׽�Ʈ_����() throws Exception {
		pdao=new PostDAO();
		factory=DBCP.getSqlSessionFactory();
		
	}

	@Test
    public void �Խñ�_��ȸ_����() {
        try (SqlSession session = factory.openSession()) {
            PostVO post = pdao.getDetailContents(session, 1);
            assertEquals(1, post.getPostId());
        }
    }

    @Test
    public void �Խñ�_��ȸ_����() {
        try (SqlSession session = factory.openSession()) {
            PostVO post = pdao.getDetailContents(session, 1);
            assertNotEquals(2, post.getPostId());
        }
    }

}
