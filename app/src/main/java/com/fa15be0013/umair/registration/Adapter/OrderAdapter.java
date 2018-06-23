package com.fa15be0013.umair.registration.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fa15be0013.umair.registration.Model.FetchOrder;
import com.fa15be0013.umair.registration.OrderDetails;
import com.fa15be0013.umair.registration.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Hamza Gaya on 12/31/2017.
 */

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<FetchOrder> menuLists = new ArrayList<>();
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<FetchOrder> menuLists) {
        this.context = context;
        this.menuLists = menuLists;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuLists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.orderview,null);

        FetchOrder menuItems = menuLists.get(position);


        final TextView userAddress, OrderDate, OrderId, PhoneNo;
        userAddress = (TextView) rowView.findViewById(R.id.userAddress);
        PhoneNo = (TextView) rowView.findViewById(R.id.userPhone);
        OrderId = (TextView) rowView.findViewById(R.id.userOrderId);
        OrderDate = (TextView) rowView.findViewById(R.id.userOrderDate);

        userAddress.setText(menuItems.getUserAddress());
        PhoneNo.setText(menuItems.getPhone());
        OrderDate.setText(menuItems.getDate());
        OrderId.setText(menuItems.getOrderID());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetails.class);
                intent.putExtra("orderId",OrderId.getText().toString());
                context.startActivity(intent);
            }
        });


        return rowView;
    }
}