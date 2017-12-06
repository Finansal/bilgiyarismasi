package com.example.burakkarabiyik.bilgiyarismasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SoruEkle extends AppCompatActivity {

    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edt4;
    EditText edt5;
    EditText edt6;
    EditText edt7;


    String dogrucevap;
    String puani;
    String kat;
    String sayi;
    String soruid;
    FirebaseDatabase db;
    DatabaseReference reference;
    DatabaseReference rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_soru_ekle);

        Button btn=(Button)findViewById(R.id.button8);
        reference = FirebaseDatabase.getInstance().getReference().child("Soru");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sorum= ((EditText)findViewById(R.id.editText6)).getText().toString();
                final String c1= ((EditText)findViewById(R.id.editText7)).getText().toString();
                final String c2=  ((EditText)findViewById(R.id.editText8)).getText().toString();
                final String c3=  ((EditText)findViewById(R.id.editText9)).getText().toString();
                final String c4=  ((EditText)findViewById(R.id.editText10)).getText().toString();
                final String dogrucevap=  ((EditText)findViewById(R.id.editText13)).getText().toString();
                final String puani=  ((EditText)findViewById(R.id.editText11)).getText().toString();
                final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("SoruSayisi");
                final String kategori=  ((EditText)findViewById(R.id.editText14)).getText().toString();
                kat=kategori;
                final Query reff3=ref3.orderByChild("Kategori").equalTo(kategori);
                reff3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {for (DataSnapshot child : dataSnapshot.getChildren()) {
                            //child is each element in the finished list
                            Map<String, Object> message = (Map<String, Object>) child.getValue();
                            sayi=(message.get("SoruSayisi").toString());
                            soruid=message.get("id").toString();

                            int say=Integer.parseInt(sayi);
                            say++;
                            sayi=Integer.toString(say);

                            soruekle(sorum,c1,c2,c3,c4,dogrucevap,sayi,puani,kategori);
                            reff3.removeEventListener(this);
                        }
                        }
                        else
                        {

                            String key=ref3.push().getKey();
                            ref3.child(key).child("id").setValue(key);
                            ref3.child(key).child("Kategori").setValue(kategori);
                            ref3.child(key).child("SoruSayisi").setValue("1");
                            sayi="0";
                            soruid=key;
                            int say=Integer.parseInt(sayi);
                            say++;
                            sayi=Integer.toString(say);

                            soruekle(sorum,c1,c2,c3,c4,dogrucevap,sayi,puani,kategori);
                            reff3.removeEventListener(this);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


// Write a message to the database
    }

    void soruekle(String sorusu, String c1, String c2, String c3, String c4, String dogru, String num, String puan,String kategori)
    {
        rf = FirebaseDatabase.getInstance().getReference("SoruSayisi");
        String key=reference.push().getKey();
        Map<String, String> users = new HashMap<>();
        users.put("id",key);
        users.put("Soru", sorusu);
        users.put("cevap1", c1);
        users.put("cevap2", c2);
        users.put("cevap3", c3);
        users.put("cevap4", c4);
        users.put("dogru", dogru);
        users.put("numarasi", num);
        users.put("kategori", kategori);
        users.put("puan", puan);
        reference.child(key).setValue(users);
        rf.child(soruid).child("SoruSayisi").setValue(num);
    }
}
