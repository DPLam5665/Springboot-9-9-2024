package com.example.demo.models;
public enum XepLoai {
    Gioi("Giỏi"), KHA("Khá"), TRUNG_BINH("Trung bình"), YEU("Yếu");
    private String ten;

    XepLoai(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public static XepLoai fromTen(String ten) {
        for (XepLoai x : XepLoai.values()) {
            if (x.getTen().equals(ten)) {
                return x;
            }
        }
        throw new IllegalArgumentException(ten);

    }
}