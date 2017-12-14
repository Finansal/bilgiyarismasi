package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class Ayarlar extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;
    EditText edtkadi;
    EditText edtmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Kullanici");
        Button btn=(Button)findViewById(R.id.button7);
        edtkadi=(EditText)findViewById(R.id.editText3);
        edtmail=(EditText)findViewById(R.id.editText4);
        Bundle extras = getIntent().getExtras();
        mAuth = FirebaseAuth.getInstance();
        final String id=extras.getString("id");
        edtkadi.setText(user.getDisplayName());
        edtmail.setText(user.getEmail());
        final String pn=extras.getString("puan");

        final FirebaseAuth finalMAuth = mAuth;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.child(id).child("Kullaniciadi").setValue(edtkadi.getText().toString().trim());
                ref.child(id).child("mail").setValue(edtmail.getText().toString().trim());

                FirebaseUser user = finalMAuth.getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(edtkadi.getText().toString().trim()).build();
                user.sendEmailVerification();
                user.updateProfile(profileUpdates);
                EditText sifre1=(EditText)findViewById(R.id.editText5);
                EditText emaill=(EditText)findViewById(R.id.editText4);
                EditText sifre2=(EditText)findViewById(R.id.editText21);

                if(!sifre1.getText().toString().isEmpty()&&!sifre1.getText().toString().isEmpty()) {


                    if (sifre1.getText().toString().trim().equals(sifre2.getText().toString().trim()))
                        user.updatePassword(sifre1.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Ayarlar.this, "Şifre Değiştirildi!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Ayarlar.this, "Şifre Değiştirilemiyor!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                }


                if(!edtmail.getText().toString().isEmpty()) {
                    user.updateEmail(edtmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Ayarlar.this, "Email Değiştirildi!", Toast.LENGTH_LONG).show();
                                        Intent intocan = new Intent(Ayarlar.this, Anasayfa.class);
                                        startActivity(intocan);
                                        Ayarlar.this.finishAffinity();
                                    } else {
                                        Toast.makeText(Ayarlar.this, "Email Değiştirilemiyor", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }

}
