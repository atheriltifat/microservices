package com.examapp.problem_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.examapp.problem_service.model.Problem;


public interface ProblemDao extends JpaRepository<Problem, Integer> {
	
	List<Problem> findByCategory(String category);
	
	@Query(value = "SELECT p.id FROM problem_table p Where p.category=:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
	List<Integer> findRandomProblemsByCategory(String category, int num);

}
