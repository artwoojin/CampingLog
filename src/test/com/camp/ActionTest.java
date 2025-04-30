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

		PostVO post = service.getPostDetails(1); // postId=1번 게시글

		assertNotNull("게시글이 존재해야 합니다.", post);
		assertEquals("게시글 ID가 일치해야 합니다.", 1, post.getPostId());
	}
	
	@Test
    public void testGetMyInfo() {
        MemberDAO dao = new MemberDAO();
        
        MemberVO member = dao.getMyInfo("iveliz"); // memberId=1번 회원
        
        assertNotNull("회원 정보는 null이면 안 됩니다.", member);
        assertEquals("회원 ID가 일치해야 합니다.", "iveliz", member.getMemberId());
    }
	
    @Test
    public void testGetTotalPostCount_withEmptySearchTerm() {
        PostDAO dao = new PostDAO();
        
        String searchTerm = ""; // 빈 검색어
        
        int totalCount = dao.getTotalPostCount(searchTerm);
        
        assertTrue("빈 검색어여도 총 게시글 수는 0개 이상이어야 합니다.", totalCount >= 0);
    }

}
