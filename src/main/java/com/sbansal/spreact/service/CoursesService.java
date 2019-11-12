package com.sbansal.spreact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and Angular"));
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and React"));
        courses.add(new Course(++idCounter, "sbansal", "Master Microservices with Spring Boot and Spring Cloud"));
        courses.add(
            new Course(
                ++idCounter, "sbansal",
                "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and Angular"));
        courses.add(new Course(++idCounter, "sbansal", "Learn Full stack with Spring Boot and React"));
        courses.add(new Course(++idCounter, "sbansal", "Master Microservices with Spring Boot and Spring Cloud"));
        courses.add(
            new Course(
                ++idCounter, "sbansal",
                "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
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


    public List<Course> find(String username, Integer offset, Integer count)
    {
        if (offset == null || offset == 0)
            return courses.stream().filter(obj -> obj.getUsername().equals(username)).collect(Collectors.toList());

        int startIdx = 0;
        int endIdx = count * offset + count;
        return getSliceOfStream(username, startIdx, endIdx);
    }


    public List<Course> getSliceOfStream(String username, int startIndex, int endIndex)
    {
        return courses
            .stream()
            // specify the no. of elements the stream 
            // that should be limited
            .limit(endIndex).collect(Collectors.toList());
    }
}
