package com.example.tops.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {


    EditText etuser,etphone;
    Button btnupdate;
    DBHandler db;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etuser=(EditText)findViewById(R.id.updatename);
        etphone=(EditText)findViewById(R.id.updatepass);

        btnupdate=(Button)findViewById(R.id.btnupdate);

        Bundle bundle =getIntent().getExtras();
         id=bundle.getInt("id");

        etuser.setText(bundle.getString("name"));
        etphone.setText(bundle.getString("phn"));

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name=etuser.getText().toString();
                String phn=etphone.getText().toString();

                User user=new User();
                user.setId(id);
                user.setName(name);
                user.setPhn(phn);

                db=new DBHandler(getApplicationContext());
                db.update(user);

                Intent intent=new Intent(UpdateActivity.this,ShowActivity.class);
                startActivity(intent);




            }
        });


    }
}
