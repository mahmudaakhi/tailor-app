package com.example.doorsteptailors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {
    EditText product_name,product_price,discountpersentage;
    String name, price, discount, catagory;
    Button add;
    RadioGroup radioGroup;
    RadioButton radioButton;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        product_name=findViewById(R.id.product_name);
        product_price=findViewById(R.id.product_price);
        discountpersentage=findViewById(R.id.discount_persentage);
        add=findViewById(R.id.addbutton);
        radioGroup = findViewById(R.id.RadioGRPsId);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                name = product_name.getText().toString();
                price = product_price.getText().toString();
                discount = discountpersentage.getText().toString();
                catagory = radioButton.getText().toString();

                ProductClass productClass = new ProductClass(name, price, discount, catagory, "false");
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");
                Intent intent=new Intent(AddProduct.this,OrderDetails.class);
                startActivity(intent);

            }
        });
    }
}
