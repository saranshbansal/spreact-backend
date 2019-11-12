package com.sbansal.spreact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbansal.spreact.model.Course;
import com.sbansal.spreact.service.CoursesService;

@RestController
public class CourseController
{

    @Autowired
    private CoursesService courseManagementService;


    @GetMapping("/")
    public String init()
    {
        return "hello!";
    }


    @GetMapping("/instructors/{username}/courses")
    public List<Course> getAllCourses(@PathVariable String username, @RequestParam(required=false) Integer offset, @RequestParam(required=false) Integer count)
    {
        return courseManagementService.find(username, offset, count);
    }
}
