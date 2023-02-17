package com.techiq.university_app.service;

import com.techiq.university_app.entity.Address;
import com.techiq.university_app.entity.Student;
import com.techiq.university_app.repository.AddressRepository;
import com.techiq.university_app.repository.StudentRepository;
import com.techiq.university_app.request.CreateStudentRequest;
import com.techiq.university_app.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setAddress(address);
        student = studentRepository.save(student);

        return new StudentResponse(student);
    }

    public StudentResponse getById (long id) {
        return new StudentResponse(studentRepository.findById(id).get());
    }
}
