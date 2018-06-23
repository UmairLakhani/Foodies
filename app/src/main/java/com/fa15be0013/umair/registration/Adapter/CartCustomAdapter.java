package com.fa15be0013.umair.registration.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.AddToCart;
import com.fa15be0013.umair.registration.Model.ViewCart;
import com.fa15be0013.umair.registration.Model.cart;
import com.fa15be0013.umair.registration.R;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Hamza Gaya on 12/26/2017.
 */

public class CartCustomAdapter extends BaseAdapter {
    List<ViewCart> carts;
    private static LayoutInflater inflater = null;
    Context context;


    public CartCustomAdapter(List<ViewCart> carts, Context context) {
        this.carts = carts;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, final View view, ViewGroup viewGroup) {
        final View rowView;
        rowView = inflater.inflate(R.layout.addtocartview,null);

        TextView name = (TextView)rowView.findViewById(R.id.txtFinalDish);
        final TextView price = (TextView)rowView.findViewById(R.id.txtFinalPrice);
        final TextView quantity = (TextView)rowView.findViewById(R.id.finalQuantity);
        ImageView inc = (ImageView)rowView.findViewById(R.id.cartIncrement);
        final ImageView dec = (ImageView)rowView.findViewById(R.id.cartDecrement);

        final ViewCart c = carts.get(i);
        name.setText(c.getDish());
        price.setText(c.getPrice());
        quantity.setText(c.getQuantity());
        final int[] FinalCounter = {Integer.parseInt(c.getQuantity())};

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity.setText(String.valueOf(++FinalCounter[0]));
                Intent intent = ((Activity)context).getIntent();

                String original = intent.getStringExtra("Original Price");
                int qunty = Integer.parseInt(quantity.getText().toString());

                Toast.makeText(context, ""+original, Toast.LENGTH_SHORT).show();
                if(qunty > 1)
                {
                    AddToCart ac = new AddToCart();
                    String currentPrice = String.valueOf(qunty * Integer.parseInt(original));
                    price.setText(currentPrice);
                }
                else if(qunty == 1){
                    price.setText(c.getPrice());
                }
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity.setText(String.valueOf(--FinalCounter[0]));
            }
        });

        return rowView;
    }
}
