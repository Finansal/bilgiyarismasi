package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sonuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        /*
        intocan.putExtra("dogru",dogru);
                intocan.putExtra("yanlis",yanlis);
                intocan.putExtra("oncekipuan",oncekipuan);
                intocan.putExtra("kazanilanpuan",kazanilanpuan);
         */
        Bundle extras = getIntent().getExtras();
        Button btn=(Button)findViewById(R.id.button18);
        Button btn2=(Button)findViewById(R.id.button19);
        TextView tv2=(TextView)findViewById(R.id.textView13);
        TextView tv3=(TextView)findViewById(R.id.textView14);
        TextView tv6=(TextView)findViewById(R.id.textView17);
        tv6.setText(extras.get("kategori").toString());
        tv2.setText(extras.get("dogru").toString());
        tv3.setText(extras.get("yanlis").toString());
        ((TextView)findViewById(R.id.textView16)).setText("Eski Puan:"+extras.get("oncekipuan")+"\n"+"Yapılan Puan:"+extras.get("kazanilanpuan"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cik();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Sonuc.this, Anasayfa.class);
                startActivity(intocan);
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
                .setMessage("Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
    }
}
