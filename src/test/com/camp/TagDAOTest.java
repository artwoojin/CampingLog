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
	public void ����_�׽�Ʈ_����() throws Exception {
		tdao=new TagDAO();
		factory = DBCP.getSqlSessionFactory();
	}
	
		
	@Test
	public void �Խñ�_�±�_��ȸ_����() {
		try (SqlSession session = factory.openSession()){
            assertEquals(2, tdao.getDetailTag(session, 1).size());
        } 
	}

	@Test
	public void �Խñ�_�±�_��ȸ_����() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals(1, tdao.getDetailTag(session, 1).size());
		}
	}
}
