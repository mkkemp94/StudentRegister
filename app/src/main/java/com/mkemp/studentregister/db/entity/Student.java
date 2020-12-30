package com.mkemp.studentregister.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    private long id;

    @ColumnInfo(name = "student_name")
    private String name;
    
    @ColumnInfo(name = "student_email")
    private String email;
    
    @ColumnInfo(name = "student_country")
    private String country;
    
    @ColumnInfo(name = "student_registration_time")
    private String registrationTime;
    
    public Student(long id, String name, String email, String country, String registrationTime)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.registrationTime = registrationTime;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public String getRegistrationTime()
    {
        return registrationTime;
    }
    
    public void setRegistrationTime(String registrationTime)
    {
        this.registrationTime = registrationTime;
    }
}
