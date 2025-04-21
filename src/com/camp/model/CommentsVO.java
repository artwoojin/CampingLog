package com.camp.model;

public class CommentsVO {
	private int commentId;
	private String commentContents;
	private String commentDate;
	private int postId;
	private String memberId;
	
	public CommentsVO(String commentContents, String commentDate) {
		super();
		this.commentContents = commentContents;
		this.commentDate = commentDate;
	}
	
	public CommentsVO(int commentId, String commentContents, String commentDate, int postId, String memberId) {
		super();
		this.commentId = commentId;
		this.commentContents = commentContents;
		this.commentDate = commentDate;
		this.postId = postId;
		this.memberId = memberId;
	}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentContents() {
		return commentContents;
	}
	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "CommentsVO [commentId=" + commentId + ", commentContents=" + commentContents + ", commentDate="
				+ commentDate + ", postId=" + postId + ", memberId=" + memberId + "]";
	}
}
