package com.mkemp.studentregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewStudentActivity extends AppCompatActivity
{
    private EditText editTextStudentName;
    private EditText editTextStudentEmail;
    private EditText editTextStudentCountry;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
    
        editTextStudentName = findViewById(R.id.et_name);
        editTextStudentEmail = findViewById(R.id.et_email);
        editTextStudentCountry = findViewById(R.id.et_country);
        Button buttonSubmit = findViewById(R.id.btnSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                submitStudent(
                        editTextStudentName.getText().toString(),
                        editTextStudentEmail.getText().toString(),
                        editTextStudentCountry.getText().toString()
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