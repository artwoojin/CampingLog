package com.camp.model;

public class PostVO {
	 private int postId;
	 private String postTitle;
	 private String postContents;
	 private String postImage;     
	 private String postDate;
	 private String memberId;      
	 private int categoryId;
	 
	public PostVO(int postId, String postTitle, String postContents, String postImage, String postDate, String memberId,
			int categoryId) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.postImage = postImage;
		this.postDate = postDate;
		this.memberId = memberId;
		this.categoryId = categoryId;
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContents() {
		return postContents;
	}
	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}
	public String getPostImage() {
		return postImage;
	}
	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", postTitle=" + postTitle + ", postContents=" + postContents
				+ ", postImage=" + postImage + ", postDate=" + postDate + ", memberId=" + memberId + ", categoryId="
				+ categoryId + "]";
	}
	
	

	 
}

