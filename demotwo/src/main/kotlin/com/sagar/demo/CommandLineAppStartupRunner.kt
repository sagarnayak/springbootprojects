package com.sagar.demo

import com.sagar.demo.entities.Student
import com.sagar.demo.repositories.CourseRepository
import com.sagar.demo.repositories.PassportRepository
import com.sagar.demo.repositories.ReviewRepository
import com.sagar.demo.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort

@Configuration
class CommandLineAppStartupRunner : CommandLineRunner {

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var passportRepository: PassportRepository

    @Autowired
    private lateinit var reviewRepository: ReviewRepository

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        /* val course = Course(
                 name = "course one"
         )

         courseRepository.save(course)

         val student = Student(
                 first_name = "Sagar",
                 last_name = "nayak",
                 email = "Sagar@test.com",
                 gender = "male"
         )

         studentRepository.save(student)

         val passport = Passport(number = "passport001")

         passportRepository.save(passport)

         reviewRepository.save(
                 Review(
                         description = "awesome course"
                 )
         )
         reviewRepository.save(
                 Review(
                         description = "best course"
                 )
         )*/

        studentRepository.findById("8d8a1a30-ce35-4a8f-9656-1f1e8094064e").apply {
            if (!this.isPresent)
                return

            val student: Student = this.get()
            passportRepository.findById("492a018e-b2ec-44dd-9c9c-0d301fd36b52").apply {
                if (!this.isPresent)
                    return

                student.passport = this.get()
                studentRepository.save(student)
            }
        }
    }

    companion object {
        private val LOG: org.slf4j.Logger = LoggerFactory.getLogger(CommandLineAppStartupRunner::class.java)
        var counter = 0
    }
}