package com.prafulla.encryptedchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Sent.SendMessage{
    
    private ViewPagerAdaptor viewPagerAdaptor;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public TextView inputDisplay;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());

        viewPagerAdaptor.addFragment(new Inbox(),"INBOX");
        viewPagerAdaptor.addFragment(new Sent(),"SENT");
        viewPagerAdaptor.addFragment(new Contacts(),"CONTACTS");
        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_inbox_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_send_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_contacts_24);

        getSupportFragmentManager().beginTransaction().add(R.id.viewPager,new Sent()).commit();
    }
    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        Inbox f = (Inbox) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
    }


}