package com.product.community.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private long commentId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name="user_id")
	private String userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="question_id")
	@JsonIgnore
	private Question question;
	
	@Column(name="posted_on")
	@CreationTimestamp
	private Date commentedOn;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "liked_count",columnDefinition = "bigint default 0")
	private long likedCount;
	
	@Transient
	private List<Comment> childs;
	
	public List<Comment> getChilds() {
		return childs;
	}

	public void setChilds(List<Comment> childs) {
		this.childs = childs;
	}

	public Question getQuestion() {
		return question;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public long getLikedCount() {
		return likedCount;
	}

	public void setLikedCount(long likedCount) {
		this.likedCount = likedCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
}
