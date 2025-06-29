package com.examapp.exam_service.exam_service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.examapp.exam_service.exam_service.dao.ExamDao;
import com.examapp.exam_service.exam_service.feign.ExamInterface;
import com.examapp.exam_service.exam_service.model.Exam;
import com.examapp.exam_service.exam_service.model.ProblemWrapper;
import com.examapp.exam_service.exam_service.model.Response;



@Service
public class ExamService {
	
	@Autowired
	ExamDao examDao;
	
	@Autowired
	ExamInterface examInterface;
	

	public ResponseEntity<String> createExam(String category, int num, String title){
		List<Integer> problems = examInterface.getProblemsIDForExam(category, num).getBody();
		Exam exam = new Exam();
		exam.setTitle(title);
		exam.setProblemIds(problems);
		examDao.save(exam);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<List<ProblemWrapper>> getProblemsByExamId(Integer id){	
		Exam exam = examDao.findById(id).get();
		List<Integer> problemIds = exam.getProblemIds();
		ResponseEntity<List<ProblemWrapper>> problems = examInterface.getProblemsFromId(problemIds);
		return problems;
	}
	

	public ResponseEntity<Integer> calculateResult(List<Response> responses) {
		ResponseEntity<Integer> score = examInterface.getScore(responses);
		return score;
	}

}
