package com.burakkarabiyik.finansal.bilgiyarismasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Burak on 17.12.2017.
 */

public class Veritabani extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "Sorular";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLO_SORU = "Soru";
    private static final String ROW_ID = "1";
    private static final String ROW_soru = "Soru";
    private static final String ROW_A = "A";
    private static final String ROW_B = "B";
    private static final String ROW_C = "C";
    private static final String ROW_D = "D";
    private static final String ROW_dogru = "Dogru";
    private static final String ROW_Puan = "Puan";
    private static final String ROW_numarasi = "Numarasi";
    private static final String ROW_kategori = "Kategori";

    public Veritabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  // Databesi oluşturuyoruz.Bu methodu biz çağırmıyoruz. Databese de obje oluşturduğumuzda otamatik çağırılıyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLO_SORU + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ROW_soru + " TEXT,"
                + ROW_A + " TEXT,"
                + ROW_B+ " TEXT,"
                + ROW_C+ " TEXT,"
                + ROW_D+ " TEXT,"
                + ROW_dogru+ " TEXT,"
                + ROW_numarasi+ " TEXT,"
                + ROW_kategori+ " TEXT,"
                + ROW_Puan+ " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void VeriEkle(String id, String soru, String A,String B,String C,String D,String dogru,String num,String Kategori,String puan){
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();

            cv.put(ROW_soru, soru);
            cv.put(ROW_A, A);
            cv.put(ROW_B, B);
            cv.put(ROW_C, C);
            cv.put(ROW_D, D);
            cv.put(ROW_dogru, dogru);
            cv.put(ROW_numarasi, num);
            cv.put(ROW_kategori, Kategori);
            cv.put(ROW_Puan, puan);
            db.insert(TABLO_SORU, null,cv);
        }catch (Exception e){
        }
        db.close();
    }

    public ArrayList<HashMap<String,String>> VeriListele(int a,String kategori){

        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {ROW_ID,ROW_soru,ROW_A,ROW_B,ROW_C,ROW_D,ROW_dogru,ROW_numarasi,ROW_kategori,ROW_Puan};
            Cursor cursor = db.query(TABLO_SORU, stunlar,null,null,null,null,null);
            while (cursor.moveToNext()){
                HashMap<String, String> veriler = new HashMap<String, String>();

               for(int i=0; i<cursor.getColumnCount();i++)
                    {
                        veriler.put(cursor.getColumnName(i), cursor.getString(i));

                    }
                liste.add(veriler);
            }
        }catch (Exception e){
        }
        db.close();
        return liste;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_SORU);
        onCreate(db);
    }
}
