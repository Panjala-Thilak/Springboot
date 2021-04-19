package com.zemoso.service;

import com.zemoso.dao.CourseRepository;
import com.zemoso.entity.Course;
import com.zemoso.entity.Student;
import com.zemoso.exceptionhandler.CourseAlreadyExist;
import com.zemoso.exceptionhandler.CourseNotFoundException;
import com.zemoso.exceptionhandler.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);

        Course course = null;

        if (result.isPresent()) {
            course = result.get();
        }
        else {
            // we didn't find the employee
            throw new CourseNotFoundException("Did not find Course id - " + theId);
        }

        return course;
    }

    @Override
    public void save(Course course) {
        List<Course> courses=courseRepository.findAll();
        List<String> titles=new ArrayList<>();
        for(Course course1:courses)
        {
            titles.add(course1.getTitle().toUpperCase());
        }
        if(titles.contains(course.getTitle().toUpperCase()))
        {
            throw new CourseAlreadyExist("course already exist");
        }
        else {
            courseRepository.save(course);
        }
    }

    @Override
    public void deleteById(int theId) {
        Optional<Course> tempCourse=courseRepository.findById(theId);
        if(!(tempCourse.isPresent())){
            throw new CourseNotFoundException("Did not find Course id -"+theId);
        }
        courseRepository.deleteById(theId);
    }
}
