package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends Base {
    EditText txtName, txtEmail, txtPassword, txtGender, txtAge, txtCity;
    ImageView btnSignup;
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        Initialize();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {

        if(!validateForm())
        {
            return;
        }

        showProgressDialog();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();





        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(UserRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(UserRegister.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            hideProgressDialog();
                        } else {
                            onAuthSuccess(task.getResult().getUser());
                            hideProgressDialog();
                        }
                    }
                });

    }

    private void onAuthSuccess(FirebaseUser user) {
        final String name, email, password, gender, age, city;


        String id = "Users100";
        name = txtName.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        gender = txtGender.getText().toString();
        age = txtAge.getText().toString();
        city = txtCity.getText().toString();

        writeNewUser(user.getUid(), id , name, email, password, gender, age, city);
        startActivity(new Intent(UserRegister.this, UserDashBoard.class));
        finish();
    }

    private void writeNewUser(String uid, String id, String name, String email, String pass, String gender, String age, String city) {
        User user = new User(uid, id, name, email, pass, gender, age, city);

        mDatabase.child("Users").child(uid).setValue(user);
    }

    private boolean validateForm() {
        boolean result = true;

        if(TextUtils.isEmpty(txtName.getText().toString())
                ||TextUtils.isEmpty(txtEmail.getText().toString())
                ||TextUtils.isEmpty(txtPassword.getText().toString())
                ||TextUtils.isEmpty(txtGender.getText().toString())
                ||TextUtils.isEmpty(txtAge.getText().toString())
                ||TextUtils.isEmpty(txtCity.getText().toString()))
        {
            Toast.makeText(this, "Required Fields are Empty", Toast.LENGTH_LONG).show();
            result = false;
        }

//        if(TextUtils.isEmpty(txtEmail.getText().toString()))
//        {
//            txtEmail.setError(REQUIRED);
//            result = false;
//        }
//
//        if(TextUtils.isEmpty(txtPassword.getText().toString()))
//        {
//            txtPassword.setError(REQUIRED);
//            result = false;
//        }
//
//        if(TextUtils.isEmpty(txtGender.getText().toString()))
//        {
//            txtGender.setError(REQUIRED);
//            result = false;
//        }
//
//        if(TextUtils.isEmpty(txtAge.getText().toString()))
//        {
//            txtAge.setError(REQUIRED);
//            result = false;
//        }
//
//        if(TextUtils.isEmpty(txtCity.getText().toString()))
//        {
//            txtCity.setError(REQUIRED);
//            result = false;
//        }
//        if (txtPassword.length() < 6) {
//            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
//            result = false;
//        }
        return result;
    }

    private void Initialize() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtGender = (EditText) findViewById(R.id.txtGender);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtCity = (EditText) findViewById(R.id.txtCity);

        btnSignup = (ImageView) findViewById(R.id.btnSignup);

        txtEmail.setTextColor(Color.YELLOW);
        txtPassword.setTextColor(Color.YELLOW);
        txtAge.setTextColor(Color.YELLOW);
        txtCity.setTextColor(Color.YELLOW);
        txtGender.setTextColor(Color.YELLOW);
        txtName.setTextColor(Color.YELLOW);


    }
}
