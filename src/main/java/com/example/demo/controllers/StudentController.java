package com.example.demo.controllers;

import com.example.demo.DTOs.StudentDTO;
import com.example.demo.models.Student;
import com.example.demo.responses.ApiResponse;
import com.example.demo.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> index() {
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getStudentsToPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getStudents(pageable);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Sắp xếp theo trang thành công")
                .data(studentPage.getContent())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> index(@PathVariable(value = "id" ) Long id) {
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody StudentDTO studentDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }
        Student student = studentService.saveStudent(studentDTO);
        return ResponseEntity.ok( "Đã thêm dữ liệu thành công: " + studentDTO.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok( "Dữ liệu với id: " + id + " đã được xóa thành công");
    }

    @GetMapping("/search1")
    public ResponseEntity<List<Student>> search1 (@RequestParam String name) {
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @GetMapping("/search2")
    public ResponseEntity<List<Student>> search2(@RequestParam String name) {
        return ResponseEntity.ok(studentService.findByThanhPho(name));
    }

    @GetMapping("/search3")
    public ResponseEntity<List<Student>> search3(@RequestParam String name) {
        return ResponseEntity.ok(studentService.findByThanhPhoAndTen(name));
    }

}
