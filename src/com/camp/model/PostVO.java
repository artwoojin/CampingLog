package com.camp.model;

public class PostVO {
	 private int postId;
	 private String postTitle;
	 private String postContents;
	 private String postImage;     
	 private String postDate;
	 private String memberId;      
	 private int categoryId;
	 private String nickName;
	 private String memberImage;
	 private String badgelmage;
	
	public PostVO(){}
	
	public PostVO(String nickName, String memberImage, String badgelmage) {
		this.nickName = nickName;
		this.memberImage = memberImage;
		this.badgelmage = badgelmage;
	}

	public PostVO(String postTitle, String postContents, String postImage, String memberId, int categoryId) {
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.postImage = postImage;
		this.memberId = memberId;
		this.categoryId = categoryId;
	}

	public PostVO(int postId, String postTitle, String postContents, String postImage, String postDate, String memberId,
			int categoryId) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.postImage = postImage;
		this.postDate = postDate;
		this.memberId = memberId;
		this.categoryId = categoryId;
	}
	

	public PostVO(int postId, String postTitle, String postContents, String postImage, String postDate, String memberId,
			int categoryId, String nickName, String memberImage, String badgelmage) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.postImage = postImage;
		this.postDate = postDate;
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.nickName = nickName;
		this.memberImage = memberImage;
		this.badgelmage = badgelmage;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(String memberImage) {
		this.memberImage = memberImage;
	}

	public String getBadgelmage() {
		return badgelmage;
	}

	public void setBadgelmage(String badgelmage) {
		this.badgelmage = badgelmage;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", postTitle=" + postTitle + ", postContents=" + postContents
				+ ", postImage=" + postImage + ", postDate=" + postDate + ", memberId=" + memberId + ", categoryId="
				+ categoryId + ", nickName=" + nickName + ", memberImage=" + memberImage + ", badgelmage=" + badgelmage
				+ "]";
	}
	
	
	
	

	 
}

