package com.zemoso.service;

import com.zemoso.dao.CourseRepository;
import com.zemoso.entity.Course;
import com.zemoso.entity.Student;
import com.zemoso.exceptionhandler.CourseNotFoundException;
import com.zemoso.exceptionhandler.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        courseRepository.save(course);
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
