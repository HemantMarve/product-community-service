package com.product.community.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.community.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
