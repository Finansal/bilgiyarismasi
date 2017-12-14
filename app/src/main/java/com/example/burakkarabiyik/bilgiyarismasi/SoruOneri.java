package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SoruOneri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_oneri);

        Button btngonder=(Button)findViewById(R.id.button19);
        Button btngeri=(Button)findViewById(R.id.button28);
        Random r=new Random(); //random sınıfı
        String kod="";
        int a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        a=r.nextInt(10);
        kod=kod+Integer.toString(a);
        final String kodd=kod;

        ((TextView)findViewById(R.id.textView24)).setText(((TextView)findViewById(R.id.textView24)).getText()+kodd);

        btngeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btngonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((EditText)findViewById(R.id.editText22)).getText().toString().equals(kodd))
                {
                    if(((EditText)findViewById(R.id.editText16)).getText().toString().isEmpty()&&((EditText)findViewById(R.id.editText17)).getText().toString().isEmpty()&&((EditText)findViewById(R.id.editText18)).getText().toString().isEmpty()&&
                            ((EditText)findViewById(R.id.editText19)).getText().toString().isEmpty()&&((EditText)findViewById(R.id.editText20)).getText().toString().isEmpty()&&((EditText)findViewById(R.id.editText23)).getText().toString().isEmpty())
                    {
                        Toast.makeText(SoruOneri.this, "Lütfen Boş bırakmayınız.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            // Name, email address, and profile photo Url
                            String name = user.getDisplayName();

                            // Check if user's email is verified
                            boolean emailVerified = user.isEmailVerified();

                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use


                        }
                        DatabaseReference myRef = database.getReference("ÖnerilenSorular");

                        String key=myRef.push().getKey();
                        myRef.child(key).child("id").setValue(key);
                        myRef.child(key).child("soru").setValue(((EditText)findViewById(R.id.editText16)).getText().toString().trim());
                        myRef.child(key).child("a").setValue(((EditText)findViewById(R.id.editText17)).getText().toString().trim());
                        myRef.child(key).child("b").setValue(((EditText)findViewById(R.id.editText18)).getText().toString().trim());
                        myRef.child(key).child("c").setValue(((EditText)findViewById(R.id.editText19)).getText().toString().trim());
                        myRef.child(key).child("d").setValue(((EditText)findViewById(R.id.editText20)).getText().toString().trim());
                        myRef.child(key).child("dogrucevap").setValue(((EditText)findViewById(R.id.editText23)).getText().toString().trim());
                        myRef.child(key).child("Gönderenkisi").setValue(user.getDisplayName());
                        Toast.makeText(SoruOneri.this, "Öneriniz için Teşekkürler değerlendireceğiz.",
                                Toast.LENGTH_SHORT).show();
                        Intent intocan = new Intent(SoruOneri.this, Anasayfa.class);
                        startActivity(intocan);
                        SoruOneri.this.finishAffinity();
                    }
                }
                else
                {
                    Toast.makeText(SoruOneri.this, "Lütfen Kodu doğru giriniz ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intocan = new Intent(SoruOneri.this, Anasayfa.class);
        startActivity(intocan);
        SoruOneri.this.finishAffinity();
    }
}
