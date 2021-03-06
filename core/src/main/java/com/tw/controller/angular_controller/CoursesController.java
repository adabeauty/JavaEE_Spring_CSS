package com.tw.controller.angular_controller;

import com.tw.entity.Course;
import com.tw.service.CourseService;
import com.tw.service.EmployeeService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hgwang on 7/27/15.
 */
@RestController
@RequestMapping(value = "/courses")
public class CoursesController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmployeeService employeeService;

    private JSONSerializer jsonSerializer= new JSONSerializer();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String getAllCourses(){
        return jsonSerializer.include("employee").serialize(courseService.getAllCourses());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    String createCourse(@RequestParam String courseName,
                               @RequestParam String coachName){

        List<Course> courses = courseService.getAllCourses();
        for(int i=0; i<courses.size(); i++){
            Course course = courses.get(i);

            if(course.getName().equals(courseName) && (! course.getName().equals("private"))){
                return jsonSerializer.serialize("the course has existed");
            }
            if(course.getName().equals(courseName) && courseName.equals("private")  && course.getEmployee().getName().equals(coachName)){
                return jsonSerializer.serialize("the course has existed");
            }
        }

        Course course = new Course(courseName, employeeService.getEmployeeByName(coachName));
        courseService.createCourse(course);

        return jsonSerializer.serialize("the course has not existed");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCourseById(@PathVariable int id){
        courseService.deleteCourse(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String getCourse(@PathVariable int id){
        return jsonSerializer.include("employee").serialize(courseService.getCourseById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void UpdateCourse(@PathVariable int id,
                               @RequestParam String courseName,
                               @RequestParam String coachName){

        Course course = new Course(id, courseName, employeeService.getEmployeeByName(coachName));
        courseService.updateCourse(course);
    }
}
