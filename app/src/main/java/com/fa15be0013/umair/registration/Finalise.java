package com.fa15be0013.umair.registration;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Model.cartInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Finalise extends Base {

    DatabaseReference mDatabase;
    ImageView btnPlace, btnViewCart;
    FirebaseAuth mAuth;
    EditText txtPhone, txtAddress, txtInstructions;
    String id, restId, ODate, OrderId;
    cartInformation ctinfo;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalise);

        btnPlace = (ImageView)findViewById(R.id.btnPlaceorder);
        btnViewCart = (ImageView)findViewById(R.id.btnViewCart);

        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtPhone = (EditText)findViewById(R.id.txtphone);
        txtInstructions = (EditText)findViewById(R.id.txtinstructions);
        mAuth = FirebaseAuth.getInstance();




        intent = getIntent();
        restId = intent.getStringExtra("ResId");
        id = getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Orders/"+restId+"/"+id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot orderDate:dataSnapshot.getChildren()) {
                    ODate = orderDate.getKey();
                    for (DataSnapshot orderId:orderDate.getChildren()) {
                        OrderId = orderId.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ctinfo = new cartInformation();
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillInformation();
                Toast.makeText(Finalise.this, restId+" "+id+" "+ODate+" "+OrderId, Toast.LENGTH_SHORT).show();
                Toast.makeText(Finalise.this, ctinfo.getPhoneNumber()+" "+ctinfo.getAddress()+" "+ctinfo.getInstructions(), Toast.LENGTH_SHORT).show();
                addCartDetails(restId, id, OrderId, ODate, ctinfo);
                addNotification();
                Intent menu = new Intent(Finalise.this,ShowMenu.class);
                startActivity(menu);
                Toast.makeText(Finalise.this, "Order Placed Will Be Deliverd In 45 Minites", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addNotification() {
        if(mAuth.getCurrentUser().getUid() == restId) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.logofinal)
                            .setContentTitle("Order From User")
                            .setContentText(ODate + "\n" + ctinfo);

            Intent notificationIntent = new Intent(this, RestaurantDashBoard.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }
    public void addCartDetails(String restId, String Uid, String Orderid, String orderDate, cartInformation cartInformation)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("CartDetails").child(restId).child(Uid).child(orderDate).child(Orderid).setValue(cartInformation);
    }

    public void fillInformation()
    {
        ctinfo = new cartInformation();

        ctinfo.setPhoneNumber(txtPhone.getText().toString());
        ctinfo.setAddress(txtAddress.getText().toString());
        ctinfo.setInstructions(txtInstructions.getText().toString());
    }
}
