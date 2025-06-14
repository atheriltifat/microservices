package com.examapp.exam_service.exam_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.examapp.exam_service.exam_service.model.ProblemWrapper;
import com.examapp.exam_service.exam_service.model.Response;



@FeignClient("PROBLEM-SERVICE")
public interface ExamInterface {
	
	@GetMapping("problem/getProblemsIDForExam")
	public ResponseEntity<List<Integer>> getProblemsIDForExam(@RequestParam String categoryName, @RequestParam Integer numProblems);
	
	@PostMapping("problem/getProblems")
	public ResponseEntity<List<ProblemWrapper>> getProblemsFromId(@RequestBody List<Integer> problemIds);
	
	@PostMapping("problem/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
