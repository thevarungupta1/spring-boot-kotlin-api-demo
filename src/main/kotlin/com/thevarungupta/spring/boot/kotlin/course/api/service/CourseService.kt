package com.thevarungupta.spring.boot.kotlin.course.api.service

import com.thevarungupta.spring.boot.kotlin.course.api.entity.Course
import com.thevarungupta.spring.boot.kotlin.course.api.payload.CourseDTO
import com.thevarungupta.spring.boot.kotlin.course.api.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository
) {

    // add course
    fun addCourse(courseDTO: CourseDTO): CourseDTO{
        //var courseDTO = CourseDTO()
//        val courseEntity = courseDTO.let {
//            Course(null, courseDTO.name, courseDTO.category)
//        }
        val courseEntity = Course(null, courseDTO.name, courseDTO.category)
        courseRepository.save(courseEntity);
        return CourseDTO(courseEntity.id, courseEntity.name, courseEntity.category)
        return courseEntity.let{
            CourseDTO(it.id, it.name, it.category)
        }
    }

    // get all course
    fun getAllCourses(courseName: String?): List<CourseDTO>{
        val courses = courseName?.let {
            courseRepository.findCoursesByName(courseName)
        } ?: courseRepository.findAll()

        return courses
            .map{
                CourseDTO(it.id, it.name, it.category)
            }
    }

    // update
    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO{
        val existingCourse = courseRepository.findById(courseId);

        return if(existingCourse.isPresent){
            existingCourse.get()
                .let{
                    it.name = courseDTO.name
                    it.category = courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(it.id, it.name, it.category)
                }
        }else{
            throw RuntimeException("no course found")
        }
    }

    // delete
    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId);

        return if(existingCourse.isPresent){
            existingCourse.get()
                .let{
                    courseRepository.deleteById(courseId)
                }
        }else{
            throw RuntimeException("no course found")
        }
    }



}