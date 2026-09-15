package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student newStudent) {

        Student oldStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        oldStudent.setName(newStudent.getName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setCourse(newStudent.getCourse());

        return studentRepository.save(oldStudent);
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}