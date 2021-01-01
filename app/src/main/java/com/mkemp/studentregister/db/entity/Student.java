package com.mkemp.studentregister.db.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student extends BaseObservable
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
    
    public Student()
    {
    
    }
    
    @Bindable
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
  
    @Bindable
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
  
    @Bindable
    public String getCountry()
    {
        return country;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }
    
    @Bindable
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    
    @Bindable
    public String getRegistrationTime()
    {
        return registrationTime;
    }
    
    public void setRegistrationTime(String registrationTime)
    {
        this.registrationTime = registrationTime;
        notifyPropertyChanged(BR.registrationTime);
    }
}
