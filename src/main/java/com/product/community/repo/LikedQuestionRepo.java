package com.product.community.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.community.model.LikedQuestion;
import com.product.community.model.Question;
import com.product.community.model.User;


@Repository
public interface LikedQuestionRepo extends JpaRepository<LikedQuestion, Long>{

	@Transactional
	long removeByUserAndQuestion(User user,Question question);
}
