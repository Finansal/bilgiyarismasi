package com.burakkarabiyik.finansal.bilgiyarismasi;

/**
 * Created by Burak on 1.12.2017.
 */

public class Soru {
    public String id,numarasi,Kategori,cvp1,cvp2,cvp3,cvp4,sorusu,dogru;
    public String  puan;
    public Soru(){}
    public Soru(String id,String numarasi,String Kategori,String cvp1,String cvp2,String cvp3,String cvp4,String sorusu,String puan,String dogru){
        this.id=id;
        this.numarasi=numarasi;
        this.Kategori=Kategori;
        this.cvp1=cvp1;
        this.cvp2=cvp2;
        this.cvp3=cvp3;
        this.cvp4=cvp4;
        this.sorusu=sorusu;
        this.puan=puan;
        this.dogru=dogru;
    }
}
