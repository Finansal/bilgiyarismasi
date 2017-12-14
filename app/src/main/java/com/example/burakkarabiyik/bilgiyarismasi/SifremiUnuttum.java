package com.example.burakkarabiyik.bilgiyarismasi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SifremiUnuttum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);

        Button gonder=(Button)findViewById(R.id.sifremigonder);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FirebaseAuth.getInstance().sendPasswordResetEmail((((EditText)findViewById(R.id.editText15)).getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SifremiUnuttum.this, "Şifreniz Gönderildi",
                                                Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                }
                            });
                }catch (Exception e)
                {
                    Toast.makeText(SifremiUnuttum.this, "Böyle Mail yoktur.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intocan = new Intent(SifremiUnuttum.this, Giris.class);
        startActivity(intocan);
        SifremiUnuttum.this.finishAffinity();
    }
}
