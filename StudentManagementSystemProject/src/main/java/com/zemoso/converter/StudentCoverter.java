package com.zemoso.converter;

import com.zemoso.dto.StudentDto;
import com.zemoso.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCoverter {

    public StudentDto entityToDto(Student student){
        StudentDto dto=new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setCourses(student.getCourses());

        return dto;
    }

    public Student dtoToEntity(StudentDto studentDto){
        Student student=new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setCourses(studentDto.getCourses());

        return student;
    }

    public List<StudentDto> entityToDto(List<Student> student){

       return student.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

    public List<Student> dotToEntity(List<StudentDto> studentDtos){

        return studentDtos.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
