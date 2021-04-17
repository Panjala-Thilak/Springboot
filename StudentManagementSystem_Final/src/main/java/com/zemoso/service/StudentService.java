package com.zemoso.service;

import java.util.List;

import com.zemoso.dto.StudentDto;
import com.zemoso.entity.Student;

public interface StudentService {

	public List<StudentDto> findAll();
	
	public StudentDto findById(int theId);
	
	public void save(StudentDto theStudent);
	
	public void deleteById(int theId);
	
}
