package com.fa15be0013.umair.registration;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Model.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RestaurantRegister extends Base {

    EditText txtName, txtEmail, txtPassword, txtRestName, txtRestAddress, txtCity;
    ImageView btnRegister, bitmap11, btnChoose;
    private static final String REQUIRED = "Required";
    String incryptCode;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference sReference;
    private DatabaseReference mReference;

    public static final String STORAGE_PATH = "images/";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);

        Initialize();

        sReference = FirebaseStorage.getInstance().getReference();
        mReference = FirebaseDatabase.getInstance().getReference();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }
                  });

        bitmap11 = (ImageView)findViewById(R.id.bitmap);
        btnChoose = (ImageView)findViewById(R.id.btnChoose);
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
                    .addOnCompleteListener(RestaurantRegister.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                hideProgressDialog();
                                Toast.makeText(RestaurantRegister.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                onAuthSuccess(task.getResult().getUser());
                                hideProgressDialog();
                            }
                        }
                    });

        }

    private void onAuthSuccess(FirebaseUser user) {
        final String name, email, password, restName, restAddress, city;


        String id = "Rest100";
        name = txtName.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        restName = txtRestName.getText().toString();
        restAddress = txtRestAddress.getText().toString();
        city = txtCity.getText().toString();

        String[] Code = restName.split(" ");
        String decode = "";
        for(int i = 0 ; i < Code.length ; i++)
        {
            decode += Code[i];
        }

        decode += "123";
        incryptCode = decode;

        writeNewUser(user.getUid(), id , name, email, password, restName, restAddress, city);
        Intent i = new Intent(RestaurantRegister.this,RestaurantDashBoard.class);
        i.putExtra("name",name);
        i.putExtra("email",email);
        startActivity(i);
        finish();
    }



    private void writeNewUser(String uid, String id, String name, String email, String pass, String RestName, String RestAdd, String city) {
        Restaurant Rest = new Restaurant(uid, id, name, email, pass, RestName, RestAdd, city);
        Restaurant restaurant = new Restaurant(RestName);
        String image = "URL";
        Restaurant restaurant1 = new Restaurant(uid, RestName, image);
        mDatabase.child("Registration").child(uid).setValue(Rest);
        mDatabase.child("Restaurant").child(incryptCode).setValue(restaurant1);
    }

//03233213911
    private boolean validateForm() {
        boolean result = true;

        if(TextUtils.isEmpty(txtName.getText().toString()))
        {
            txtName.setError(REQUIRED);
            result = false;
        }

        if(TextUtils.isEmpty(txtEmail.getText().toString()))
        {
            txtEmail.setError(REQUIRED);
            result = false;
        }

        if(TextUtils.isEmpty(txtPassword.getText().toString()))
        {
            txtPassword.setError(REQUIRED);
            result = false;
        }

        if(TextUtils.isEmpty(txtRestName.getText().toString()))
        {
            txtRestName.setError(REQUIRED);
            result = false;
        }

        if(TextUtils.isEmpty(txtRestAddress.getText().toString()))
        {
            txtRestAddress.setError(REQUIRED);
            result = false;
        }

        if(TextUtils.isEmpty(txtCity.getText().toString()))
        {
            txtCity.setError(REQUIRED);
            result = false;
        }
        if (txtPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }


    private void Initialize() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRestName = (EditText) findViewById(R.id.txtRestName);
        txtRestAddress = (EditText) findViewById(R.id.txtRestAddress);
        txtCity = (EditText) findViewById(R.id.txtCity);

        btnRegister = (ImageView) findViewById(R.id.btnRegister);

        txtEmail.setTextColor(Color.YELLOW);
        txtPassword.setTextColor(Color.YELLOW);
        txtRestName.setTextColor(Color.YELLOW);
        txtCity.setTextColor(Color.YELLOW);
        txtRestAddress.setTextColor(Color.YELLOW);
        txtName.setTextColor(Color.YELLOW);


    }

//    public void browseImages(View view)
//    {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"select Image"),0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 0 && resultCode == RESULT_OK){
//            uri= data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                bitmap11.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public String getActuallImage(Uri uri)
//    {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
}
