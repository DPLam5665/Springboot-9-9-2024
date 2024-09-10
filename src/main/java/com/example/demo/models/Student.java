package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@Builder
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên không được trống nha")
    private String ten;

    @NotBlank(message = "Thành phố không được trống")
    private String thanhPho;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Phải là 1 ngày trong quá khứ")
    private LocalDate ngaySinh;

    @NotNull(message = "Xếp loại không được trống")
    @Enumerated(EnumType.STRING)
    private XepLoai xepLoai;
}