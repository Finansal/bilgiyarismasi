package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Giris extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;


    /////////////////


int ooo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);


       /* new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.
                Intent mainIntent = new Intent(Giris.this,Kayitol.class);
                Giris.this.startActivity(mainIntent);
                Giris.this.finish();
            }
        }, 100); */
        Button btn = (Button)findViewById(R.id.kayit);
        Button btn2 = (Button)findViewById(R.id.button);
        Button btn3 = (Button)findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        //Butonumuza tıklama özelliği kazandırıyoruz.
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                Intent intocan = new Intent(Giris.this, Kayitol.class);
                startActivity(intocan);
                Giris.this.finishAffinity();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                Intent intocan = new Intent(Giris.this, SifremiUnuttum.class);
                startActivity(intocan);
                Giris.this.finishAffinity();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(internetErisimi()){ { sign(((EditText)findViewById(R.id.editText)).getText().toString().trim(),((EditText)findViewById(R.id.editText2)).getText().toString().trim()); } }else{ {


                    Toast.makeText(Giris.this, "İnternet Gerekmektedir ",
                            Toast.LENGTH_SHORT).show();
                } }


            }

        });
    }

    public boolean internetErisimi() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null         && conMgr.getActiveNetworkInfo().isAvailable()         && conMgr.getActiveNetworkInfo().isConnected())
        {         return true;         }
        else {         return false;         }
    }



    public void sign(String email,String password)
    {

        if(email.equals("admin"))
    {
        Intent intocan = new Intent(Giris.this, SoruEkle.class);
        startActivity(intocan);
        Giris.this.finishAffinity();
    }
    else
    {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                          //  if(user.isEmailVerified())
                            //{
                                Intent intocan = new Intent(Giris.this, Anasayfa.class);
                                startActivity(intocan);

                                Giris.this.finishAffinity();
                           /* }
                            else {
                                Toast.makeText(Giris.this, "Üyeliğinizi Onaylamanız Gerekmektedir ",
                                        Toast.LENGTH_SHORT).show();
                            }*/

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Giris.this, "Hatalı Giriş ",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }



}
