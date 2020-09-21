package com.example.doorsteptailors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tailor_info extends AppCompatActivity implements View.OnClickListener {
    String Name,Username,Address,PhoneNo,email,password;
    EditText shopName,experience;
    Button button;
    DatabaseHelper databaseHelper;
    UserDetails user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_info);
        shopName=findViewById(R.id.TailorShopId);
        experience=findViewById(R.id.TailorExperienceId);
        button=findViewById(R.id.TailorinfoButtonId);
        button.setOnClickListener(this);
        databaseHelper=new DatabaseHelper(this);
        user=new UserDetails();

    }

    @Override
    public void onClick(View view) {
        String Shopname=shopName.getText().toString();
        String Experience=experience.getText().toString();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Name=bundle.getString("Name");
            Username=bundle.getString("Username");
            Address=bundle.getString("Address");
            PhoneNo=bundle.getString("PhoneNo");
            email=bundle.getString("email");
            password=bundle.getString("password");
        }
        user.setname (Name);
        user.setusername(Username);
        user.setaddress(Address);
        user.setConNo(PhoneNo);
        user.setEmail(email);
        user.setPw(password);
        user.setShopName(Shopname);
        user.setWorkingExperience(Experience);
        user.setAvailability("yes");
        user.setRating("0");

        if(view.getId()== R.id.TailorinfoButtonId)
        {
            long rowId = databaseHelper.InsertTailor(user);
            if(rowId>0)
            {
                Toast.makeText(getApplicationContext(),"Row :"+rowId+"has inserted in Tailor Table",Toast.LENGTH_LONG).show();
                Intent signin = new Intent(getApplicationContext(),DeliveryManInfo.class);
                startActivity(signin);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Nooo insertion",Toast.LENGTH_LONG).show();
            }
        }
    }
}
