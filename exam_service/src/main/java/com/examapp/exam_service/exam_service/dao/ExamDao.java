package com.examapp.exam_service.exam_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examapp.exam_service.exam_service.model.Exam;


public interface ExamDao extends JpaRepository<Exam, Integer> {

}
