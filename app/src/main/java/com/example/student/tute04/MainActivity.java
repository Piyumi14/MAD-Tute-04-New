package com.example.student.tute04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DBHelper;
import Database.UserMaster;
import Model.Users;


public class MainActivity extends AppCompatActivity {

    EditText uname,pwd;
    Button bAdd;

    TextView data;

    private String username,password;

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.txtUname);
        pwd = findViewById(R.id.txtPwd);
        bAdd = findViewById(R.id.btnAdd);
        data = findViewById(R.id.data);

        db = new DBHelper(this);

        data.setText("");
        int count = 1;

        ArrayList<Users> u = db.readAllInfor();
        for ( Users user : u ) {
            data.append( count  + " " + user.getUname() + "\t\t\t" + user.getPassword() + "\n"  );
            count++;
        }


    }

    public void addData(View view) {
        username = uname.getText().toString();
        password = pwd.getText().toString();

        boolean ans = db.add(username, password);

        if (ans == true) {
            Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        displayInfor(view);

    }

    public void updateData(View view){
        username = uname.getText().toString();
        password = pwd.getText().toString();

        boolean ans2 = db.updateInfo(username,password);

        if(ans2 == true){
            Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error in update",Toast.LENGTH_SHORT).show();
        }
        displayInfor(view);
    }

    public void deleteData(View view){
        username = uname.getText().toString();
        boolean ans3 = db.deleteInfo(username);

        if(ans3 == true){
            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error in delete",Toast.LENGTH_SHORT).show();
        }
        displayInfor(view);
    }

    public void displayInfor(View view){
        data.setText("");
        int count = 1;

        ArrayList<Users> u = db.readAllInfor();

        for(Users user : u){
            data.append(count + " " + user.getUname() + "\t\t\t" + user.getPassword() + "\n");
            count++;
        }
    }
}
