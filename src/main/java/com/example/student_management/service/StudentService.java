package com.example.student_management.service;

import com.example.student_management.dto.StudentDTO;
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

    // Add Student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get All Students
    public List<StudentDTO> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> new StudentDTO(
                        student.getName(),
                        student.getEmail(),
                        student.getCourse(),
                        student.getAge()
                ))
                .toList();
    }

    // Update Student
    public Student updateStudent(Long id, Student newStudent) {

        Student oldStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        oldStudent.setName(newStudent.getName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setCourse(newStudent.getCourse());

        if (newStudent.getPassword() != null &&
                !newStudent.getPassword().isEmpty()) {
            oldStudent.setPassword(newStudent.getPassword());
        }

        return studentRepository.save(oldStudent);
    }

    // Delete Student
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}