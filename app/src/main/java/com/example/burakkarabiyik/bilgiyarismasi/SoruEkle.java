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

    String sorum;
    String c1;
    String c2;
    String c3;
    String c4;

    String dogrucevap;
    String puani;

     String sayi="5";
    FirebaseDatabase db;
    DatabaseReference reference;
     DatabaseReference rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_soru_ekle);

        Button btn=(Button)findViewById(R.id.button8);


         reference = db.getReference().child("Soru");
         rf=db.getReference().child("SoruSayisi");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sorum=((EditText)findViewById(R.id.editText6)).getText().toString();
                c1=((EditText)findViewById(R.id.editText7)).getText().toString();
                c2=((EditText)findViewById(R.id.editText8)).getText().toString();
                c3=((EditText)findViewById(R.id.editText9)).getText().toString();
                c4=((EditText)findViewById(R.id.editText10)).getText().toString();
                //sayi
                puani=((EditText)findViewById(R.id.editText11)).getText().toString();
                dogrucevap=((EditText)findViewById(R.id.editText12)).getText().toString();

                Toast.makeText(getApplicationContext(), "Soru:"+sorum, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "C1:"+c1, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "c2:"+c2, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "c3:"+c3, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "c4:"+c4, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Puan:"+puani, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Doğru:"+dogrucevap, Toast.LENGTH_LONG).show();
            }
        });
        rf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sayi=(dataSnapshot.getValue(String.class));
                int say=Integer.parseInt(sayi);
                say++;
                sayi=Integer.toString(say);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void soruekle(String sorusu,String c1,String c2,String c3,String c4,String dogru,String num,String puan)
    {

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
        users.put("puan", puan);
        reference.child(key).setValue(users);
        rf.setValue(sayi);
    }
}
