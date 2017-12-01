package com.example.burakkarabiyik.bilgiyarismasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText edtsifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);

        ref = FirebaseDatabase.getInstance().getReference("Kullanici");
        Button btn=(Button)findViewById(R.id.button7);
        edtkadi=(EditText)findViewById(R.id.editText3);
        edtsifre=(EditText)findViewById(R.id.editText4);
        edtmail=(EditText)findViewById(R.id.editText5);
        Bundle extras = getIntent().getExtras();
        edtkadi.setText(extras.getString("kadi"));
        edtmail.setText(extras.getString("email"));
        edtsifre.setText(extras.getString("sifre"));
        final String pn=extras.getString("puan");
        final String id=extras.getString("id");
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.child(id).child("Kullaniciadi").setValue(edtkadi.getText().toString().trim());
                ref.child(id).child("mail").setValue(edtmail.getText().toString().trim());
                ref.child(id).child("sifre").setValue(edtsifre.getText().toString().trim());
            }
        });
    }

}
