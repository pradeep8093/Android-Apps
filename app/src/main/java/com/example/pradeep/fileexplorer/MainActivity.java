package com.example.pradeep.fileexplorer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

//import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    File f;
    //String[] items;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> sitems = new ArrayList<String>();
    ArrayList<File> sfiles = new ArrayList<File>();
   // TextView d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




                lv = (ListView) findViewById(R.id.listView);
      //  d=(TextView)findViewById(R.id.textView);
        final ArrayList<File> myfiles= findfiles(Environment.getExternalStorageDirectory());
       // d.setText(myfiles.get(0).getName().toString());
        for(int i=0; i<myfiles.size();i++){

          items.add(myfiles.get(i).getName().toString());
       }

       ArrayAdapter<String> adp= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File s = myfiles.get(position);
                if (s.isDirectory() == true) {
                    sfiles = findfiles(myfiles.get(position));
                    for (int i = 0; i < sfiles.size(); i++) {

                        sitems.add(sfiles.get(i).getName().toString());
                    }


                    ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sitems);
                    lv.setAdapter(adp1);

                }
            }

        });
           f = sfiles.get(0).getParentFile();
            onBackPressed();

    }

    public void onBackPressed(){

        ArrayList<File> myfiles1= findfiles(f);
        // d.setText(myfiles.get(0).getName().toString());
        for(int i=0; i<myfiles1.size();i++){

            items.add(myfiles1.get(i).getName().toString());
        }
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adp);
    }

    private ArrayList<File> findfiles(File root) {

        File[] files = root.listFiles();
        ArrayList<File> al = new ArrayList<File>();
        for (File singlefile :files){
           al.add(singlefile);
        }
        return al;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
