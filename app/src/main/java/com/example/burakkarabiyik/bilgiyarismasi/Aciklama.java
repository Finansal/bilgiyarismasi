package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aciklama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aciklama);
        Button btn5=(Button)findViewById(R.id.button5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Aciklama.this, Anasayfa.class);
                startActivity(intocan);
                Aciklama.this.finishAffinity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intocan = new Intent(Aciklama.this, Anasayfa.class);
        startActivity(intocan);
        Aciklama.this.finishAffinity();
    }
}
