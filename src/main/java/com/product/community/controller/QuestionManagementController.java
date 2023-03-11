package com.product.community.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.community.exception.QuestionNotFoundException;
import com.product.community.model.Comment;
import com.product.community.model.Question;
import com.product.community.model.StandardMessage;
import com.product.community.service.impl.QuestionServiceImpl;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins="http://localhost:8083/")
public class QuestionManagementController {

	@Autowired
	QuestionServiceImpl questionServiceImpl;
	
	@PostMapping("")
	public ResponseEntity<StandardMessage> saveQuestion(@RequestBody Question question,Principal principal) {
		questionServiceImpl.saveQuestion(question, principal.getName());
		 return ResponseEntity.ok(new StandardMessage("success","question posted"));
	}
	
	@GetMapping("")
	public ResponseEntity<List<Question>> getQuestions() {
		
		return new ResponseEntity<List<Question>>(questionServiceImpl.findAll(),HttpStatus.OK);
	}
	
	
	@PostMapping("/comment/{questionId}")
	public ResponseEntity<StandardMessage> commentOnQuestion(@RequestBody Comment comment,@PathVariable("questionId") long questionId,Principal principal) {
		questionServiceImpl.saveComment(comment, principal.getName(),questionId);
		return ResponseEntity.ok(new StandardMessage("success","comment added"));
	}
	@GetMapping("/search/{text}")
    public ResponseEntity<List<Question>> searchQuestions(@PathVariable("text") String text) {
		
		return ResponseEntity.ok(this.questionServiceImpl.searchQuestionOnText(text));
	}
	
	@GetMapping("/close/{questionId}/{commentId}")
	public ResponseEntity<Question> closeQuestion(@PathVariable("questionId") long questionId,@PathVariable("commentId") long commentId) throws QuestionNotFoundException {
		Question question=this.questionServiceImpl.closeQuestion(questionId,commentId);
		return ResponseEntity.ok(question);
	}
	
	
	
	@GetMapping("/stats")
	public ResponseEntity<Map<String,Long>> getStats() {
		return ResponseEntity.ok(this.questionServiceImpl.countQuestions());
	}
	
	@GetMapping("/getFilter")
	public ResponseEntity<Object> getFilterData() {
		return null;
	}
	
	@GetMapping("/like/{questionId}")
	public ResponseEntity<StandardMessage> likeQuestion(@PathVariable("questionId") long questionId,Principal principal) {
		this.questionServiceImpl.likeQuestion(questionId,principal.getName());
		return ResponseEntity.ok(new StandardMessage("success","question liked"));
	}
	@GetMapping("/dislike/{questionId}")
	public ResponseEntity<StandardMessage> dislikeQuestion(@PathVariable("questionId") long questionId,Principal principal) {
		this.questionServiceImpl.dislikeQuestion(questionId, principal.getName());
		return ResponseEntity.ok(new StandardMessage("success","question disliked"));
	}
	
	@GetMapping("/like/comment/{commentId}")
	public ResponseEntity<Object> likeComment(@PathVariable("commentId") long commentId,Principal principal) {
		this.questionServiceImpl.likeComment(commentId, principal.getName());
		return ResponseEntity.ok(new StandardMessage("success","comment liked"));
	}
	
	@GetMapping("/dislike/comment/{commentId}")
	public ResponseEntity<Object> dislikeComment(@PathVariable("commentId") long commentId,Principal principal) {
		this.questionServiceImpl.dislikeComment(commentId, principal.getName());
		return ResponseEntity.ok(new StandardMessage("success","comment disliked"));
	}
	
}
