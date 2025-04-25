<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.camp.model.*" %>
<%
    Map<String, Object> result = (Map<String, Object>) request.getAttribute("result");

    PostVO post = (PostVO) result.get("post");
    List<TagVO> allTags = (List<TagVO>) result.get("allTags");
    List<Integer> selectedTags = (List<Integer>) result.get("selectedTags");
%>
{
  "post": {
    "postId": <%= post.getPostId() %>,
    "postTitle": "<%= post.getPostTitle() %>",
    "postContents": "<%= post.getPostContents().replace("\n", "\\n").replace("\"", "\\\"") %>",
    "postImage": "<%= post.getPostImage() != null ? post.getPostImage() : "" %>",
    "categoryId": <%= post.getCategoryId() %>
  },
  "allTags": [
    <%
      for (int i = 0; i < allTags.size(); i++) {
        TagVO tag = allTags.get(i);
    %>
    {
      "tagId": <%= tag.getTagId() %>,
      "tagName": "<%= tag.getTagName() %>"
    }<%= i < allTags.size() - 1 ? "," : "" %>
    <%
      }
    %>
  ],
  "selectedTags": [
    <%
      for (int i = 0; i < selectedTags.size(); i++) {
    %><%= selectedTags.get(i) %><%= i < selectedTags.size() - 1 ? "," : "" %><%
      }
    %>
  ]
}
