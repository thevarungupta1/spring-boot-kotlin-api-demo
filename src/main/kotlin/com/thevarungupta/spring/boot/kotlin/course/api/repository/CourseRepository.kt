package com.thevarungupta.spring.boot.kotlin.course.api.repository

import com.thevarungupta.spring.boot.kotlin.course.api.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<Course, Int> {

    fun findByNameContaining(courseName: String): List<Course>

    @Query(value = "select * from courses where name like %?1%",
    nativeQuery = true)
    fun findCoursesByName(courseName: String): List<Course>
}