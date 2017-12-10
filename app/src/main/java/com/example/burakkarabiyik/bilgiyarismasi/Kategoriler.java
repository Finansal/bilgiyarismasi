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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Kategoriler extends AppCompatActivity {

    DatabaseReference ref;
     String soru;
    TextView edtsoru;
    Button a;
    Button b;
    Button c;
    Button d;
    int kazanilanpuan=0;
    String oncekipuan;
    Random r=new Random();
    Button cik;
    Kullanici k;
    String degeri;
    String cevap;
    String id;
    String mod,kategori;
    int cozulensorusayisi=0;
    int rastgele,sonuc;
    int dogru=0,yanlis=0;
    List<String> sorunumaralari = new ArrayList<String>();

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
        Bundle extras = getIntent().getExtras();
        kategori=extras.getString("kategori");
        edtsoru=(TextView)findViewById(R.id.editText12);
        a=(Button)findViewById(R.id.button11);
        b=(Button)findViewById(R.id.button12);
        c=(Button)findViewById(R.id.button13);
        d=(Button)findViewById(R.id.button14);
        cik=(Button)findViewById(R.id.button15);
        Toast.makeText(Kategoriler.this, kategori,
                Toast.LENGTH_SHORT).show();

            final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("SoruSayisi");

            final Query reff3=ref3.orderByChild("Kategori").equalTo(kategori);
            reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot child : dataSnapshot.getChildren()) {
            //child is each element in the finished list

            Map<String, Object> message = (Map<String, Object>) child.getValue();
                mod=(String)message.get("SoruSayisi");

              sayiuret();

            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });


        ref = FirebaseDatabase.getInstance().getReference("Kullanici");
        Query yeni=ref.orderByChild("email").equalTo(mail);
        yeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //child is each element in the finished list

                    Map<String, Object> message = (Map<String, Object>) child.getValue();

                    id=(String)message.get("id");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button bt=(Button)findViewById(R.id.button9);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setVisibility(View.GONE);
                d.setVisibility(View.GONE);
            }
        });
        //Buttonlarrr

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorgu();

            if(cevap.equals("a"))
            {
                dogru++;
                kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);

               sayiuret();
            }
            else {
                yanlis++;
               sayiuret();
            }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorgu();
                if(cevap.equals("b"))
                {
                    dogru++;
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);

                   sayiuret();
                }
                else {
                    yanlis++;
                    sayiuret();
                }
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorgu();
                if(cevap.equals("c"))
                {
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                    dogru++;
                   sayiuret();

                }
                else
                {
                    yanlis++;
                    sayiuret();
                }
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorgu();
                if(cevap.equals("d"))
                {
                    dogru++;
                    kazanilanpuan=kazanilanpuan+Integer.parseInt(degeri);
                    sayiuret();
                }
                else
                {
                    yanlis++;
                    sayiuret();

                }
            }
        });

        cik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitir();

            }
        });


    }
    void sayiuret()
    {

            rastgele=r.nextInt(Integer.parseInt(mod))+1;
            sonuc=sorunumaralari.indexOf(Integer.toString(rastgele));

            if(Integer.toString(sonuc).equals("-1"))
            {
                sorunumaralari.add(Integer.toString(rastgele));
                soru(Integer.toString(rastgele));

                cozulensorusayisi++;

            }
            else
            {
                if(mod.equals(Integer.toString(cozulensorusayisi)))
                {
                    bitir();

                }
                else
                 sayiuret();
            }



    }
    void sorgu()
    {
        if(mod.equals(Integer.toString(cozulensorusayisi+1)))
        {
            bitir();

        }
    }
    void soru(final String sorum)
    {
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Soru");
        Bundle extras = getIntent().getExtras();

        Query yeni=ref2.orderByChild("kategori").equalTo(extras.getString("kategori"));

        kategori=extras.getString("kategori");


        yeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists())
                {

                }
                else
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //child is each element in the finished list
                    Map<String, Object> message = (Map<String, Object>) child.getValue();
                    if(sorum.equals(message.get("numarasi").toString()))
                    {
                        edtsoru.setText(message.get("Soru").toString());
                        a.setText(message.get("cevap1").toString());
                        b.setText(message.get("cevap2").toString());
                        c.setText(message.get("cevap3").toString());
                        d.setText(message.get("cevap4").toString());
                        cevap=message.get("dogru").toString();
                        degeri=message.get("puan").toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        soru=sorum;
    }


    void bitir()
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
                        oncekipuan=message.get(kategori).toString();

                        if(kazanilanpuan>Integer.parseInt(message.get(kategori).toString()))
                            ref3.child(message.get("id").toString()).child(kategori).setValue(Integer.toString(kazanilanpuan));
                        else {
                        }

                        Intent intocan = new Intent(Kategoriler.this, Sonuc.class);
                        intocan.putExtra("dogru",dogru);
                        intocan.putExtra("yanlis",yanlis);
                        intocan.putExtra("oncekipuan",oncekipuan);
                        intocan.putExtra("kazanilanpuan",kazanilanpuan);
                        intocan.putExtra("kategori",kategori);
                        startActivity(intocan);
                        reff3.removeEventListener(this);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
