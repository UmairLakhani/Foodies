package com.fa15be0013.umair.registration.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.fa15be0013.umair.registration.Model.MenuItems;
import com.fa15be0013.umair.registration.Model.MenuList;
import com.fa15be0013.umair.registration.R;

import java.util.ArrayList;

/**
 * Created by Hamza Gaya on 12/25/2017.
 */

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MenuList> menuLists = new ArrayList<>();
    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<MenuList> menuLists) {
        this.context = context;
        this.menuLists = menuLists;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return menuLists.size();
    }

    @Override
    public int getChildrenCount(int groupPos) {
        return menuLists.get(groupPos).item.size();
    }

    @Override
    public Object getGroup(int groupPos) {
        return  menuLists.get(groupPos);
    }

    @Override
    public Object getChild(int groupPos, int childPos) {
        return menuLists.get(groupPos).item.get(childPos);
    }

    @Override
    public long getGroupId(int groupPos) {
        return 0;
    }

    @Override
    public long getChildId(int groupPos, int childPos) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            view = inflater.inflate(R.layout.menuheader, null);
        }
        MenuList m  = (MenuList) getGroup(groupPos);

        TextView category = (TextView)view.findViewById(R.id.Category);

        category.setText(m.category);
        return view;
    }

    @Override
    public View getChildView(int groupPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            view = inflater.inflate(R.layout.menuitemview, null);
        }
     //   String Name ,Price,Quantity;
        MenuItems menuItems = (MenuItems)getChild(groupPos, childPos);
//        Name =  getChild(groupPos,childPos).toString();
//        Price = getChild(groupPos, childPos).toString();
//        Quantity = getChild(groupPos, childPos).toString();

        TextView name = (TextView)view.findViewById(R.id.ItemName);
        TextView price = (TextView)view.findViewById(R.id.ItemPrice);
        TextView quantity = (TextView)view.findViewById(R.id.ItemQuantity);

        name.setText(menuItems.getCategory());
        price.setText(menuItems.getPrice());
        quantity.setText(menuItems.getQuantity());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPos, int childPos) {
        return true;
    }
}
