package com.world_tech_point.calling_app.CallingDetails;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.world_tech_point.calling_app.R;
import java.util.ArrayList;

public class ContactsFragment extends Fragment {

private RecyclerView recyclerView;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_CONTACTS= 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerView = root.findViewById(R.id.contractRecyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        //Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED){

            contacts();


        }else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

                contacts();
            }else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    fetchLocation();

                }else {

                    contacts();

                }

            }




        }



        return root;
    }

    private void contacts (){

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        ArrayList<Contacts>arrayList = new ArrayList<>();
        //arrayList.clear();

        if (cursor.getCount() > 0) {

            while(cursor.moveToNext()){

                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                //String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                if (number.length() >0){

                    Cursor phoneCursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"=?", new String[] {id},null );

                    if (phoneCursor.getCount() > 0){

                        while (phoneCursor.moveToNext()){

                            String phoneNumberValue = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Contacts contacts = new Contacts(name,phoneNumberValue);
                            arrayList.add(contacts);



                        }

                        ContactsAdapter contactsAdapter = new ContactsAdapter(arrayList,getContext());
                        recyclerView.setAdapter(contactsAdapter);
                        contactsAdapter.notifyDataSetChanged();

                    }
                    phoneCursor.close();
                }

            }


        }else {
            Toast.makeText(getContext(), "No contacts found", Toast.LENGTH_SHORT).show();
        }

    }

    private void fetchLocation() {


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to access this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        MY_PERMISSIONS_REQUEST_ACCESS_CONTACTS);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                             dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_ACCESS_CONTACTS);

            }
        }else {

           contacts();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_CONTACTS){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

              contacts();

            }else{

                fetchLocation();

            }
        }else {

            fetchLocation();

        }

    }


}