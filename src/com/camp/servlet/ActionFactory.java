package com.camp.servlet;

public class ActionFactory {

   private ActionFactory() {}

   public static Action getAction(String cmd) {
      Action a;
      System.out.println("cmd: " + cmd);

      switch (cmd) {
      case "detail":
         a = new DetailAction();
         break;
      case "searchResult":
         a = new SearchResultAction();
         break;
      case "mainPopularListAction":
         a = new MainPopularListAction();
         break;
      case "getCategoryName":
         a = new GetCategoryNameAction();
         break;
      case "mainEquListAction":
         a = new MainEquListAction();
         break;
      case "mainRankUpAction":
         a = new MainRankUpAction();
         break;
      case "yesterdayRankListAction":
         a = new YesterdayRankListAction();
         break;
      case "getMyInfo":
         a = new GetMyInfoAction();
         break;
      case "login":
         a = new LoginAction();
         break;
      case "checkDuplicate":
         a = new CheckDuplicateAction();
         break;
      case "signUp":
         a = new SignUpAction();
         break;
      case "addProfilePhotoAction":
          a = new AddProfilePhotoAction();
          break;
      case "updateMemberAction":
         a = new UpdateMemberAction();
         break;
      case "deleteMemberAction":
         a = new DeleteMemberAction();
         break;
      case "tagListAction":
         a = new TagListAction();
         break;
      case "writeAction":
         a = new WriteAction();
         break;
      case "getPostDetail":
         a = new GetPostDetailAction();
         break;
      case "updatePostAction":
         a = new UpdatePostAction();
         break;
      case "deletePostAction":
         a = new DeletePostAction();
         break;
      case "logout":
         a = new LogoutAction();
         break;
      case "passwordCheckAction":
          a = new PasswordCheckAction();
          break;
      case "updatePasswordAction":
         a = new UpdatePasswordAction();
         break;
      case "addBookmark":
          a = new AddBookmarkAction();
          break;
      case "commentsByPagination":
         a = new CommentsByPagination();
         break;
      case "addHeartAction":
         a = new AddHeartAction();
         break;
      case "addComment":
          a = new AddCommentAction();
          break;
      case "noticeAction":
          a = new NoticeAction();
          break;
      case "memberImageUIAction":
    	  a = new MemberImageUIAction();
    	  break;
      default:
         a = new MainUIAction(); 
         break;
      }

      return a;
   }
}
