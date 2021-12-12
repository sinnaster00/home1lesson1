package com.geektech.home1lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ContactsAdapter adapter ;
 private ArrayList<Contact> list ;
 private RecyclerView rvList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }


    private void initial() {

        rvList = findViewById(R.id.rv_list);
        list = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
     requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);

            } else {
                contacts();
            }
        }

    }

    private void contacts() {
        Cursor cursor = getContentResolver().query (ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        if(cursor.getCount() > 0){
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(new Contact(name , number));

        }

        } cursor.close();
          adapter = new ContactsAdapter(list , this);
          rvList.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){

            if(grantResults [0] == PackageManager.PERMISSION_GRANTED){
                contacts();
            }
        }
    }
}