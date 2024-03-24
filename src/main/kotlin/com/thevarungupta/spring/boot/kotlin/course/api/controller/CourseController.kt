package com.thevarungupta.spring.boot.kotlin.course.api.controller

import com.thevarungupta.spring.boot.kotlin.course.api.payload.CourseDTO
import com.thevarungupta.spring.boot.kotlin.course.api.service.CourseService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/courses")
@RestController
class CourseController(
    val courseService: CourseService
) {

    @PostMapping
    fun addCourse(@RequestBody courseDTO: CourseDTO): CourseDTO{
        return courseService.addCourse(courseDTO)
    }

    // http://localhost:9000/api/courses/java - route parameter
    // http://localhost:9000/api/courses?course_name=java - query string
    @GetMapping
    fun getCourses(@RequestParam("course_name", required = false) courseName: String?): List<CourseDTO>
    = courseService.getAllCourses(courseName)


    @PutMapping("/{courseId}")
    fun updateCourse(@RequestBody courseDTO: CourseDTO, @PathVariable("courseId") courseId: Int): CourseDTO
    = courseService.updateCourse(courseId, courseDTO)


    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable("courseId") courseId: Int)
     = courseService.deleteCourse(courseId)


}