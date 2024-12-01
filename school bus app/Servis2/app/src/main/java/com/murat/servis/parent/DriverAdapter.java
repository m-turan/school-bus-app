package com.murat.servis.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.murat.servis.R;
import com.murat.servis.driver.DriverInfoResponse;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.MyViewHolder>{
    Context context;
    List<DriverInfoResponse> driver_list;

    public DriverAdapter(Context context, List<DriverInfoResponse> driver_list) {
        this.context = context;
        this.driver_list = driver_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.driver_name.setText(driver_list.get(position).getDriverName());
        holder.driver_surname.setText(driver_list.get(position).getDriverSurname());
        holder.driver_phone.setText(driver_list.get(position).getDriverPhone());
        holder.driver_mail.setText(driver_list.get(position).getDriverEmail());

    }

    @Override
    public int getItemCount() {
        return driver_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView driver_name,driver_surname, driver_mail,driver_phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            driver_name = itemView.findViewById(R.id.textView_driver_name);
            driver_surname = itemView.findViewById(R.id.textView_driver_surname);
            driver_mail = itemView.findViewById(R.id.textView_driver_mail);
            driver_phone = itemView.findViewById(R.id.textView_driver_phone);
        }
    }
}
