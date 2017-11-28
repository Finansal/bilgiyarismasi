package com.example.burakkarabiyik.bilgiyarismasi;

/**
 * Created by Burak on 28.11.2017.
 */

public class Kullanici {
    private String Kullaniciadi,sifre,email;
    public Kullanici(){}
    public Kullanici(String ad,String sifre,String email){
        Kullaniciadi=ad;
        this.sifre=sifre;
        this.email=email;
    }
    public String getAd(){return Kullaniciadi;}
    public String getSifre(){return sifre;}
    public String getEmail(){return email;}

    public  void setad(String ad){this.Kullaniciadi=ad;}
    public  void setSifre(String sifre){this.sifre=sifre;}
    public  void setMail(String mail){this.email=mail;}
    /*
     if(ad.isEmpty()&&sifre.isEmpty()&&sifre2.isEmpty()&&email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Boş bırakma", Toast.LENGTH_LONG).show();
//Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Adi:"+ad+"Sifre:"+sifre+":Sifre2:"+sifre2+":Email:"+email, Toast.LENGTH_LONG).show();
//Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                    if(sifre.equals(sifre2))
                    {

                        //Kullanıcı kontrol kullanici adi ve email
                        DatabaseReference dbref= db.getReference("Kullanici");
                        String key=dbref.push().getKey();
                        DatabaseReference keyli=db.getReference("Kullanici/"+key);
                        keyli.setValue(new Kullanici(ad,sifre,email));

                        Toast.makeText(getApplicationContext(), "Kayıt Sağlandı", Toast.LENGTH_LONG).show();
//Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                        Intent intocan = new Intent(Kayitol.this, Giris.class);
                        startActivity(intocan);
                    }
                    else
                    {Toast.makeText(getApplicationContext(), "Şifreler Uyuşmuyor", Toast.LENGTH_LONG).show();
//Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                    }
                }
     */
}
