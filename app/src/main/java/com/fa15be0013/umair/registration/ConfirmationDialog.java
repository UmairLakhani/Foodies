package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.fa15be0013.umair.registration.Adapter.CartCustomAdapter;
import com.fa15be0013.umair.registration.Model.cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConfirmationDialog extends Base {

    ImageView btnAddtoCart, btnCancel, inc, dec;
    TextView txtDishName;
    int setQuantity = 0;
    EditText Counter;
    List<cart> cartList = new ArrayList<>();

    private DatabaseReference mDatabase, newref;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    String food, price, resName, resId, quan, orderID;
    String userId, Name, currentDateTimeString;

    Calendar c;
    SimpleDateFormat df, sdf, ddf;
    Date d;
    String currentTime;

    public static String globalSharedPreference = "Original Price";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_dialog);

        c = Calendar.getInstance();

        userId = getUid();
        newref = database.getReference("Users/"+userId+"/fullName");
        newref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Name = (String) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        inc = (ImageView)findViewById(R.id.Increment);
        dec = (ImageView)findViewById(R.id.Decrement);

        btnAddtoCart = (ImageView)findViewById(R.id.btnAddtocart);
        btnCancel = (ImageView)findViewById(R.id.btnCancel);

        txtDishName = (TextView)findViewById(R.id.DishName);

        Counter = (EditText)findViewById(R.id.txtQuantity1);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final Intent intent = getIntent();
        txtDishName.setText(intent.getStringExtra("DishName"));
        final String oldprice = intent.getStringExtra("DishPrice");
        resId = intent.getStringExtra("resid");
        resName = intent.getStringExtra("resName");

        food = intent.getStringExtra("DishName");
        price = intent.getStringExtra("DishPrice");

        SharedPreferences.Editor editor = getSharedPreferences(globalSharedPreference, MODE_PRIVATE).edit();
        editor.putString("OrigianlPrice",oldprice);
        editor.commit();

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.setText(String.valueOf(setQuantity++));
                quan = Counter.getText().toString();
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Counter.getText().equals(setQuantity))
                {
                    Counter.setText(String.valueOf(--setQuantity));
                    quan = Counter.getText().toString();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ConfirmationDialog.this,ShowMenu.class);
                startActivity(intent1);
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addtocart();
                Toast.makeText(ConfirmationDialog.this, "data Inserted", Toast.LENGTH_SHORT).show();


//                Intent intent1 = new Intent(ConfirmationDialog.this,AddToCart.class);
//                int price = Integer.parseInt(oldprice) * Integer.parseInt(Counter.getText().toString());
//                Toast.makeText(ConfirmationDialog.this, ""+price, Toast.LENGTH_SHORT).show();
//                cart c = new cart(txtDishName.getText().toString(),Counter.getText().toString(),String.valueOf(price));
//                cartList.add(c);
//                intent1.putExtra("list", (Serializable) cartList);
//                startActivity(intent1);

            }
        });


    }
    public void addtocart()
    {
        int newprice = 0;
        String order = "Order";
        cart Cart = null;

        // String finalOrder = order + orderCount;
        
        if(Integer.parseInt(quan) >= 1)
        {
            newprice = Integer.parseInt(price) * Integer.parseInt(quan);
        }
        else if(Integer.parseInt(quan) == 0)
        {
            Toast.makeText(this, "Quantity atleast Be 1", Toast.LENGTH_SHORT).show();
        }
        else{
            newprice = Integer.parseInt(price);
        }
        

        if(Integer.parseInt(quan) != 0)
        {
             Cart = new cart(food, quan, String.valueOf(newprice));
        }
        else{
            Toast.makeText(this, "Quantity atleast Be 1", Toast.LENGTH_SHORT).show();
        }
            

        c = Calendar.getInstance();

        df = new SimpleDateFormat("dd-MMM-yyyy");

        String formattedDate = df.format(c.getTime());
        d = new Date();
        sdf = new SimpleDateFormat("ddMMMyyyy");
        currentDateTimeString = sdf.format(d);
        ddf = new SimpleDateFormat("hhmmss");
        currentTime = ddf.format(c.getTime());
        orderID = order+"-"+currentDateTimeString;

        mDatabase.child("Orders").child(resId).child(getUid()).child(formattedDate).child(orderID).child(currentTime).setValue(Cart);
    }
}
