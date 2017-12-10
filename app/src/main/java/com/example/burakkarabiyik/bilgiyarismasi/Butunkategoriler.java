package com.example.burakkarabiyik.bilgiyarismasi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Butunkategoriler extends AppCompatActivity {


    String mail,puan,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butunkategoriler);
        Button btn=(Button)findViewById(R.id.button16);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            mail = user.getEmail();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.

        }




    }
FirebaseDatabase db;
    public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),Kategoriler.class);
        switch(v.getId()){
            case R.id.button17:
                sorgu("Türkçe");
                i.putExtra("kategori","Türkçe");
                i.putExtra(("puan"),puan);
                startActivity(i);
                break;
            case R.id.button16:
                sorgu("Matematik");
                i.putExtra("kategori","Matematik");
                i.putExtra(("puan"),puan);
                startActivity(i);
                break;
            case R.id.button23:
                sorgu("Sinema");
                i.putExtra("kategori","Sinema");
                i.putExtra(("puan"),puan);
                startActivity(i);
                break;
        }
    }

    public  void sorgu(final String kategori)
    {
        db=FirebaseDatabase.getInstance();
        final DatabaseReference ref3=db.getReference("Puanlar");
        final Query reff3=ref3.orderByChild("email").equalTo(mail);

        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list
                        Map<String, Object> message = (Map<String, Object>) child.getValue();
                        id=message.get("id").toString();
                        sorgu2(kategori);
                        reff3.removeEventListener(this);
                    }
                }
                else
                {

                    String key=ref3.push().getKey();
                    ref3.child(key).child("id").setValue(key);
                    ref3.child(key).child("email").setValue(mail);
                    ref3.child(key).child(kategori).setValue("0");
                    reff3.removeEventListener(this);
                    puan="0";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void sorgu2(final String kategori)
    {
        final DatabaseReference ref3=db.getReference("Puanlar").child(id).child(kategori);

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list
                        Map<String, Object> message = (Map<String, Object>) child.getValue();
                        puan=(message.get(kategori).toString());
                        id=message.get("id").toString();
                        ref3.removeEventListener(this);
                    }
                }
                else
                {

                    ref3.setValue("0");
                    ref3.removeEventListener(this);
                    puan="0";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
