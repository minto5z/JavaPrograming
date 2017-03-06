package com.blogspot.larn4android.firebaseedittext.m_Firebase;

import com.blogspot.larn4android.firebaseedittext.m_Model.Spacecraft;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Minto on 8/4/2016.
 */
public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved = null;
    ArrayList<Spacecraft>spacecrafts = new ArrayList<>();
    public FirebaseHelper(DatabaseReference db){
        this.db = db;
    }
    public Boolean save(Spacecraft spacecraft){
        if(spacecraft==null){
            saved = false;
        }
        else{
            try {
                db.child("Spacecraft").push().setValue(spacecraft);
                saved =true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved =false;
            }
        }
        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        spacecrafts.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Spacecraft spacecraft = ds.getValue(Spacecraft.class);
            spacecrafts.add(spacecraft);
        }
    }
    public ArrayList<Spacecraft>retrieve(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return spacecrafts;
    }
}
