package com.blogspot.larn4android.firebaseedittext.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blogspot.larn4android.firebaseedittext.DetailsActivity;
import com.blogspot.larn4android.firebaseedittext.R;
import com.blogspot.larn4android.firebaseedittext.m_Model.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Minto on 8/4/2016.
 */
public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Spacecraft>spacecrafts;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }
        TextView nameTxt = (TextView)convertView.findViewById(R.id.nameTxt);
        TextView propTxt = (TextView)convertView.findViewById(R.id.propTxt);
        TextView descTxt = (TextView)convertView.findViewById(R.id.descTxt);
        final Spacecraft s = (Spacecraft)this.getItem(position);
        nameTxt.setText(s.getName());
        propTxt.setText(s.getpropellen());
        descTxt.setText(s.getDiscription());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDetailActivity(s.getName(),s.getDiscription(),s.getpropellen());
            }
        });
        return convertView;
    }
    private void openDetailActivity(String...details)
    {
        Intent i = new Intent(c, DetailsActivity.class);
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("PROP_KEY",details[2]);

        c.startActivity(i);
    }
}
