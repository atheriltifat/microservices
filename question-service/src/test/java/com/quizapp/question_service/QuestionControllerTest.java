package com.quizapp.question_service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quizapp.question_service.controller.QuestionController;
import com.quizapp.question_service.model.Question;
import com.quizapp.question_service.service.QuestionService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	
	@Mock
	private QuestionService questionService;
	
	@InjectMocks
	private QuestionController questionController;
	
	Question obj_1;

	@Before
	public void setup(){
		obj_1 = new Question();
		obj_1.setId(1);
		obj_1.setQuestionTitle("test question to ask");
		obj_1.setOption1("testOption1");
		obj_1.setOption2("testOption2");
		obj_1.setOption3("testOption3");
		obj_1.setRightAnswer("2");
		obj_1.setDifficultyLevel("Easy");
		obj_1.setCategory("Java");
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
	}
	
	@Test
	public void getAllQuestions_success() {
		try {
			List<Question> questionList = new ArrayList<>(Arrays.asList(obj_1));
			ResponseEntity<List<Question>> response = ResponseEntity.ok(questionList);
			Mockito.when(questionService.getAllQuestions()).thenReturn(response);
			mockMvc.perform(MockMvcRequestBuilders
					.get("/question/allQuestions")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].questionTitle", is("test question to ask")));
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	} 

}
