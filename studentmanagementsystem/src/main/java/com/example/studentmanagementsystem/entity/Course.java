package com.example.studentmanagementsystem.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

@Data
public class Course {
    @DocumentId
    private String courseId;
    private String courseName;
    private double fee;
    private String lectureId;
    private String lecturerName;
}
