package com.burakkarabiyik.finansal.bilgiyarismasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
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
    ProgressDialog pDialog;
    String id;
    String mod,kategori;
    int cozulensorusayisi=0;
    int rastgele,sonuc;
    int dogru=0,yanlis=0;
    List<String> sorunumaralari = new ArrayList<String>();
    TextView txtsure;
    //////////////////////
    private SensorManager sm;
    private float acelVal;
    private float acelLast;
    private float shake;
    /////////////////
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriler);





    }

    void cikartbeni()
    {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Kategoriler.this, Butunkategoriler.class);
                        startActivity(intent);
                        Kategoriler.this.finishAffinity();
                    }
                }).setNegativeButton("Hayır", null).show();
    }
    void yoket(String cvp)
    {
        Toast.makeText(Kategoriler.this, cvp,
                Toast.LENGTH_SHORT).show();




    }

    @Override
    protected void onStart() {
        new Post().execute();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Kategoriler.this, Butunkategoriler.class);
                        startActivity(intent);
                        Kategoriler.this.finishAffinity();
                    }
                }).setNegativeButton("Hayır", null).show();
    }

    void goster()
    {
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
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
        if(mod.equals(Integer.toString(cozulensorusayisi)))
        {
            bitir();

        }
    }



    private final SensorEventListener sensorListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x=event.values[0];
            float y=event.values[1];
            float z=event.values[2];

            acelLast=acelVal;
            acelVal=(float)(Math.sqrt(Double.parseDouble(Float.toString((x*x+y*y+z*z)))));
            float delta=acelVal-acelLast;
            shake=shake*0.9f+delta;
            if(shake>35)
            {
                sayiuret();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };



    void soru(final String sorum)
    {

                        btnOyunBaslatClick();


        Veritabani vt=new Veritabani(Kategoriler.this);
        ArrayList<HashMap<String, String>> veriler = vt.VeriListele(1,"Sinema");
        for (int i=0;i<veriler.size();i++)
        {
            if(veriler.get(i).get("Kategori").equals(kategori) &&veriler.get(i).get("Numarasi").equals(sorum))
            {
                edtsoru.setText(veriler.get(i).get("Soru").toString());
                a.setText(veriler.get(i).get("A").toString());
                b.setText(veriler.get(i).get("B").toString());
                c.setText(veriler.get(i).get("C").toString());
                d.setText(veriler.get(i).get("D").toString());
                cevap=veriler.get(i).get("Dogru").toString();
                degeri=veriler.get(i).get("Puan").toString();
            }
        }


                        TextView tvdeger=(TextView)findViewById(R.id.textView5);
                        tvdeger.setText("Sorunun Değeri : "+degeri);


        soru=sorum;
    }


    class Post extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() { // Post tan önce yapılacak işlemler. Yükleniyor yazısını(ProgressDialog) gösterdik.
            pDialog = new ProgressDialog(Kategoriler.this);
            pDialog.setMessage("Yükleniyor...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false); // ProgressDialog u iptal edilemez hale getirdik.
            pDialog.show();
        }

        protected Void doInBackground(Void... unused) { // Arka Planda yapılacaklar. Yani Post işlemi



            sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
            sm.registerListener(sensorListener,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
            a=(Button)findViewById(R.id.button11);
            b=(Button)findViewById(R.id.button12);
            c=(Button)findViewById(R.id.button13);
            d=(Button)findViewById(R.id.button14);
            cik=(Button)findViewById(R.id.button15);
            acelVal=SensorManager.GRAVITY_EARTH;
            acelLast=SensorManager.GRAVITY_EARTH;

            Bundle extras = getIntent().getExtras();
            kategori=extras.getString("kategori");
            //shake=0.00f;
            txtsure=(TextView)findViewById(R.id.textView4);
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

            edtsoru=(TextView)findViewById(R.id.editText12);


            final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("SoruSayisi");

            final Query reff3=ref3.orderByChild("Kategori").equalTo(kategori);
            reff3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        //child is each element in the finished list

                        Map<String, Object> message = (Map<String, Object>) child.getValue();
                        mod=(String)message.get("SoruSayisi");

                        ///////////
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
                            {
                                sayiuret();

                            }
                        }


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


            final Button bt=(Button)findViewById(R.id.button10);
            final Button ikikat=(Button)findViewById(R.id.button9);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    char[] cevaplar={'a','b','c','d'};
                    for (int i=0;i<4;i++)
                    {
                        if(cevaplar[i]==cevap.charAt(0))
                        {
                            cevaplar[i]='0';


                        }
                    }


                    int k=2;



                    do{

                        rastgele=r.nextInt(3);
                        if(cevaplar[Integer.parseInt(Integer.toString(rastgele))]!='0')
                        {



                            String cvp=Character.toString(cevaplar[Integer.parseInt(Integer.toString(rastgele))]);

                            if(cvp.equals("a"))
                            {
                                a.setVisibility(View.INVISIBLE);
                            }

                            if(cvp.equals("b"))
                            {
                                b.setVisibility(View.INVISIBLE);
                            }

                            if(cvp.equals("c"))
                            {
                                c.setVisibility(View.INVISIBLE);
                            }
                            if(cvp.equals("d"))
                            {
                                d.setVisibility(View.INVISIBLE);
                            }
                            k--;
                            cevaplar[rastgele]='0';
                        }
                    }while (k!=0);
                    bt.setVisibility(View.INVISIBLE);

                }
            });
            //Buttonlarrr

            ikikat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    degeri=Integer.toString(2*Integer.parseInt(degeri));
                    ikikat.setVisibility(View.INVISIBLE);
                }
            });

            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sorgu();
                    suredurdur();
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
                    goster();
                }
            });
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sorgu();
                    suredurdur();
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

                    goster();
                }
            });
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sorgu();
                    suredurdur();
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

                    goster();
                }
            });
            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sorgu();
                    suredurdur();
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
                    goster();
                }
            });

            cik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cikartbeni();

                }
            });


            return null;
        }

        protected void onPostExecute(Void unused) { //Posttan sonra
            pDialog.dismiss();  //ProgresDialog u kapatıyoruz.

        }
    }
    CountDownTimer t;
    public void btnOyunBaslatClick() {
        t= new CountDownTimer(45000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Kalan süreyi saniye cinsine çevirip ekran alanına yazıyoruz.

                txtsure.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                // Süre tamamlandığını bildiriyoruz.
                sayiuret();
                suredurdur();


                //sayimBitti = true;
            }
        }.start();

    }
    public void suredurdur()
    {
        t.cancel();
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
                        Kategoriler.this.finishAffinity();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
