package com.mkemp.studentregister;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mkemp.studentregister.adapter.StudentAdapter;
import com.mkemp.studentregister.db.StudentDatabase;
import com.mkemp.studentregister.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    
    public static final int ADD_NEW_STUDENT_REQUEST_CODE = 2457;
    public static final String ADD_NEW_STUDENT_NAME_EXTRA = "add_student_name_extra";
    public static final String ADD_NEW_STUDENT_EMAIL_EXTRA = "add_student_email_extra";
    public static final String ADD_NEW_STUDENT_COUNTRY_EXTRA = "add_student_country_extra";
    
    private StudentDatabase studentDatabase;
    private StudentAdapter studentAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToAddNewStudentActivity();
            }
        });
    
        studentDatabase = Room.databaseBuilder(
                getApplicationContext(), StudentDatabase.class,
                "StudentDB"
        ).build();
        
        RecyclerView recyclerViewStudents = findViewById(R.id.rvStudents);
    
        studentAdapter = new StudentAdapter();
        recyclerViewStudents.setAdapter(studentAdapter);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewStudents.setLayoutManager(layoutManager);
    
        loadData();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private void goToAddNewStudentActivity()
    {
        Intent intent = new Intent(this, AddNewStudentActivity.class);
        startActivityForResult(intent, ADD_NEW_STUDENT_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == ADD_NEW_STUDENT_REQUEST_CODE)
        {
            String studentName = data.getStringExtra(ADD_NEW_STUDENT_NAME_EXTRA);
            String studentEmail = data.getStringExtra(ADD_NEW_STUDENT_EMAIL_EXTRA);
            String studentCountry = data.getStringExtra(ADD_NEW_STUDENT_COUNTRY_EXTRA);
    
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
            String currentDateAndTime = simpleDateFormat.format(new Date());
    
            addStudent(studentName, studentEmail, studentCountry, currentDateAndTime);
        }
    }
    
    private void loadData()
    {
        new GetAllStudentsTask().execute();
    }
    
    private void addStudent(String name, String email, String country, String registrationTime)
    {
        new AddStudentToDBTask().execute(
                new Student(0, name, email, country, registrationTime)
        );
    }
    
    private class GetAllStudentsTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            studentArrayList = (ArrayList<Student>) studentDatabase.getStudentDao().getStudents();
            return null;
        }
    
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            studentAdapter.setStudents(studentArrayList);
        }
    }
    
    private class AddStudentToDBTask extends AsyncTask<Student, Void, Void>
    {
        @Override
        protected Void doInBackground(Student... students)
        {
            studentDatabase.getStudentDao().addStudent(students[0]);
            return null;
        }
    
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            loadData();
        }
    }
    
    private class DeleteStudentFromDBTask extends AsyncTask<Student, Void, Void>
    {
        @Override
        protected Void doInBackground(Student... students)
        {
            studentDatabase.getStudentDao().deleteStudent(students[0]);
            return null;
        }
        
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            loadData();
        }
    }
}