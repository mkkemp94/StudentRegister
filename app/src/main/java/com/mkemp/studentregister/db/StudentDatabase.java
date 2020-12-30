package com.mkemp.studentregister.db;

import com.mkemp.studentregister.db.entity.Student;
import com.mkemp.studentregister.db.entity.StudentDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        Student.class
}, version = 1)
public abstract class StudentDatabase extends RoomDatabase
{
    public abstract StudentDao getStudentDao();
}
