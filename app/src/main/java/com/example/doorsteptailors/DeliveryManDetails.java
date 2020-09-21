package com.example.doorsteptailors;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeliveryManDetails extends AppCompatActivity implements View.OnClickListener {
    EditText dName,dPhnNo,dGender;
    Button button;
    DeliveryManInfo deliveryManInfo;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_man_details);

        dName=findViewById(R.id.D_nameId);
        dPhnNo=findViewById(R.id.D_phnNoId);
        dGender=findViewById(R.id.D_GenderId);
        button=findViewById(R.id.D_ButtonId);

        button.setOnClickListener(this);

        //databaseHelper.getWritableDatabase();
        deliveryManInfo = new DeliveryManInfo();
    }

    @Override
    public void onClick(View view) {
        String d_Name = dName.getText().toString();
        String d_PhnNo = dPhnNo.getText().toString();
        String d_Gender = dGender.getText().toString();

        deliveryManInfo.setD_Name(d_Name);
        deliveryManInfo.setD_PhnNo(d_PhnNo);
        deliveryManInfo.setD_Gender(d_Gender);

        if(view.getId() == R.id.D_ButtonId)
        {

        }
    }
}
