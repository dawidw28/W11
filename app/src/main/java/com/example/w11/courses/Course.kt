package com.example.w11.courses

import java.io.Serializable

class Course(var picture: Int, var name: String, var semester: Int, var ects: Int, var teacher: String, var description: String) : Serializable {
}