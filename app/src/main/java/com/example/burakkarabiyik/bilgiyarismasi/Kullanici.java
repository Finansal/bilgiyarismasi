package com.example.burakkarabiyik.bilgiyarismasi;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Burak on 28.11.2017.
 */
@IgnoreExtraProperties
public class Kullanici {
    public String Kullaniciadi,sifre,email,puan;
    public Kullanici(){}
    public Kullanici(String ad,String email,String sifre,String puan){
        Kullaniciadi=ad;
        this.sifre=sifre;
        this.email=email;
        this.puan=puan;
    }
    public String getAd(){return Kullaniciadi;}
    public String getSifre(){return sifre;}
    public String getEmail(){return email;}
    public String getpuan(){return puan;}

    public  void setad(String ad){this.Kullaniciadi=ad;}
    public  void setSifre(String sifre){this.sifre=sifre;}
    public  void setMail(String mail){this.email=mail;}
    public  void setpuan(String puan){this.puan=puan;}

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Kullaniciadi", Kullaniciadi);
        result.put("email", email);
        result.put("sifre", sifre);
        result.put("puan", puan);

        return result;
    }
}
