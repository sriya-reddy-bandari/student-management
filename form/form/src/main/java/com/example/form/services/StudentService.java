package com.example.form.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.model.Address;
import com.example.form.model.Student;
import com.example.form.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {

        if (student.getLaptop() != null) {
            student.getLaptop().setStudent(student);
        }

        if (student.getAddressList() != null) {
            for (Address address : student.getAddressList()) {
                address.setStudent(student);
            }
        }

        return studentRepository.save(student);

    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

}
