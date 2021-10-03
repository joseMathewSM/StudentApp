package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with ID " + studentId + " does not exist.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.getById(studentId);
        if(student == null){
            throw new IllegalStateException("Student Does not Exist");
        }

        if(name!=null && name.length()>0) {
            student.setName(name);
        }

        if(email!=null && email.length()>0) {
            Optional<Student> eStudent = studentRepository.findStudentByEmail(email);
            if(eStudent.isPresent()){
                throw new IllegalStateException("email Taken");
            }
            student.setEmail(email);
        }
    }
}