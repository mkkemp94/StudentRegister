package com.mkemp.studentregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mkemp.studentregister.databinding.ActivityAddNewStudentBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AddNewStudentActivity extends AppCompatActivity
{
    private ActivityAddNewStudentBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);
    
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                submitStudent(
                        binding.etName.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etCountry.getText().toString()
                );
            }
        });
    }
    
    private void submitStudent(String name, String email, String country)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_NAME_EXTRA, name);
        returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_EMAIL_EXTRA, email);
        returnIntent.putExtra(MainActivity.ADD_NEW_STUDENT_COUNTRY_EXTRA, country);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}