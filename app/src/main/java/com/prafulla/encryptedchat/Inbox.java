package com.prafulla.encryptedchat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Inbox extends Fragment  {

    View view;
    TextView textView;
    private static final String TAG = "Inbox";

    public Inbox() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inbox, container, false);

       /* LinearLayout person1 = view.findViewById(R.id.person1);
        person1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent person1intent = new Intent(getActivity(), MessageReceived.class);
                String text = getArguments().getString("text");
                String pass = getArguments().getString("pass");
                Bundle bundle = new Bundle();
                bundle.putString("text",text);
                bundle.putString("pass", pass);
                person1intent.putExtras(bundle);
                startActivity(person1intent);
                displayReceivedData(Inbox.this.toString());
                Sent sent = new Sent();
                sent.setTargetFragment(Inbox.this,1);
                //sent.show(getFragmentManager(),"Sent");
                sent.getFragmentManager();
            }
        });*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.txtData);
    }
/*
    TextView txtData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_inbox, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtData = (TextView)view.findViewById(R.id.txtData);
    }*/

    public void displayReceivedData(String inputMessage)
    {
        textView.setText("Data received: "+inputMessage);
    }
}