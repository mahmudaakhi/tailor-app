package com.example.doorsteptailors;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity  {
    public static final String TAG = "TAG";
    private EditText name,username,address,phnNo,Email,Password,ConfirmPassword;
    private RadioGroup radiogrp;
    private RadioButton radioBtn;
    private Button btn;
TextView alreadyhaveaccount;
    int selectedid;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    UserDetails user;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username= findViewById(R.id.username);
        phnNo= findViewById(R.id.phone);
        Email= findViewById(R.id.Email);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmpassword);
        radiogrp =findViewById(R.id.RadioGRPsId);
        btn=findViewById(R.id.reg_Btn);

        alreadyhaveaccount =findViewById(R.id.already_have);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user =new UserDetails();
   btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String Username= username.getText().toString();
           String PhoneNo= phnNo.getText().toString();
           String email= Email.getText().toString();
           final String password= Password.getText().toString();

           String confirmpassword = ConfirmPassword.getText().toString();

           user.setusername(Username);
           user.setConNo(PhoneNo);
           user.setEmail(email);
           user.setPw(password);


           int selectedid= radiogrp.getCheckedRadioButtonId();
           radioBtn=findViewById(selectedid);
           String selection=radioBtn.getText().toString();

           if(TextUtils.isEmpty(email)){
               Email.setError("Email is Required.");
               return;
           }

           if(TextUtils.isEmpty(password)){
               Password.setError("Password is Required.");
               return;
           }

           if(password.length() < 6){
               Password.setError("Password Must be >= 6 Characters");
               return;
           }


           // register the user in firebase

           fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){

                       // send verification link

                       FirebaseUser fuser = fAuth.getCurrentUser();
                       fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(SignUp.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                           }
                       });

                       Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                       userID = fAuth.getCurrentUser().getUid();
                       DocumentReference documentReference = fStore.collection("users").document(userID);
                       Map<String,Object> user = new HashMap<>();
                       user.put("fName",username);
                       user.put("email",Email);
                       user.put("phone",phnNo);
                       user.put("phone",password);
                       documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.d(TAG, "onFailure: " + e.toString());
                           }
                       });
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));

                   }else {
                       Toast.makeText(SignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           });


    }
   });
        //Toast.makeText(getApplicationContext(),"Onclick is working",Toast.LENGTH_LONG).show();
     alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(SignUp.this,SignIn.class);
             startActivity(intent);
         }
     });

    }
}
