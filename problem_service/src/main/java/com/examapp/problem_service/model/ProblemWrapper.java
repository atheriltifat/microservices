package com.examapp.problem_service.model;

public class ProblemWrapper {
	
	private Integer id;
	private String problemTitle;
	private String option1;
	private String option2;
	
	public ProblemWrapper() {
		
	}
	
	public ProblemWrapper(Integer id, String problemTitle, String option1, String option2) {
		this.id = id;
		this.problemTitle = problemTitle;
		this.option1 = option1;
		this.option2 = option2;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}
	
	

}
