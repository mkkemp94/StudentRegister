package com.mkemp.studentregister.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mkemp.studentregister.R;
import com.mkemp.studentregister.databinding.StudentListItemBinding;
import com.mkemp.studentregister.db.entity.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>
{
    private List<Student> studentList;
    
    public void setStudents(List<Student> students)
    {
        this.studentList = students;
        notifyDataSetChanged();
    }
    
    public static class StudentViewHolder extends RecyclerView.ViewHolder
    {
        private StudentListItemBinding binding;
        
        public StudentViewHolder(@NonNull StudentListItemBinding studentListItemBinding)
        {
            super(studentListItemBinding.getRoot());
            binding = studentListItemBinding;
        }
    }
    
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        StudentListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.student_list_item, parent, false
        );
        return new StudentViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position)
    {
        Student currentStudent = studentList.get(position);
        holder.binding.setStudent(currentStudent);
    }
    
    @Override
    public int getItemCount()
    {
        if (studentList != null)
        {
            return studentList.size();
        }
        else
        {
            return 0;
        }
    }
}
