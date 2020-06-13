package com.example.qr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StanList extends ArrayAdapter<Member> {

    DatabaseReference reference;
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Activity context;
    private List<Member> memberList;

    public StanList(Activity context, List<Member> memberList){
        super(context, R.layout.list_layout, memberList);
        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        final TextView textViewNazwa = (TextView) listViewItem.findViewById(R.id.textViewNazwa);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.textViewData);

        Member member = memberList.get(position);
        DatabaseReference myRef = database.getInstance().getReference("Produkty");
        DatabaseReference myRef1 = myRef.child(member.getKodKreskowy());
        DatabaseReference myRefnazwa2 = myRef1.child("nazwa");
        myRefnazwa2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textViewNazwa.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            };
        });
       //textViewNazwa.setText(member.getKodKreskowy());
        textViewData.setText(member.getObecnaData());


        return listViewItem;

    }
}
