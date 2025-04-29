package test.com.camp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.camp.model.CommentsVO;
import com.camp.model.DBCP;
import com.camp.service.DetailService;

public class DetailServiceTest {
	DetailService ds=null;	
	
	@Before
	public void 단위_테스트_시작() throws Exception {
		SqlSessionFactory factory=DBCP.getSqlSessionFactory();
		ds=new DetailService(factory);
	}


	@Test
	public void 서비스_게시글_조회_성공() {
		assertEquals(1, ds.getPostDetails(1).getPostId());
	}
	
	@Test
	public void 서비스_게시글_조회_실패() {
		assertNotEquals(2, ds.getPostDetails(1).getPostId());
	}
	
	@Test
	public void 서비스_게시글_댓글_조회_성공() {
		assertEquals(5, ds.getCommentsForPost(1).size());
	}
	
	@Test
	public void 서비스_게시글_댓글_조회_실패() {
		assertNotEquals(52, ds.getCommentsForPost(1).size());
	}
	
//	@Test
	public void 서비스_게시글_댓글_등록() {
		assertTrue(ds.addComment(new CommentsVO("와 가보고 싶어요!!!", 2, "user02")));
	}
	
	@Test
	public void 서비스_게시글_댓글_등록_실패() {
		assertFalse(ds.addComment(new CommentsVO("저기 좋아요!!!", 9999, "abc123")));
	}
	
	@Test
	public void 서비스_댓글_본인_프로필사진_성공() {
		assertEquals("silver.jpg", ds.getMemberImage("silver99"));
	}
	
	@Test
	public void 서비스_댓글_본인_프로필사진_실패() {
		assertNotEquals("silver.png", ds.getMemberImage("silver99"));
	}
}
