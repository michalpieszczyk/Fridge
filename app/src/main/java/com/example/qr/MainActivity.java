package com.example.qr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected Button scanBtn;
    protected Date obecnaData;
    protected String zeskanowanyKod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        scanCode();
    }

        private void scanCode(){

            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setCaptureActivity(CaptureAct.class);
            integrator.setOrientationLocked(false);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("Skanowanie kodu");
            integrator.initiateScan();
        }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null)
            {
                if (result.getContents() != null)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(result.getContents());
                    zeskanowanyKod = result.getContents();
                    builder.setTitle("Wynik Skanowania  to "+zeskanowanyKod+" data: "+ obecnaData);

                    builder.setPositiveButton("Dodaj do lodówki", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseReference myRef = database.getReference("Stan");
                            Date dt = new Date();
                            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                            String strDt = simpleDate.format(dt);
                            //myRef.setValue(zeskanowanyKod+";"date);
                            myRef.push().setValue(dt);
                            myRef.child(zeskanowanyKod);
                        }
                    }).setNegativeButton("Zabierz z lodówki", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    Toast.makeText(this, "Brak Wynikow", Toast.LENGTH_LONG).show();
                }
            }
            else{
                super.onActivityResult(requestCode, resultCode, data);
            }

        }

}