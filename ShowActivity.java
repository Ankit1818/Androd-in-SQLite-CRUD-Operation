package com.example.tops.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<HashMap<String,String>> arrlist=new ArrayList<>();
    List<User> detailist;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv=(ListView)findViewById(R.id.list);
        db=new DBHandler(getApplicationContext());

        detailist=db.view();

        for(User user:detailist)
        {
            HashMap<String,String>hm=new HashMap<>();
            hm.put("name",user.getName());
            hm.put("phn",user.getPhn());
            arrlist.add(hm);
        }

        String from[]={"name","phn"};
        int to[]={R.id.txt1,R.id.txt2};

        SimpleAdapter adapter =new SimpleAdapter(ShowActivity.this,arrlist,R.layout.design,from,to);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem m1=menu.add(0,1,0,"update");
        MenuItem m2=menu.add(0,2,0,"delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo acm =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        int id=acm.position;

        User user=detailist.get(id);
         if(item.getItemId()==1)
         {
             Intent i =new Intent(ShowActivity.this,UpdateActivity.class);
             i.putExtra("id",user.getId());
             i.putExtra("name",user.getName());
             i.putExtra("phn",user.getPhn());
             startActivity(i);
         }
         if(item.getItemId()==2)
         {
             db=new DBHandler(getApplicationContext());
             db.delete(user.getId());

             AlertDialog.Builder alert=new AlertDialog.Builder(ShowActivity.this);
             alert.setTitle("Delete");
             alert.setMessage("Want to delete it?");
             alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                     Intent intent=new Intent(ShowActivity.this,ShowActivity.class);
                     startActivity(intent);
                 }
             });
             alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
             });
             alert.show();
         }

        return super.onContextItemSelected(item);
    }
}
