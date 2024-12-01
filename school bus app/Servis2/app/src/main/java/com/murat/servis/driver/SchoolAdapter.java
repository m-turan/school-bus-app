package com.murat.servis.driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.murat.servis.R;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.MyViewHolder> {
    Context context;
    List<SchoolResponse> schools;

    public SchoolAdapter(Context context, List<SchoolResponse> schools) {
        this.context = context;
        this.schools = schools;
    }

    @NonNull
    @Override
    public SchoolAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapter.MyViewHolder holder, int position) {
        holder.school_name.setText(schools.get(position).getSchoolName());

    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView school_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            school_name = itemView.findViewById(R.id.d_school);
        }
    }
}
