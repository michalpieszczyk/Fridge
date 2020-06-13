package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference reference;
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected Button scanBtn;
    protected Button refreshBtn;
    protected Date obecnaData;
    protected String zeskanowanyKod;
    //protected Produkt produkt;

    ListView listViewMembers;

    List<Member> memberList;

    TextView a,b,c,d;
    Member member;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference = FirebaseDatabase.getInstance().getReference();

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);

        refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(this);

        listViewMembers = (ListView) findViewById(R.id.listViewMember);

        memberList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference myRef = database.getReference("Stan");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                memberList.clear();
                for(DataSnapshot memberSnapshot: dataSnapshot.getChildren()){
                    Member member = memberSnapshot.getValue(Member.class);

                    memberList.add(member);
                }

                StanList adapter = new StanList(MainActivity.this, memberList);
                listViewMembers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        private void refreshDb(){

        //REFRESH

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

                  builder.setTitle("Wynik Skanowania  to: ");


                    DatabaseReference myRef = database.getInstance().getReference("Produkty");
                    DatabaseReference myRef1 = myRef.child(zeskanowanyKod);
                    DatabaseReference myRefnazwa = myRef1.child("nazwa");
                    DatabaseReference myRefgramatura = myRef1.child("gramatura");

                    myRefnazwa.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Toast.makeText(getApplicationContext(),"======="+dataSnapshot.getValue(String.class),Toast.LENGTH_LONG).show();
                            
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        };
                    });







                    builder.setPositiveButton("Dodaj do lodówki", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            member = new Member();

                            DatabaseReference myRef = database.getReference("Stan");
                            Date dt = new Date();
                            SimpleDateFormat simpleDate =  new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                            String strDt = simpleDate.format(dt);

                            member.setKodKreskowy(zeskanowanyKod);
                            member.setObecnaData(strDt);

                            myRef.push().setValue(member);

                            Toast.makeText(getApplicationContext(),"Pomyślnie dodano produkt do lodówki",Toast.LENGTH_LONG).show();

                            //myRef.child(strDt).setValue(zeskanowanyKod);

                        }
                    }).setNegativeButton("Zabierz z lodówki", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            DatabaseReference myRef = database.getReference("Stan");
                            //DatabaseReference myRef = database.getReference("Stan");



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