package com.zemoso.exceptionhandler;

public class CourseAlreadyExist extends RuntimeException {
    public CourseAlreadyExist(String message) {
        super(message);
    }

    public CourseAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseAlreadyExist(Throwable cause) {
        super(cause);
    }
}
