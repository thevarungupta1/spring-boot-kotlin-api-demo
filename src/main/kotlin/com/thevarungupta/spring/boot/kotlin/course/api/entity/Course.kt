package com.thevarungupta.spring.boot.kotlin.course.api.entity

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int? = null,
    var name: String? = null,
    var category: String? = null
)