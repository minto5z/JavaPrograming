package com.blogspot.larn4android.firebaseedittext;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.blogspot.larn4android.firebaseedittext.m_Firebase.FirebaseHelper;
import com.blogspot.larn4android.firebaseedittext.m_Model.Spacecraft;
import com.blogspot.larn4android.firebaseedittext.m_UI.CustomAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    GridView gv;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText nameEditTxt,propTxt,descTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readData();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(),"Refresh",Toast.LENGTH_LONG).show();

                if(adapter!=null){
                    //readData();
                    if(swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(false);
                }
                readData();
            }
        });
        gv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                displayInputDialog();
            }
        });

    }

    private void readData(){
        gv = (GridView)findViewById(R.id.gv);
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);
        adapter = new CustomAdapter(this,helper.retrieve());


    }

    private void displayInputDialog()
    {
        Dialog d = new Dialog(this);
        d.setTitle("Save Information");
          d.setContentView(R.layout.input_layout);
        nameEditTxt = (EditText)d.findViewById(R.id.nameEditText);
        propTxt = (EditText)d.findViewById(R.id.propellenEditText);
        descTxt = (EditText)d.findViewById(R.id.discriptEditText);
        Button saveBtn = (Button)d.findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditTxt.getText().toString();
                String propellent = propTxt.getText().toString();
                String desc = descTxt.getText().toString();
                Spacecraft s = new Spacecraft();
                s.setName(name);
                s.setDiscription(desc);
                s.setpropellen(propellent);
                if(name!= null&&name.length()>0){
                    if(helper.save(s))
                    {
                        nameEditTxt.setText("");
                        propTxt.setText("");
                        descTxt.setText("");
                        adapter = new CustomAdapter(MainActivity.this,helper.retrieve());
                        gv.setAdapter(adapter);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Name Must Not Be Empty",Toast.LENGTH_LONG).show();
                }
            }
        });
        d.show();
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
