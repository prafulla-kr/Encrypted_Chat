package com.prafulla.encryptedchat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Inbox extends Fragment {

    View view;
    TextView textView;
    String AES = "AES";
    String outputString;
    private static final String TAG = "Inbox";

    public Inbox() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inbox, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.txtData);

    }


    public void displayReceivedData(String inputMessage, String inputPassword) {
        //textView.setText("Message received: "+inputMessage);
        String text = inputMessage;
        String pass = inputPassword;
        try {
            outputString = encrypt(text, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView.setText(outputString);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Enter Secret Key");
                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.alert_dialog_enter_secretkey, (ViewGroup) getView(), false);
                EditText secretKey = (EditText) viewInflated.findViewById(R.id.secretKey);
                builder.setView(viewInflated);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            outputString = decrypt(outputString, secretKey.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Wrong Secret Key", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        textView.setText(outputString);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    private String encrypt(String Data, String inputPassword) throws Exception {
        SecretKeySpec key = generateKey(inputPassword);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private String decrypt(String outputString, String inputPassword) throws Exception {
        SecretKeySpec key = generateKey(inputPassword);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedVal = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decVal = cipher.doFinal(decodedVal);
        String decryptedValue = new String(decVal);
        return decryptedValue;
    }

    private SecretKeySpec generateKey(String inputPassword) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = inputPassword.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }


}