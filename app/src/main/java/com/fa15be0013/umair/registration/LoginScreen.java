package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends Base {

    private FirebaseAuth mAuth;
    //    String id, userId = getUid();
    String userId, id;
    FirebaseDatabase database;
    DatabaseReference ref, refs;
    Intent i ;
//    DatabaseReference ref = database.getReference("Users/" + userId + "/id");;

    TextInputEditText txtEmail, txtPassword;
    AppCompatTextView btnRegister;
    ImageView btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            //loginCheck();
            showProgressDialog();
            loginCheck();
//            Intent i = new Intent(LoginScreen.this, MainActivity.class);
//            startActivity(i);
//            finish();
        } else {
            setContentView(R.layout.activity_login_screen);

            txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
            txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
            btnRegister = (AppCompatTextView) findViewById(R.id.btnRegister);

            txtEmail.setTextColor(Color.YELLOW);
            txtPassword.setTextColor(Color.YELLOW);

            btnLogin = (ImageView) findViewById(R.id.btnLogin);
            btnSignup = (ImageView) findViewById(R.id.btnSignup);


            txtEmail.setHintTextColor(Color.WHITE);
            mAuth = FirebaseAuth.getInstance();

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Login();
                }
            });

            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   i = new Intent(LoginScreen.this, UserRegister.class);
                    startActivity(i);
                }
            });

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginScreen.this, RestaurantRegister.class);
                    startActivity(i);
                }
            });
        }
    }


    private void Login() {

        if (!validateForm()) {
            return;
        }
        showProgressDialog();

        final String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            hideProgressDialog();
                            Toast.makeText(LoginScreen.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();

                        } else {
                            Intent intent = new Intent(LoginScreen.this, Linked.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                            hideProgressDialog();
                        }
                    }
                });
    }


    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(txtEmail.getText().toString())) {
            txtEmail.setError("Required");
            Toast.makeText(this, "Email Can't Be Empty", Toast.LENGTH_LONG).show();
            result = false;

        }

        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            Toast.makeText(this, "Password Can't Be Empty", Toast.LENGTH_LONG).show();
            result = false;

        }

        return result;
    }

    private void loginCheck() {
        userId = getUid();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users/" + userId + "/id");
        refs = database.getReference("Registration/" + userId + "/id");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id = (String) dataSnapshot.getValue();
                if (id != null) {
                    if (id.equals("Users100")) {
                        i = new Intent(LoginScreen.this, UserDashBoard.class);
                        startActivity(i);
                        finish();
                        hideProgressDialog();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        refs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
                id = (String) dataSnapshot.getValue();
                if (id != null) {
                    if (id.equals("Rest100")) {
                        i = new Intent(LoginScreen.this, RestaurantDashBoard.class);
                        startActivity(i);
                        finish();
                        hideProgressDialog();
                    }
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError){
            }
        });
    }


//    private void loginCheck() {
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                id = (String) dataSnapshot.getValue();
//
//                if (id.equals("Users100")) {
//                    Intent i = new Intent(LoginScreen.this, MainActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//                else
//                {
//                    Intent i = new Intent(LoginScreen.this, RestaurantManage.class);
//                    startActivity(i);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

}
