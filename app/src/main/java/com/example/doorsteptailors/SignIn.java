package com.example.doorsteptailors;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity  {
    private EditText mEmail,mPassword;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView textView;
    private Button button;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);
        radioGroup=findViewById(R.id.RadioGRPsId);
        textView=findViewById(R.id.createaccounttext);
        button=findViewById(R.id.loginBtn);


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email =mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            int getid = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(getid);
            String select = radioButton.getText().toString();
            Intent intent =new Intent(SignIn.this,MainActivity.class);
            startActivity(intent);

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword.setError("Password Must be >= 6 Characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // authenticate the user

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignIn.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else {
                        Toast.makeText(SignIn.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });

        }
    });




        //Toast.makeText(getApplicationContext(),"Onclick is working",Toast.LENGTH_LONG).show();
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(SignIn.this,SignUp.class);
        startActivity(intent);
    }
});



    }

}
