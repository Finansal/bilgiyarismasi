package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Kayitol extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase db=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        mAuth = FirebaseAuth.getInstance();
        Button btn = (Button)findViewById(R.id.buttonRegister);



        //Butonumuza tıklama özelliği kazandırıyoruz.
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String ad= ((EditText)findViewById(R.id.kullaniciadi)).getText().toString();
                final String sifre= ((EditText)findViewById(R.id.sifrem)).getText().toString();
                final String sifre2=  ((EditText)findViewById(R.id.sifrem2)).getText().toString();
                final String email=  ((EditText)findViewById(R.id.emailim)).getText().toString();
                Toast.makeText(getApplicationContext(), "Kullanıcıadi:"+email+":Sifre:"+sifre+":", Toast.LENGTH_LONG).show();

                uyeol(ad,email,sifre);
                Intent intocan = new Intent(Kayitol.this, Giris.class);
                startActivity(intocan);
            }
        });

    }

    public void createuser(String kadi,String email,String password)
    {

        final DatabaseReference reference = db.getReference().child("Users");
        Map<String, String> map = new HashMap<String, String>();
        map.put("Kullanici Adı", kadi);
        map.put("Email", email);
        map.put("sifre",password);
        map.put("Puan","0");
        reference.push().setValue(map);

    }

    public  void uyeol(final String kadi, final String email, final String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            createuser(kadi, email, password);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Kayitol.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                            // ...
                        }
                    }
                });
    }

    }


