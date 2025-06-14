package com.examapp.exam_service.exam_service.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String title;
	
	@ElementCollection
	private List<Integer> problemIds;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getProblemIds() {
		return problemIds;
	}
	public void setProblemIds(List<Integer> problemIds) {
		this.problemIds = problemIds;
	}

	
}
