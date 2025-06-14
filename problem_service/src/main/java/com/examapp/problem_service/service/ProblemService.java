package com.examapp.problem_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.examapp.problem_service.dao.ProblemDao;
import com.examapp.problem_service.model.Problem;
import com.examapp.problem_service.model.ProblemWrapper;
import com.examapp.problem_service.model.Response;

@Service
public class ProblemService {
	
	@Autowired
	ProblemDao problemDao;
	
	public ResponseEntity<List<Problem>> getAllProblems(){
		try {
			return new ResponseEntity<>(problemDao.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	

	public ResponseEntity<List<Problem>> getProblemsByCategory(String category) {	
		try {
			return new ResponseEntity<>(problemDao.findByCategory(category), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	

	public ResponseEntity<String> addProblem(Problem problem) {
		problemDao.save(problem);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	

	public ResponseEntity<List<Integer>> getProblemsIDForExam(String categoryName, Integer numProblems) {
		List<Integer> questions = problemDao.findRandomProblemsByCategory(categoryName, numProblems);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	

	public ResponseEntity<List<ProblemWrapper>> getProblemsFromId(List<Integer> questionIds) {
		List<ProblemWrapper> wrappers = new ArrayList<>();
		List<Problem> problems = new ArrayList<>();
		
		for(Integer id: questionIds) {
			problems.add(problemDao.findById(id).get());
		}
		
		for(Problem problem: problems) {
			ProblemWrapper wrapper = new ProblemWrapper();
			wrapper.setId(problem.getId());
			wrapper.setProblemTitle(problem.getProblemTitle());
			wrapper.setOption1(problem.getOption1());
			wrapper.setOption2(problem.getOption2());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}


	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		for(Response response : responses) {
			Problem problem = problemDao.findById(response.getId()).get();
			if(response.getResponse().equals(problem.getRightAnswer())) {
				right++;
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
