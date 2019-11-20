package com.example.tops.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtname,edtphn;
    Button btninsert,btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    edtname=(EditText)findViewById(R.id.edtname);
    edtphn=(EditText)findViewById(R.id.edtpass);
    btninsert=(Button)findViewById(R.id.btninsert);
    btnview=(Button)findViewById(R.id.btnview);

    btninsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String user1=edtname.getText().toString();
            String phn=edtphn.getText().toString();

            User user =new User();

            user.setName(user1);
            user.setPhn(phn);

            DBHandler dbHandler=new DBHandler(getApplicationContext());

            long id=dbHandler.insert(user);

            Toast.makeText(MainActivity.this, "Record inserted"+id, Toast.LENGTH_SHORT).show();
        }
    });

    btnview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i =new Intent(MainActivity.this,ShowActivity.class);
            startActivity(i);

        }
    });
    }
}
