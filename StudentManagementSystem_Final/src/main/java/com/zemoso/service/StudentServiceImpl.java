package com.zemoso.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.zemoso.DemoApplication;
import com.zemoso.converter.StudentCoverter;
import com.zemoso.dao.StudentRepository;
import com.zemoso.dto.StudentDto;
import com.zemoso.entity.Student;
import com.zemoso.exceptionhandler.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	private static Logger myLogger=Logger.getLogger(DemoApplication.class.getName());

	private StudentRepository studentRepository;

	@Autowired
	public StudentCoverter studentCoverter;
	
	@Autowired
	public StudentServiceImpl(StudentRepository theStudentRepository) {
		studentRepository = theStudentRepository;
	}
	
	@Override
	public List<StudentDto> findAll() {
		List<Student> students=studentRepository.findAllByOrderByLastNameAsc();
		return studentCoverter.entityToDto(students);
	}

	@Override
	public StudentDto findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		
		Student theStudent = null;
		
		if (result.isPresent()) {
			theStudent = result.get();
		}
		else {
			throw new StudentNotFoundException("Did not find student id - " + theId);
		}
		
		return studentCoverter.entityToDto(theStudent);
	}

	@Override
	public void save(StudentDto studentDto) {
		Student student=studentCoverter.dtoToEntity(studentDto);
		studentRepository.save(student);
	}

	@Override
	public void deleteById(int theId) {
		Optional<Student> tempStudent=studentRepository.findById(theId);
		if(!(tempStudent.isPresent())){
			throw new StudentNotFoundException("Did not find student id -"+theId);
		}
		studentRepository.deleteById(theId);
	}

}






