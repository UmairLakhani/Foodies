package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Linked extends Base{
    public String userId, id, idR, fullname, email;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref, refs, refName, refEmail, refsName, refsEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked);
        showProgressDialog();
        userId = getUid();
        ref = database.getReference("Users/" + userId + "/id");
        refs = database.getReference("Registration/" + userId + "/id");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id = (String) dataSnapshot.getValue();

                Toast.makeText(Linked.this, fullname+" "+email, Toast.LENGTH_SHORT).show();

                if(id !=null) {
                    if (id.equals("Users100")) {
                        Intent i = new Intent(Linked.this, UserDashBoard.class);
                        i.putExtra("email",i.getStringExtra("email"));
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
            public void onDataChange(DataSnapshot dataSnapshot) {
                idR = (String) dataSnapshot.getValue();
                if(idR != null) {
                    if (idR.equals("Rest100")) {
                        Intent intent = new Intent(Linked.this, RestaurantDashBoard.class);
                        intent.putExtra("email",intent.getStringExtra("email"));
                        startActivity(intent);
                        finish();
                        hideProgressDialog();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
