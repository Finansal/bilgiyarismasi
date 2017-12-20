package com.burakkarabiyik.finansal.bilgiyarismasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Anasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        setContentView(R.layout.activity_anasayfa);
        Button kuralbtn=(Button)findViewById(R.id.button27);
        Button sorubtn=(Button)findViewById(R.id.button20);
        Button btn=(Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Profil.class);
                startActivity(intocan);
                Anasayfa.this.finishAffinity();
            }
        });
        Button btn2=(Button)findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Butunkategoriler.class);
                startActivity(intocan);
                Anasayfa.this.finishAffinity();
            }
        });
        /* */

        kuralbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Aciklama.class);
                startActivity(intocan);
                Anasayfa.this.finishAffinity();
            }
        });
        sorubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, SoruOneri.class);
                startActivity(intocan);
                Anasayfa.this.finishAffinity();
            }
        });
        }

    @Override
    public void onBackPressed() {
        cik();
    }

    void cik()
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Uygulamadan çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Anasayfa.this.finishAffinity();
                    }
                }).setNegativeButton("Hayır", null).show();
    }
}
