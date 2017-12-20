package com.burakkarabiyik.finansal.bilgiyarismasi;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Burak on 28.11.2017.
 */
@IgnoreExtraProperties
public class Kullanici {
    public String Kullaniciadi,sifre,email,puan,id,seviye;
    public Kullanici(){}
    public Kullanici(String id,String ad,String email,String sifre,String puan,String seviye){
        Kullaniciadi=ad;
        this.sifre=sifre;
        this.email=email;
        this.puan=puan;
        this.seviye=seviye;

    }
    public String getAd(){return Kullaniciadi;}
    public String getSifre(){return sifre;}
    public String getEmail(){return email;}
    public String getpuan(){return puan;}
    public String getid(){return id;}
    public String getSeviye(){return seviye;}

    public  void setad(String ad){this.Kullaniciadi=ad;}
    public  void setid(String id){this.id=id;}
    public  void setSifre(String sifre){this.sifre=sifre;}
    public  void setMail(String mail){this.email=mail;}
    public  void setpuan(String puan){this.puan=puan;}

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("Kullaniciadi", Kullaniciadi);
        result.put("email", email);
        result.put("sifre", sifre);
        result.put("puan", puan);

        return result;
    }
}
