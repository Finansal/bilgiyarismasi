package com.example.burakkarabiyik.bilgiyarismasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Kategoriler extends AppCompatActivity {

    DatabaseReference ref;
     String soru;
    EditText edtsoru;
    Button a;
    Button b;
    Button c;
    Button d;
    int kazanilanpuan=0;
    Button cik;
    Kullanici k;
    String degeri;
    String cevap;

    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);
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

        }

        edtsoru=(EditText)findViewById(R.id.editText12);
        a=(Button)findViewById(R.id.button11);
        b=(Button)findViewById(R.id.button12);
        c=(Button)findViewById(R.id.button13);
        d=(Button)findViewById(R.id.button14);
        cik=(Button)findViewById(R.id.button15);

        ref = FirebaseDatabase.getInstance().getReference("Kullanici");
        Query yeni=ref.orderByChild("email").equalTo(mail);
        yeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //child is each element in the finished list

                    Map<String, Object> message = (Map<String, Object>) child.getValue();
                    soru=(message.get("seviye").toString());
                    Toast.makeText(Kategoriler.this, "soru :"+message.get("seviye")+" "+message.get("id"),
                            Toast.LENGTH_SHORT).show();
                    soru(soru);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Buttonlarrr

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(cevap.equals("a"))
            {
                kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                int sr=Integer.parseInt(soru);
                sr++;
                soru=Integer.toString(sr);
                soru(soru);
            }
            else {
                int sr=Integer.parseInt(soru);
                sr++;
                soru=Integer.toString(sr);
                soru(soru);
            }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevap.equals("b"))
                {
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                    int sr=Integer.parseInt(soru);
                    sr++;
                    soru=Integer.toString(sr);
                    soru(soru);
                }
                else {
                    int sr=Integer.parseInt(soru);
                    sr++;
                    soru=Integer.toString(sr);
                    soru(soru);
                }
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevap.equals("c"))
                {
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                    int sr=Integer.parseInt(soru);
                    sr++;
                    soru=Integer.toString(sr);
                    soru(soru);
                }
                else
                {
                    int sr=Integer.parseInt(soru);
                    sr++;
                    soru=Integer.toString(sr);
                    soru(soru);
                }
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevap.equals("d"))
                {
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                    int sr=Integer.parseInt(soru);
                    sr++;
                    soru=Integer.toString(sr);
                }
            }
        });

        cik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Kategoriler.this, "Kazanılan puan :"+kazanilanpuan,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    void soru(String sorum)
    {
        DatabaseReference  ref2=FirebaseDatabase.getInstance().getReference("Soru");
        Query yeni=ref2.orderByChild("numarasi").equalTo(sorum);


        yeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //child is each element in the finished list
                    Map<String, Object> message = (Map<String, Object>) child.getValue();
                    edtsoru.setText(message.get("Soru").toString());
                    a.setText(message.get("cevap1").toString());
                    b.setText(message.get("cevap2").toString());
                    c.setText(message.get("cevap3").toString());
                    d.setText(message.get("cevap4").toString());
                    cevap=message.get("dogru").toString();
                    degeri=message.get("puan").toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
