package com.example.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 50, message = "Tên phải có từ 2 đến 50 ký tự")
    private String ten;

    @NotBlank(message = "Thành phố không được để trống")
    private String thanhPho;

    @Past(message = "Ngày tháng năm sinh phải là một ngày trong quá khứ")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    @NotNull(message = "Xếp loại không được để trống")
    private String xepLoai; // Thêm thuộc tính xếp loại
}
