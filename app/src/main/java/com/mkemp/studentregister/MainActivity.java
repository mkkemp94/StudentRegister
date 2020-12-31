package com.mkemp.studentregister;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mkemp.studentregister.adapter.StudentAdapter;
import com.mkemp.studentregister.databinding.ActivityMainBinding;
import com.mkemp.studentregister.db.StudentDatabase;
import com.mkemp.studentregister.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding activityMainBinding;
    public MainActivityClickHandlers handlers;
    
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
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        setSupportActionBar(activityMainBinding.toolbar);
        handlers = new MainActivityClickHandlers(this);
        activityMainBinding.setOnClickHandlers(handlers);
    
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
    
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }
    
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                Student studentToDelete = studentArrayList.get((viewHolder.getAdapterPosition()));
                deleteStudent(studentToDelete);
            }
        }).attachToRecyclerView(recyclerViewStudents);
    }
    
    public class MainActivityClickHandlers
    {
        Context context;
    
        public MainActivityClickHandlers(Context context)
        {
            this.context = context;
        }
    
        public void onFloatingActionButtonClicked(View view)
        {
            Intent intent = new Intent(context, AddNewStudentActivity.class);
            startActivityForResult(intent, ADD_NEW_STUDENT_REQUEST_CODE);
        }
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
    
    private void deleteStudent(Student student)
    {
        new DeleteStudentFromDBTask().execute(student);
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