package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Anasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        setContentView(R.layout.activity_anasayfa);
        Button kuralbtn=(Button)findViewById(R.id.button27);
        Button sorubtn=(Button)findViewById(R.id.button20);
        Button btn=(Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Profil.class);
                startActivity(intocan);
            }
        });
        Button btn2=(Button)findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Butunkategoriler.class);
                startActivity(intocan);
            }
        });

        /* */

        kuralbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Aciklama.class);
                startActivity(intocan);
            }
        });
        sorubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, SoruOneri.class);
                startActivity(intocan);
            }
        });
        }

    @Override
    public void onBackPressed() {

    }
}
