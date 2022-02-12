package com.veritabanlari.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager vpMain2;
    private TabLayout tabsMain2;
    private TabsAdapter tabsAdapter;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    public void init(){
       vpMain2=(ViewPager) findViewById(R.id.vpMain);
       tabsAdapter= new TabsAdapter(getSupportFragmentManager());
       vpMain2.setAdapter(tabsAdapter);

       auth =FirebaseAuth.getInstance();
       currentUser=auth.getCurrentUser();

       tabsMain2=(TabLayout) findViewById(R.id.tabsMain);
       tabsMain2.setupWithViewPager(vpMain2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    @Override
    protected void onStart() {
        if (currentUser == null){
            Intent MainIntent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(MainIntent);
            finish();
        }
        super.onStart();
    }
//Menu için
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        super.onOptionsItemSelected(item);//çıkış seçeneğini işlevsel hale getirmek için
        if(item.getItemId()==R.id.mainLogout){
            auth.signOut();
            Intent loginIntent = new Intent(MainActivity2.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        return true;
    }
}