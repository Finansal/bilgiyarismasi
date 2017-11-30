package com.example.burakkarabiyik.bilgiyarismasi;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Profil extends AppCompatActivity {


    DatabaseReference dbref;
    String mail;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("Kullanici");
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
            uid = user.getUid();

        }

    }

    String adi;

    @Override
    protected void onStart() {

        super.onStart();

      dbref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              for (DataSnapshot child : dataSnapshot.getChildren()) {
                  //child is each element in the finished list
                  Map<String, Object> message = (Map<String, Object>) child.getValue();

                  Kullanici msg = new Kullanici((String) message.get("Kullaniciadi"),
                          (String) message.get("email"), (String) message.get("sifre"), (String) message.get("puan"));
                    if(msg.getEmail().toString()==mail) {
                        Toast.makeText(getApplicationContext(), msg.Kullaniciadi+" "+msg.email+" "+msg.puan, Toast.LENGTH_LONG).show();
                    }
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
    }

}
