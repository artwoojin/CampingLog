package com.camp.servlet;

public class ActionFactory {
	//싱글톤
	private ActionFactory(){};

	public static Action getAction(String cmd){
		Action a;
		System.out.println(cmd);
		switch (cmd) {
		case "addCommentAction":
			a=new AddCommentAction();// 댓글등록
			break;
		case "yesterdayRankListAction":
			a=new YesterdayRankListAction(); // 홈화면 랭킹기준 어제
			break;
		case "detail":
			a=new DetailAction(); // 게시글 상세보기
			break;
		case "addHeartAction":
			a=new AddHeartAction(); //실시간 좋아요 추가
			break;
		case "mainRankUpAction":
			a=new MainRankUpAction(); //홈화면 전체회원 승급 업데이트 
			break;  
		case "mainEquListAction":
			a=new MainEquListAction(); //인기 장비리뷰 상위3개 리스트
			break;
		case "memberRankListAction":
			a=new MemberRankListAction();//명예캠핑로거 상위5명
			break;
		case "mainPopularListAction":
			a=new MainPopularListAction(); //인기 캠핑장리뷰 상위3개 리스트
			break;
		case "searchResult":
			a=new SearchResultAction();
			break;
		case "signUp":
			a = new SignUpAction();
			break;
		case "checkDuplicate":
			a = new CheckDuplicateAction();
			break;
		case "login":
			a=new LoginAction();
			break;
		case "myPageUI":
			a=new MyPageUIAction();
			break;
		case "GetMyInfo":
			a=new GetMyInfoAction();
			break;
		case "myPagePosts":
			a=new MyPagePostsAction();
			break;
		case "myPageBookmark":
			a=new MyPageBookmarkAction();
			break;
		case "passwordOkResult":
			a=new PasswordOkResultAction();
			break;
		case "passwordCheckAction":
		    a = new PasswordCheckAction();
		    break;
		default:
			a=new MainUIAction();
			break;
		}
	    System.out.println("[ActionFactory] 선택된 Action: " + (a != null ? a.getClass().getSimpleName() : "null"));
		return a;
	}
}
