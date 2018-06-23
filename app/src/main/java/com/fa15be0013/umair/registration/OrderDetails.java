package com.fa15be0013.umair.registration;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Adapter.CartCustomAdapter;
import com.fa15be0013.umair.registration.Model.ViewCart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDetails extends Base {

    Intent intent;
    String OrderId, date1, orderId, itemid, id, userId;
    Button btnDeli;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseAuth mAuth;
    ViewCart viewCart;
    SimpleDateFormat df;
    List<ViewCart> addtocart = new ArrayList<>();
    ListView listView;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        mDatabase = FirebaseDatabase.getInstance();
        btnDeli = (Button)findViewById(R.id.btnDelivered);
        id = getUid();
        intent = getIntent();
        OrderId = intent.getStringExtra("orderId");
        mAuth = FirebaseAuth.getInstance();

        mRef = mDatabase.getReference("/Orders/"+id);

        df = new SimpleDateFormat("dd-MMM-yyyy");

        final String formattedDate = df.format(c.getTime());

        addtocart = new ArrayList<>();

        listView = (ListView)findViewById(R.id.menuListview);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot resid:dataSnapshot.getChildren()) {
                    userId = resid.getKey();
                    Toast.makeText(OrderDetails.this, "" + userId, Toast.LENGTH_SHORT).show();
                    for (DataSnapshot date:resid.getChildren()) {
                        date1 = date.getKey();
                        for (DataSnapshot orderid:date.getChildren()) {
                            orderId = orderid.getKey();
                            for (DataSnapshot itemId:orderid.getChildren()) {
                                itemid = itemId.getKey();
                                viewCart = new ViewCart();
                                viewCart.setDish(itemId.child("dish").getValue().toString());
                                viewCart.setPrice(itemId.child("price").getValue().toString());
                                viewCart.setQuantity(itemId.child("quantity").getValue().toString());
                                if(date1.equals(formattedDate))
                                {
                                    addtocart.add(viewCart);
                                }
//                                sumlist.add(Integer.parseInt(itemId.child("price").getValue().toString()));
//                                sum += Integer.parseInt(itemId.child("price").getValue().toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        CartCustomAdapter cartCustomAdapter = new CartCustomAdapter(addtocart,OrderDetails.this);
        listView.setAdapter(cartCustomAdapter);

        btnDeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
                finish();
            }
        });

    }


    private void addNotification() {
        if(mAuth.getCurrentUser().getUid() == userId) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Your Order is Received")
                            .setContentText(date1 + "\n" + "Your Order Id: " +orderId);

            Intent notificationIntent = new Intent(this, UserDashBoard.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }
}
