package com.sbansal.spreact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbansal.spreact.model.Course;

@Service
public class CoursesService
{

    private static List<Course> courses = new ArrayList<>();
    private static long idCounter = 0;

    static
    {
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and Angular"));
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and React"));
        courses.add(new Course(++idCounter, "sbansal", "Master Microservices with Spring Boot and Spring Cloud"));
        courses.add(
            new Course(
                ++idCounter, "sbansal",
                "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
    }


    public List<Course> findAll()
    {
        return courses;
    }
}
