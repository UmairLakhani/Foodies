package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Adapter.CartCustomAdapter;
import com.fa15be0013.umair.registration.Adapter.CustomAdapter;
import com.fa15be0013.umair.registration.Model.ViewCart;
import com.fa15be0013.umair.registration.Model.cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddToCart extends Base {

    List<ViewCart> addtocart;
    ListView listView ;
    DatabaseReference mRef;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    String id, date1, orderId, itemid, resId;
    ViewCart viewCart;
    SimpleDateFormat df;
    FloatingActionButton btnproceed;
    Calendar c = Calendar.getInstance();
    List<Integer> sumlist = new ArrayList<>();
    int i, sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        final Intent intent = getIntent();
        id = intent.getStringExtra("resId");
//      List<cart> carts =  (List<cart>) intent.getSerializableExtra("list");

       mRef = mDatabase.getReference("/Orders/"+id);

        df = new SimpleDateFormat("dd-MMM-yyyy");

        final String formattedDate = df.format(c.getTime());

        addtocart = new ArrayList<>();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot resid:dataSnapshot.getChildren()) {
                   // resId = resid.getKey();
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
                                sumlist.add(Integer.valueOf(itemId.child("price").getValue().toString()));
                                if(date1.equals(formattedDate))
                                {
                                   addtocart.add(viewCart);
                                }
//                                sumlist.add(Integer.valueOf(itemId.child("priice").getValue().toString()));
                                //sum += Integer.parseInt(itemId.child("price").getValue().toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnproceed = (FloatingActionButton) findViewById(R.id.fab);
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceed = new Intent(AddToCart.this,Finalise.class);
                proceed.putExtra("ResId",id);
                startActivity(proceed);
            }
        });



      listView = (ListView)findViewById(R.id.finalcartlist);
      CartCustomAdapter cartCustomAdapter = new CartCustomAdapter(addtocart,AddToCart.this);
      listView.setAdapter(cartCustomAdapter);
      Toast.makeText(this, ""+listView.getAdapter().getViewTypeCount(), Toast.LENGTH_SHORT).show();
//      for(i = 0; i < sumlist.size(); i++) {
//         sum += sumlist.get(i);
//      }
//
//        Toast.makeText(this, ""+sum, Toast.LENGTH_SHORT).show();

    }

}
