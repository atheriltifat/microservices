package com.examapp.exam_service.exam_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examapp.exam_service.exam_service.model.ExamDto;
import com.examapp.exam_service.exam_service.model.ProblemWrapper;
import com.examapp.exam_service.exam_service.model.Response;
import com.examapp.exam_service.exam_service.service.ExamService;


@RestController
@RequestMapping("exam")
public class ExamController {
	
	@Autowired 
	ExamService examService;
	
	@PostMapping("create")
	public ResponseEntity<String> createExam(@RequestBody ExamDto examDto){
		return examService.createExam(examDto.getCategoryName(), examDto.getNumQuestions(), examDto.getTitle());
		
	}
	
	@GetMapping("getProblemsByExamId/{id}")
	public ResponseEntity<List<ProblemWrapper>> getProblemsByExamId(@PathVariable Integer id){
		return examService.getProblemsByExamId(id);
		
	}
	
	@PostMapping("submitResponse")
	public ResponseEntity<Integer> submitExam(@RequestBody List<Response> responses){
		return examService.calculateResult(responses);
		
	}

}
