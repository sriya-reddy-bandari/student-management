package com.example.form.Schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.form.model.Student;

import com.example.form.repository.StudentRepository;

@Service
public class StudentSchedulers {

	@Autowired
	StudentRepository studentrepo;
    
	
	@Scheduled(cron = "0/10 * * * * ?") 
	public void processStudentLaptops() {
	    System.out.println("Processing Students");
	    List<Student> students = studentrepo.findByStatus("new");
	    students.forEach(student ->{
	    	 System.out.println("Student Name: " + student.getStudentName());
	    	 student.setStatus("admitted");
	    	 studentrepo.save(student);
	    	 System.out.println("Processing completed");
	    });
	       
	  
	        
	    }
	}

