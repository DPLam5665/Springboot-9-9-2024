package com.example.demo.services;

import com.example.demo.DTOs.StudentDTO;
import com.example.demo.models.Student;
import com.example.demo.models.XepLoai;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Không tìm thấy"));
    }

//    @Override
//    public Page<Student> getStudents(org.springframework.data.domain.Pageable pageable) {
//        return null;
//    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

//    @Override
//    public Student getStudentsByID(Long id) {
//        return studentRepository.findById(id).orElse(null);
//    }

    @Override
    public Student saveStudent(StudentDTO studentDTO) {
        Student student = Student.builder()
                .ten(studentDTO.getTen())
                .thanhPho(studentDTO.getThanhPho())
                .ngaySinh(studentDTO.getNgaySinh())
                .xepLoai(XepLoai.fromTen(studentDTO.getXepLoai()))
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student studentExisting = getStudentById(id);
        studentExisting.setTen(studentDTO.getTen());
        studentExisting.setThanhPho(studentDTO.getThanhPho());
        studentExisting.setXepLoai(XepLoai.fromTen(studentDTO.getXepLoai()));
        studentExisting.setNgaySinh(studentDTO.getNgaySinh());
        return studentRepository.save(studentExisting);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByTenContainingIgnoreCase(name);
    }

    @Override
    public List<Student> findByThanhPho(String name) {
        return studentRepository.findByThanhPho(name);
    }

    @Override
    public List<Student> findByThanhPhoAndTen(String name) {
        return List.of();
    }

}