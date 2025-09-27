package com.example.studentmanagementsystem.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

import java.lang.annotation.Documented;
import java.util.List;

@Data
public class Student {
    @DocumentId
    private String studentId;
    private String title;
    private String name;
    private String address;
    private String city;
    private List<String> courseId;


}
