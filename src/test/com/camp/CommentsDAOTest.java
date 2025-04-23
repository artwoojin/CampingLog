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
	public void 단위_테스트_시작() throws Exception {
		cdao=new CommentsDAO();
		factory=DBCP.getSqlSessionFactory();

	}

	@Test
	public void 게시글_댓글_조회_성공() {
		try (SqlSession session = factory.openSession()) {
			assertEquals(5, cdao.getDetailComments(session, 1).size());
		}
	}

	@Test
	public void 게시글_댓글_조회_실패() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals(12, cdao.getDetailComments(session, 1).size());
		}
	}

	// 실제 실행 시 DB 상태에 따라 주석 해제
	// @Test
	public void 게시글_댓글_등록() {
		try (SqlSession session = factory.openSession()) {
			boolean result = cdao.addComments(session, new CommentsVO("저기 좋아요!!!", 1, "user01"));
			if (result) session.commit();
			else session.rollback();
			assertTrue(result);
		}
	}

	@Test
	public void 게시글_댓글_등록_실패() {
		try (SqlSession session = factory.openSession()) {
			boolean result = cdao.addComments(session, new CommentsVO("실패 테스트", 9999, "abc123"));
			if (result) session.commit();
			else session.rollback();
			assertFalse(result);
		}
	}

	@Test
	public void 댓글_본인_프로필사진_성공() {
		try (SqlSession session = factory.openSession()) {
			assertEquals("silver.jpg", cdao.getMemberImage(session, "silver99"));
		}
	}

	@Test
	public void 댓글_본인_프로필사진_실패() {
		try (SqlSession session = factory.openSession()) {
			assertNotEquals("silver.png", cdao.getMemberImage(session, "silver99"));
		}
	}
}
