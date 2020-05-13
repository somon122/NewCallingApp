package com.world_tech_point.calling_app.CallingDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.world_tech_point.calling_app.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    private List<Contacts>contactsList;
    private Context context;

    private Contacts contacts;

    public ContactsAdapter(List<Contacts> contactsList, Context context) {
        this.contactsList = contactsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contacts_model_view,parent,false);

        contacts = new Contacts();


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {

        contacts = contactsList.get(position);
        holder.name.setText(contacts.getmName());
        holder.number.setText(contacts.getmNumber());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        name = itemView.findViewById(R.id.contactsName_id);
        number = itemView.findViewById(R.id.contactsNumber_id);

        }
    }
}
