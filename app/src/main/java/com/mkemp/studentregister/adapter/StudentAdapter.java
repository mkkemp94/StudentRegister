package com.mkemp.studentregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkemp.studentregister.R;
import com.mkemp.studentregister.db.entity.Student;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>
{
    private ArrayList<Student> studentList;
    
    public StudentAdapter(ArrayList<Student> studentList)
    {
        this.studentList = studentList;
    }
    
    public static class StudentViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewCountry;
        public TextView textViewRegistrationTime;
        
        public StudentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            
            textViewName = itemView.findViewById(R.id.tvName);
            textViewEmail = itemView.findViewById(R.id.tvEmail);
            textViewCountry = itemView.findViewById(R.id.tvCountry);
            textViewRegistrationTime = itemView.findViewById(R.id.tvTime);
        }
    }
    
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position)
    {
        holder.textViewName.setText(studentList.get(position).getName());
        holder.textViewEmail.setText(studentList.get(position).getEmail());
        holder.textViewCountry.setText(studentList.get(position).getCountry());
        // TODO Time
//        holder.textViewRegistrationTime.setText(studentList.get(position).getRegistrationTime());
    }
    
    @Override
    public int getItemCount()
    {
        return studentList.size();
    }
}
