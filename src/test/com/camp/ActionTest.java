package test.com.camp;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.camp.model.DBCP;
import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;
import com.camp.model.PostDAO;
import com.camp.model.PostVO;
import com.camp.service.DetailService;
import com.camp.servlet.ActionFactory;
import com.camp.servlet.DetailAction;

public class ActionTest {
	
	@Test
	public void DetailTest() {
		DetailService service = new DetailService(DBCP.getSqlSessionFactory());

		PostVO post = service.getPostDetails(1); // postId=1�� �Խñ�

		assertNotNull("�Խñ��� �����ؾ� �մϴ�.", post);
		assertEquals("�Խñ� ID�� ��ġ�ؾ� �մϴ�.", 1, post.getPostId());
	}
	
	@Test
    public void testGetMyInfo() {
        MemberDAO dao = new MemberDAO();
        
        MemberVO member = dao.getMyInfo("iveliz"); // memberId=1�� ȸ��
        
        assertNotNull("ȸ�� ������ null�̸� �� �˴ϴ�.", member);
        assertEquals("ȸ�� ID�� ��ġ�ؾ� �մϴ�.", "iveliz", member.getMemberId());
    }
	
    @Test
    public void testGetTotalPostCount_withEmptySearchTerm() {
        PostDAO dao = new PostDAO();
        
        String searchTerm = ""; // �� �˻���
        
        int totalCount = dao.getTotalPostCount(searchTerm);
        
        assertTrue("�� �˻���� �� �Խñ� ���� 0�� �̻��̾�� �մϴ�.", totalCount >= 0);
    }

}
