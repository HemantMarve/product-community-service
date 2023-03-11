package com.product.community.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.community.exception.QuestionNotFoundException;
import com.product.community.model.Comment;
import com.product.community.model.LikedComment;
import com.product.community.model.LikedQuestion;
import com.product.community.model.Question;
import com.product.community.model.User;
import com.product.community.repo.CommentRepo;
import com.product.community.repo.LikedCommentRepo;
import com.product.community.repo.LikedQuestionRepo;
import com.product.community.repo.QuestionRepo;

@Service
public class QuestionServiceImpl {

	@Autowired
	private QuestionRepo questionRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private LikedQuestionRepo likedQuestionRepo;
	@Autowired
	private LikedCommentRepo likedCommentRepo;

	public void saveQuestion(Question question, String userId) {
		User user = new User();
		user.setUserId(userId);
		question.setUser(user);
		this.questionRepo.save(question);

	}

	public void saveComment(Comment comment, String userId, long questionId) {
		Question question = new Question();
		question.setQuestionId(questionId);
		comment.setUserId(userId);
		comment.setQuestion(question);
		commentRepo.save(comment);
	}

	public List<Question> findAll() {
		List<Question> questions;
		questions = questionRepo.findAll();
		if (questions == null) {
			questions = new LinkedList<>();
		}
		for (Question q : questions) {
			q.setComments(this.connectComments(q.getComments()));
		}

		return questions;
	}

	public void likeQuestion(long questionId, String userId) {
		LikedQuestion likedQuestion = new LikedQuestion();
		User user = new User();
		Question question = new Question();
		user.setUserId(userId);
		question.setQuestionId(questionId);
		likedQuestion.setQuestion(question);
		likedQuestion.setUser(user);
		likedQuestionRepo.save(likedQuestion);

	}

	public void likeComment(long commentId, String userId) {
		LikedComment likedComment = new LikedComment();
		User user = new User();
		Comment comment = new Comment();
		user.setUserId(userId);
		comment.setCommentId(commentId);
		likedComment.setComment(comment);
		likedComment.setUser(user);
		likedCommentRepo.save(likedComment);

	}

	public void dislikeComment(long commentId, String userId) {
		User user = new User();
		Comment comment = new Comment();
		user.setUserId(userId);
		comment.setCommentId(commentId);
		likedCommentRepo.removeByUserAndComment(user, comment);
	}

	public void dislikeQuestion(long questionId, String userId) {
		User user = new User();
		Question question = new Question();
		user.setUserId(userId);
		question.setQuestionId(questionId);
		likedQuestionRepo.removeByUserAndQuestion(user, question);
	}

	public Map<String,Long> countQuestions() {
		long countOfClosedQuestion=this.questionRepo.countClosedQuestions();
		long totalQuestions=this.questionRepo.count();
		Map<String,Long> stats=new HashMap<>();
		stats.put("total questions", totalQuestions);
		stats.put("closed questions", countOfClosedQuestion);
		return stats;
	}

	public Question closeQuestion(long questionId, long commentId) throws QuestionNotFoundException {
		Question question;
		try {
			question = this.questionRepo.findById(questionId).orElseThrow(() -> new Exception("Invalid User"));
			question.setQuestionClosed(commentId);
			this.questionRepo.save(question);
		} catch (Exception e) {
			question = new Question();
			throw new QuestionNotFoundException("Question Not Found Exception");
		}

		return question;
	}

	public List<Question> searchQuestionOnText(String text) {

		List<Question> questions = this.questionRepo.searchQuestions(text);
		if (questions == null) {
			questions = new LinkedList<>();
		}
		for (Question q : questions) {
			q.setComments(this.connectComments(q.getComments()));
		}

		return questions;
	}

	public List<Comment> connectComments(List<Comment> all) {
		List<Comment> result = new ArrayList<>();
		Map<Long, Comment> map = new HashMap<>();
		for (Comment c : all) {
			if (c.getParentId() == null) {
				c.setChilds(new ArrayList<>());
				result.add(c);
			}
			map.put(c.getCommentId(), c);
		}
		for (Comment c : all) {
			if (c.getParentId() != null) {
				Comment parent = map.get(c.getParentId());
				if (parent.getChilds() == null) {
					parent.setChilds(new ArrayList<>());
				}
				parent.getChilds().add(c);
			}
		}
		return result;
	}

}
