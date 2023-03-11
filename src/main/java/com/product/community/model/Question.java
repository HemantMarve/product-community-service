package com.product.community.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "question_info")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private long questionId;

	@Column(name = "content")
	@NotNull
	private String content;

	@Column(name = "subject")
	@NotNull
	private String subject;

	@Column(name = "productType")
	@NotNull
	private String productType;

	@Column(name = "product_code")
	@NotNull
	private long productCode;

	public long getProductCode() {
		return productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}

	@Column(name = "liked_count", columnDefinition = "bigint default 0")
	private long likedCount;

	@Column(name = "posted_on")
	@CreationTimestamp
	private Date postedOn;

	@Column(name = "question_closed", columnDefinition = "bigint default 0")
	private long questionClosed;

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Comment> comments;

	public long getQuestionId() {
		return questionId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getLikedCount() {
		return likedCount;
	}

	public void setLikedCount(long likedCount) {
		this.likedCount = likedCount;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public long getQuestionClosed() {
		return questionClosed;
	}

	public void setQuestionClosed(long questionClosed) {
		this.questionClosed = questionClosed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
