package com.blogspot.larn4android.firebaseedittext;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView nameTxt,propTxt,descTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        propTxt = (TextView)findViewById(R.id.propellenDetailTxt);
        descTxt = (TextView)findViewById(R.id.descDetailTxt);


        Intent i = this.getIntent();

        String name = i.getExtras().getString("NAME_KEY");
        String desc = i.getExtras().getString("DESC_KEY");
        String propellent = i.getExtras().getString("PROP_KEY");

        nameTxt.setText(name);
        descTxt.setText(desc);
        propTxt.setText(propellent);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
