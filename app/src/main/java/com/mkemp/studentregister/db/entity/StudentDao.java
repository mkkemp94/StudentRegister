package com.mkemp.studentregister.db.entity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao
{
    @Insert
    public long addStudent(Student student);
    
    @Update
    public void updateStudent(Student student);
    
    @Delete
    public void deleteStudent(Student student);
    
    @Query("SELECT * FROM students")
    public List<Student> getStudents();
    
    @Query("SELECT * FROM students WHERE student_id == :studentId")
    public Student getStudent(long studentId);
}
