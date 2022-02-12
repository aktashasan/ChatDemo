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

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtUsername,txtEmail,txtPassword;
    private Button btnRegister,btnLoginRegister;

    private FirebaseAuth auth;


    public void init(){
        auth=FirebaseAuth.getInstance();

        txtUsername =(EditText) findViewById(R.id.txtUsernameRegister);
        txtEmail=(EditText) findViewById(R.id.txtEmailRegister);
        txtPassword=(EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnLoginRegister=(Button) findViewById(R.id.btnLoginRegister);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivty();
            }
        });
    }

    private void goToLoginActivty() {
        Intent loginIntent =new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void createNewAccount() {
    String username =txtUsername.getText().toString();
    String email =txtEmail.getText().toString();
    String Password =txtPassword.getText().toString();
    if(TextUtils.isEmpty(email)){
        Toast.makeText(this,"Email alanı boş olamaz",Toast.LENGTH_LONG).show();
    }else if(TextUtils.isEmpty(Password)){
        Toast.makeText(this,"Şifre alanı boş olamaz",Toast.LENGTH_LONG).show();
    }else{
        auth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Hesabınız Başarılı bir şekilde oluşturuldu!",Toast.LENGTH_LONG).show();
                    Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,"Bir Hata oluştu !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
}