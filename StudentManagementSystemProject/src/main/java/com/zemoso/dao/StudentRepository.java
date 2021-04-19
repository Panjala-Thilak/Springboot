package com.zemoso.dao;

import java.util.List;

import com.zemoso.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zemoso.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findAllByOrderByLastNameAsc();
}
