package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.entity.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    private Firestore firestore;
    private final String collection = "courses";

    public CourseRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Course save(Course course) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collection)
                .document(course.getCourseId() == null ? firestore.collection(collection).document().getId() : course.getCourseId());
        course.setCourseId(docRef.getId());
        docRef.set(course).get();
        return course;
    }
    public Course findById(String courseId) throws ExecutionException, InterruptedException{
        DocumentSnapshot document = firestore.collection(collection)
                .document(courseId).get().get();

        if(document.exists()){
            return document.toObject(Course.class);
        }
        return null;
    }

    public List<Course> findAll() throws ExecutionException, InterruptedException{
        QuerySnapshot querySnapshot = firestore.collection(collection).get().get();
        return querySnapshot.getDocuments().stream()
                .map(document -> document.toObject(Course.class))
                .collect(Collectors.toList());
    }

    public Course update(Course course) throws ExecutionException,InterruptedException{
        DocumentReference docRef = firestore.collection(collection).document(course.getCourseId());
        DocumentSnapshot document = docRef.get().get();
        if (!document.exists()) {
            return null;
        }
        return document.toObject(Course.class);
    }



public void deleteById(String courseId) throws ExecutionException,InterruptedException{
    firestore.collection(collection).document(courseId).delete();

}

}
