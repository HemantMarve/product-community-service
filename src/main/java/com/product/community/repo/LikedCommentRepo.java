package com.product.community.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.community.model.Comment;
import com.product.community.model.LikedComment;
import com.product.community.model.User;

@Repository
public interface LikedCommentRepo extends JpaRepository<LikedComment, Long> {
	
	@Transactional
	long removeByUserAndComment(User user,Comment comment);
}
