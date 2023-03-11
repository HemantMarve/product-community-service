package com.product.community.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.community.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

	@Query(value = "Select q from Question q where q.content like CONCAT('%',:text,'%') OR q.postedOn like CONCAT('',:text,' %') OR q.productCode like CONCAT('',:text,'')"
			+ " OR q.productType like CONCAT('%',:text,'%') OR q.subject like CONCAT('%',:text,'%')")
			List<Question> searchQuestions(
			  @Param("text") String text); 
	
	@Query(value="select count(*) from question_info where question_closed<>0",nativeQuery=true)
	 long countClosedQuestions();
}
