package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RestaurantManage extends AppCompatActivity {
    Button signout, setMenu, checkStatus;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_manage);
        signout = (Button) findViewById(R.id.signout);
        setMenu = (Button) findViewById(R.id.btnSetMenu);
        checkStatus = (Button) findViewById(R.id.btnCheckStatus);

        setMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantManage.this, SetMenu.class));
                finish();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RestaurantManage.this, LoginScreen.class));
                finish();
            }
        });
    }
}
