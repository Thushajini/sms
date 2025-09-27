package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.entity.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private Firestore firestore;
    private  final String collection = "students";

    public StudentRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Student save(Student student) throws ExecutionException, InterruptedException{
        DocumentReference docRef = firestore.collection(collection)
                .document(student.getStudentId() == null ? firestore.collection(collection).document().getId() : student.getStudentId());
                student.setStudentId(docRef.getId());
                docRef.set(student).get();
                 return student;

    }

    public Student findById(String studentId) throws ExecutionException, InterruptedException{
        DocumentSnapshot document = firestore.collection(collection)
                .document(studentId).get().get();
        if(document.exists()){
            return document.toObject(Student.class);
        }
        return null;
    }

    public List<Student> findAll() throws ExecutionException, InterruptedException{
        QuerySnapshot querySnapshot = firestore.collection(collection).get().get();
       return querySnapshot.getDocuments().stream()
               .map(document -> document.toObject(Student.class))
               .collect(Collectors.toList());
    }

    public Student update(Student student) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collection).document(student.getStudentId());
        DocumentSnapshot document = docRef.get().get();
        if (!document.exists()) {
            return null;
        }
        return document.toObject(Student.class);
    }

    public void deleteById(String studentId) throws ExecutionException,InterruptedException {
        firestore.collection(collection).document(studentId).delete();
    }

}
