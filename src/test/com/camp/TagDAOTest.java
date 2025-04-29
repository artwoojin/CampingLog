package test.com.camp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.camp.model.DBCP;
import com.camp.model.TagDAO;

public class TagDAOTest {
	SqlSessionFactory factory=null;
	TagDAO tdao=null;

	@Before
	public void 단위_테스트_시작() throws Exception {
		tdao=new TagDAO();
		factory = DBCP.getSqlSessionFactory();
	}
	
		
	@Test
	public void 게시글_태그_조회_성공() {
		try (SqlSession session = factory.openSession()){
            assertEquals(2, tdao.getDetailTag(session, 1).size());
        } 
	}

	@Test
	public void 게시글_태그_조회_실패() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals(1, tdao.getDetailTag(session, 1).size());
		}
	}
}
