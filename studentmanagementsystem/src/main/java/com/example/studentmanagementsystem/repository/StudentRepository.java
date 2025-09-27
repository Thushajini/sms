package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.entity.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class StudentRepository {
    private Firestore firestore;
    private  final String collection = "students";

    public StudentRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public String saveStudent(Student student) throws ExecutionException, InterruptedException{
        if(student.getStudentId() == null){
            student.setStudentId(firestore.collection(collection).document().getId());
        }
        ApiFuture<WriteResult> future = firestore.collection(collection).document(student.getStudentId()).set(student);
        return future.get().getUpdateTime().toString();
    }

    public Student getStudentById(String studentId) throws ExecutionException, InterruptedException{
        DocumentReference docRef = firestore.collection(collection).document(studentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if(document.exists()){
            return document.toObject(Student.class);
        }
        return null;
    }

    public List<Student> getAllStudents() throws ExecutionException, InterruptedException{
        List<Student> students = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = firestore.collection(collection).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document:documents){
            students.add(document.toObject(Student.class));
        }
        return students;
    }

    public String updateStudent(Student student) throws ExecutionException,InterruptedException{
        if(student.getStudentId() == null){
            throw new IllegalArgumentException("Student ID cannot be null for update");
        }
        ApiFuture<WriteResult> future = firestore.collection(collection)
                .document(student.getStudentId())
                .set(student, SetOptions.merge());
        return future.get().getUpdateTime().toString();
    }

    public String deleteStudentById(String studentId) throws ExecutionException,InterruptedException{
        ApiFuture<WriteResult> future = firestore.collection(collection).document(studentId).delete();
        return future.get().getUpdateTime().toString();
    }

}
