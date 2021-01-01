package com.mkemp.studentregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mkemp.studentregister.databinding.ActivityAddNewStudentBinding;
import com.mkemp.studentregister.db.entity.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AddNewStudentActivity extends AppCompatActivity
{
    private ActivityAddNewStudentBinding binding;
    private Student student;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);
        
        AddNewStudentActivityClickHandlers handlers = new AddNewStudentActivityClickHandlers(this);
        student = new Student();
        
        binding.setOnClickHandlers(handlers);
        binding.setStudent(student);
    }
    
    public class AddNewStudentActivityClickHandlers
    {
        Context context;
        
        public AddNewStudentActivityClickHandlers(Context context)
        {
            this.context = context;
        }
        
        public void onSubmitClicked(View view)
        {
            if ( student.getName() == null || student.getName().isEmpty() )
            {
                Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_NAME_EXTRA, student.getName());
                returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_EMAIL_EXTRA, student.getEmail());
                returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_COUNTRY_EXTRA, student.getCountry());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}