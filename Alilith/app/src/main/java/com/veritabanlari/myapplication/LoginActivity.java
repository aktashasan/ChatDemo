package com.veritabanlari.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail,txtPassword;
    private Button btnLogin,btnRegisterLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){
        txtEmail=(EditText) findViewById(R.id.txtEmailLogin);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegisterLogin=(Button) findViewById(R.id.btnRegisterLogin);

        auth=FirebaseAuth.getInstance();
        currentUser =auth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterActivty();
            }
        });
    }

    private void goToRegisterActivty() {
        Intent registerIntent =new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    private void loginUser() {
        String email =txtEmail.getText().toString();
        String Password =txtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email alanı boş olamaz",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Şifre alanı boş olamaz",Toast.LENGTH_LONG).show();
        }else{
            btnLogin.setEnabled(false);

            auth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Giriş Başarılı !",Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(LoginActivity.this,MainActivity2.class);
                        startActivity(mainIntent);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this,"Bir Hata oluştu !",Toast.LENGTH_LONG).show();
                   btnLogin.setEnabled(true);
                    }
            }
        });
        }
    }

}

