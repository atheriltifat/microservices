package com.examapp.problem_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examapp.problem_service.model.Problem;
import com.examapp.problem_service.model.ProblemWrapper;
import com.examapp.problem_service.model.Response;
import com.examapp.problem_service.service.ProblemService;


@RestController
@RequestMapping("problem")
public class ProblemController {
	
	@Autowired
	ProblemService problemService;
	
	@GetMapping("allProblems")
	public ResponseEntity<List<Problem>> getAllProblems(){
		return problemService.getAllProblems();
		
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Problem>> getProblemsByCategory(@PathVariable String category){
		return problemService.getProblemsByCategory(category);
		
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addProblem(@RequestBody Problem problem){
		return problemService.addProblem(problem);
		
	}
	
	@GetMapping("getProblemsIDForExam")
	public ResponseEntity<List<Integer>> getProblemsIDForExam(@RequestParam String categoryName, @RequestParam Integer numProblems){
		return problemService.getProblemsIDForExam(categoryName, numProblems);
		
	}
	
	@PostMapping("getProblems")
	public ResponseEntity<List<ProblemWrapper>> getProblemsFromId(@RequestBody List<Integer> problemIds){
		return problemService.getProblemsFromId(problemIds);
		
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return problemService.getScore(responses);
		
	}

}
