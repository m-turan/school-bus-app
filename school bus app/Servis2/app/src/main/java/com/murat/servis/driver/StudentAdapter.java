package com.murat.servis.driver;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.murat.servis.R;
import com.murat.servis.driver.StudentResponse;
import com.murat.servis.parent.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>{
    Context context;
    List<StudentResponse> student_list;

    public StudentAdapter(Context context, List<StudentResponse> student_list) {
        this.context = context;
        this.student_list = student_list;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(student_list.get(position).getStudentName());
        holder.surname.setText(student_list.get(position).getStudentSurname());
        holder.school.setText(student_list.get(position).getSchoolName());
        holder.parent_phonee.setText(student_list.get(position).getParentPhone());

    }

    @Override
    public int getItemCount() {
        return student_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,surname,school,parent_phonee;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView_student_name);
            surname = itemView.findViewById(R.id.textView_student_surname);
            school = itemView.findViewById(R.id.textView_school_name);
            parent_phonee=itemView.findViewById(R.id.parent_phone2);

        }
    }
}



