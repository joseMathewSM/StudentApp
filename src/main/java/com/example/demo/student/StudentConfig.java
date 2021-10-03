package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jose = new Student("jose", LocalDate.of(1995, Month.AUGUST, 13),"jm@gamil.com");
            Student mathew = new Student("mathew", LocalDate.of(1995, Month.AUGUST, 13),"mat@gamil.com");

            List<Student> myList = new ArrayList<Student >();
            myList.add(jose);
            myList.add(mathew);

            repository.saveAll(myList);
        };
    }
}
