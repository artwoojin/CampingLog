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
	public void ����_�׽�Ʈ_����() throws Exception {
		SqlSessionFactory factory=DBCP.getSqlSessionFactory();
		ds=new DetailService(factory);
	}


	@Test
	public void ����_�Խñ�_��ȸ_����() {
		assertEquals(1, ds.getPostDetails(1).getPostId());
	}
	
	@Test
	public void ����_�Խñ�_��ȸ_����() {
		assertNotEquals(2, ds.getPostDetails(1).getPostId());
	}
	
	@Test
	public void ����_�Խñ�_���_��ȸ_����() {
		assertEquals(5, ds.getCommentsForPost(1).size());
	}
	
	@Test
	public void ����_�Խñ�_���_��ȸ_����() {
		assertNotEquals(52, ds.getCommentsForPost(1).size());
	}
	
//	@Test
	public void ����_�Խñ�_���_���() {
		assertTrue(ds.addComment(new CommentsVO("�� ������ �;��!!!", 2, "user02")));
	}
	
	@Test
	public void ����_�Խñ�_���_���_����() {
		assertFalse(ds.addComment(new CommentsVO("���� ���ƿ�!!!", 9999, "abc123")));
	}
	
	@Test
	public void ����_���_����_�����ʻ���_����() {
		assertEquals("silver.jpg", ds.getMemberImage("silver99"));
	}
	
	@Test
	public void ����_���_����_�����ʻ���_����() {
		assertNotEquals("silver.png", ds.getMemberImage("silver99"));
	}
}
