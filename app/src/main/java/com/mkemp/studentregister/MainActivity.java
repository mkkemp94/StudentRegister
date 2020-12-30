package com.mkemp.studentregister;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mkemp.studentregister.adapter.StudentAdapter;
import com.mkemp.studentregister.db.entity.Student;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    
        RecyclerView recyclerViewStudents = findViewById(R.id.rvStudents);
        
        studentArrayList.add(
                new Student(0, "Me", "Myself", "I")
        );
        
        StudentAdapter studentAdapter = new StudentAdapter(studentArrayList);
        recyclerViewStudents.setAdapter(studentAdapter);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewStudents.setLayoutManager(layoutManager);
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
}