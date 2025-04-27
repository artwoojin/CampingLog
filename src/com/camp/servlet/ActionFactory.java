package com.camp.servlet;

public class ActionFactory {
	//�̱���
	private ActionFactory(){};

	public static Action getAction(String cmd){
		Action a;
		System.out.println(cmd);
		switch (cmd) {
		case "addCommentAction":
			a=new AddCommentAction();// ��۵��
			break;
		case "yesterdayRankListAction":
			a=new YesterdayRankListAction(); // Ȩȭ�� ��ŷ���� ����
			break;
		case "detail":
			a=new DetailAction(); // �Խñ� �󼼺���
			break;
		case "addHeartAction":
			a=new AddHeartAction(); //�ǽð� ���ƿ� �߰�
			break;
		case "mainRankUpAction":
			a=new MainRankUpAction(); //Ȩȭ�� ��üȸ�� �±� ������Ʈ 
			break;  
		case "mainEquListAction":
			a=new MainEquListAction(); //�α� ��񸮺� ����3�� ����Ʈ
			break;
		case "memberRankListAction":
			a=new MemberRankListAction();//��ķ�ηΰ� ����5��
			break;
		case "mainPopularListAction":
			a=new MainPopularListAction(); //�α� ķ���帮�� ����3�� ����Ʈ
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
	    System.out.println("[ActionFactory] ���õ� Action: " + (a != null ? a.getClass().getSimpleName() : "null"));
		return a;
	}
}
