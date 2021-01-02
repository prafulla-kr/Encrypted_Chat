package com.prafulla.encryptedchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageSent extends AppCompatActivity {

    EditText inputText, inputPassword;
    Button encButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_sent);
        inputText = (EditText) findViewById(R.id.inputText);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        encButton = (Button) findViewById(R.id.encButton);

        encButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageSent.this, MainActivity.class);
                String gettext=inputText.getText().toString();
                String getpass=inputPassword.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("text",gettext);
                bundle.putString("pass", getpass);
               // Inbox fragobj = new Inbox();
              //  fragobj.setArguments(bundle);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}