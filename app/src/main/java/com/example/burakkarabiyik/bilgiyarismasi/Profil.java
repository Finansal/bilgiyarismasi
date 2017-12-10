package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profil extends AppCompatActivity {


    DatabaseReference dbref;
    Query yeni;
    String mail="";
    String uid;
    TextView kadi;
    TextView emaill;
    TextView puann;
    Kullanici k;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
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
        kadi = (TextView) findViewById(R.id.textView5);
        emaill = (TextView) findViewById(R.id.textView6);
        puann = (TextView) findViewById(R.id.textView7);

        dbref = FirebaseDatabase.getInstance().getReference("Kullanici");
         yeni=dbref.orderByChild("email").equalTo(mail);

        Button btn6=(Button)findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Ayarlar.class);
                i.putExtra("kadi",k.getAd());
                i.putExtra("email",k.getEmail());
                i.putExtra("sifre",k.getSifre());
                i.putExtra("puan",k.getpuan());
                i.putExtra("id",id);
                startActivity(i);
            }
        });

    }

    String adi;

    @Override
    protected void onStart() {

        super.onStart();

        yeni.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                //child is each element in the finished list

                                                Map<String, Object> message = (Map<String, Object>) child.getValue();

                                                 k = new Kullanici((String) message.get("id"),(String) message.get("Kullaniciadi"),
                                                        (String) message.get("email"), (String) message.get("sifre"), (String) message.get("puan"),(String) message.get("seviye"));
                                                    kadi.setText(k.getAd());
                                                    id=(String)message.get("id");


                                                emaill.setText(k.getEmail());
                                                puanlarigetir();

                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }
        );
    }

    void  puanlarigetir()
    {
        final DatabaseReference ref3=FirebaseDatabase.getInstance().getReference("Puanlar");
        final Query reff3=ref3.orderByChild("email").equalTo(mail);

        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list

                        kategoribul(child.getKey());
                        reff3.removeEventListener(this);
                    }
                   // kategoripuanlar(list);
                }
                else
                {

                    puann.setText("Hiçbir soru çözülmedi");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void kategoribul(final String key)
    {
        final ArrayList<String> list=new ArrayList<>();
        final DatabaseReference ref3=FirebaseDatabase.getInstance().getReference("Puanlar").child(key);

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list
                       String v= child.getKey();
                        if(v.trim().equals("id"))
                        {

                        } else if(v.trim().equals("email"))
                        {

                        }
                        else
                        {
                            list.add(v);
                        }

                    }
                    kategoripuanlar(list);
                }
                else
                {

                    puann.setText("Hiçbir soru çözülmedi");
                }
                ref3.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void kategoripuanlar(final ArrayList<String> list)
    {
        final DatabaseReference ref3=FirebaseDatabase.getInstance().getReference("Puanlar");
        final Query reff3=ref3.orderByChild("email").equalTo(mail);

        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list
                        Map<String, Object> message = (Map<String, Object>) child.getValue();
                        for (String ds: list  ) {

                            puann.setText(puann.getText()+ds+" : "+message.get(ds).toString()+"\n");
                        }

                        reff3.removeEventListener(this);
                    }
                }
                else
                {

                    puann.setText("Hiçbir soru çözülmedi");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}


