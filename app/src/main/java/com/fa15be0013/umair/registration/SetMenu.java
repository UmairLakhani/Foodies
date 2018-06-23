package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fa15be0013.umair.registration.Model.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetMenu extends Base {

    EditText category, item, price, quantity;
    ImageView add, submit;
    String userId, Category, Price, Food, Quantity;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_menu);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        category = (EditText) findViewById(R.id.txtCategory);
        item = (EditText) findViewById(R.id.txtFoodName);
        quantity = (EditText) findViewById(R.id.txtQuantity);
        price = (EditText) findViewById(R.id.txtPrice);

        add = (ImageView) findViewById(R.id.btnAdd);
        submit = (ImageView) findViewById(R.id.btnSubmit);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitted();
            }
        });
    }

    private void submitted() {
        if(!validateForm())
        {
            return;
        }

        Category = category.getText().toString();
        Food = item.getText().toString();
        Quantity = quantity.getText().toString();
        Price = price.getText().toString();

        setEditingEnabled(false);

        userId = getUid();
        writeNewPost(Quantity, Price);

        item.setText("");
        quantity.setText("");
        price.setText("");

        setEditingEnabled(true);

        Intent i = new Intent(SetMenu.this, RestaurantManage.class);
        startActivity(i);
        finish();
    }

    private void addItem() {

        if(!validateForm())
        {
            return;
        }

        userId = getUid();

        setEditingEnabled(false);

        Category = category.getText().toString();
        Food = item.getText().toString();
        Quantity = quantity.getText().toString();
        Price = price.getText().toString();
        writeNewPost( Quantity, Price);

        item.setText("");
        quantity.setText("");
        price.setText("");

        setEditingEnabled(true);
    }

    private void setEditingEnabled(boolean enabled) {
        category.setEnabled(false);
        item.setEnabled(enabled);
        quantity.setEnabled(enabled);
        price.setEnabled(enabled);
    }

    private boolean validateForm() {
        boolean result = true;

        if(TextUtils.isEmpty(category.getText().toString()))
        {
            category.setError("REQUIRED");
            result = false;
        }

        if(TextUtils.isEmpty(item.getText().toString()))
        {
            item.setError("REQUIRED");
            result = false;
        }

        if(TextUtils.isEmpty(quantity.getText().toString()))
        {
            quantity.setError("REQUIRED");
            result = false;
        }

        if(TextUtils.isEmpty(price.getText().toString()))
        {
            price.setError("REQUIRED");
            result = false;
        }

        return result;
    }

    private void writeNewPost( String quantity, String price) {
        Item item = new Item(quantity, price);
        mDatabase.child("Menu").child(userId).child(Category).child(Food).setValue(item);
    }

}
