package com.example.burakkarabiyik.bilgiyarismasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    Toast.makeText(SoruOneri.this, "Kod Doğru",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SoruOneri.this, "Lütfen Kodu doğru giriniz ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
