package com.geektech.home1lesson1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    LayoutInflater inflater ;
    List<Contact> list ;


    public ContactsAdapter (List<Contact> list , Context context){
        this.list = list ;
        this.inflater =LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.design , parent ,false);



        return  new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtNumber.setText(list.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactsViewHolder extends  RecyclerView.ViewHolder {
         TextView txtName , txtNumber ;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.namePhone);
            txtNumber = itemView.findViewById(R.id.numberPhone);
        }



    }
}
