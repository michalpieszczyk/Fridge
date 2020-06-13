package com.example.qr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StanList extends ArrayAdapter<Member> {

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
        TextView textViewNazwa = (TextView) listViewItem.findViewById(R.id.textViewNazwa);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.textViewData);

        Member member = memberList.get(position);
        textViewNazwa.setText(member.getKodKreskowy());
        textViewData.setText(member.getObecnaData());


        return listViewItem;

    }
}
