package test.com.camp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.camp.model.CommentsDAO;
import com.camp.model.CommentsVO;
import com.camp.model.DBCP;

public class CommentsDAOTest {
	CommentsDAO cdao=null;
	SqlSessionFactory factory = null;

	@Before
	public void ����_�׽�Ʈ_����() throws Exception {
		cdao=new CommentsDAO();
		factory=DBCP.getSqlSessionFactory();

	}

	@Test
	public void �Խñ�_���_��ȸ_����() {
		try (SqlSession session = factory.openSession()) {
			assertEquals(5, cdao.getDetailComments(session, 1).size());
		}
	}

	@Test
	public void �Խñ�_���_��ȸ_����() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals(12, cdao.getDetailComments(session, 1).size());
		}
	}

	// ���� ���� �� DB ���¿� ���� �ּ� ����
	// @Test
	public void �Խñ�_���_���() {
		try (SqlSession session = factory.openSession()) {
			boolean result = cdao.addComments(session, new CommentsVO("���� ���ƿ�!!!", 1, "user01"));
			if (result) session.commit();
			else session.rollback();
			assertTrue(result);
		}
	}

	@Test
	public void �Խñ�_���_���_����() {
		try (SqlSession session = factory.openSession()) {
			boolean result = cdao.addComments(session, new CommentsVO("���� �׽�Ʈ", 9999, "abc123"));
			if (result) session.commit();
			else session.rollback();
			assertFalse(result);
		}
	}

	@Test
	public void ���_����_�����ʻ���_����() {
		try (SqlSession session = factory.openSession()) {
			assertEquals("silver.jpg", cdao.getMemberImage(session, "silver99"));
		}
	}

	@Test
	public void ���_����_�����ʻ���_����() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals("silver.png", cdao.getMemberImage(session, "silver99"));
		}
	}
}
