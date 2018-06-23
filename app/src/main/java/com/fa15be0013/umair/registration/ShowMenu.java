package com.fa15be0013.umair.registration;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.fa15be0013.umair.registration.Adapter.CustomAdapter;
import com.fa15be0013.umair.registration.Model.MenuItems;
import com.fa15be0013.umair.registration.Model.MenuList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMenu extends Base {

    FirebaseDatabase database;
    DatabaseReference reference, newref;
    final List<MenuItems> list = new ArrayList<>();
    List<String> Mainkeys = new ArrayList<String>();
    ExpandableListView listView;
    String userId, Name;
    MenuList m1;
   // NonScrollExpandableListView listView1;
    List<String> ChildKeys = new ArrayList<String>();
    ArrayList<MenuList> allmenu = new ArrayList<>();

    FloatingActionButton btnfab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

     //   btnfab.setEnabled(false);


      final Intent intent = getIntent();
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      toolbar.setTitle("Menu");
      toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
      setActionBar(toolbar);

        // Assuming in activity_main, you are using LinearLayout as roo

       final String id, name;
       id = intent.getStringExtra("Id");
       name = intent.getStringExtra("Name");

//     Toast.makeText(this, name + " " + id, Toast.LENGTH_SHORT).show();

       database = FirebaseDatabase.getInstance();

       reference = database.getReference("/Menu/" + id);

       listView = (ExpandableListView) findViewById(R.id.menuListview);

       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot childSnapshot:dataSnapshot.getChildren()) {
                   String key = childSnapshot.getKey();
                   m1 = new MenuList(key);
                   for (DataSnapshot dede:childSnapshot.getChildren()) {
                       String cKey = dede.getKey();
                     //  m1.item.add();
                       ChildKeys.add(cKey);
                      MenuItems menuItems = new MenuItems();
                     // String Price = dede.child("price").getValue().toString();
                      menuItems.setPrice(dede.child("price").getValue().toString());
                      menuItems.setQuantity(dede.child("quantity").getValue().toString());
                      menuItems.setCategory(dede.getKey());
                      m1.item.add(menuItems);
                   }
                   allmenu.add(m1);
               }

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        CustomAdapter customAdapter = new CustomAdapter(this,allmenu);
        listView.setAdapter(customAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
              //  btnfab.setEnabled(true);
                Intent intent1  = new Intent(ShowMenu.this,ConfirmationDialog.class);
                intent1.putExtra("DishName",allmenu.get(i).item.get(i1).getCategory());
                intent1.putExtra("DishPrice",allmenu.get(i).item.get(i1).getPrice());
                intent1.putExtra("resid",id);
                intent1.putExtra("resName",name);
                intent1.putExtra("UserName",Name);
                startActivity(intent1);
                Toast.makeText(ShowMenu.this, allmenu.get(i).item.get(i1).getCategory() +"", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        listView1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long l) {
//                setListViewHeight(parent, groupPosition);
//                return false;
//            }
//        });


        btnfab = (FloatingActionButton)findViewById(R.id.fab);
        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShowMenu.this,AddToCart.class);
                intent1.putExtra("resId",id);
                startActivity(intent1);
            }
        });
    }
//    private void setListViewHeight(ExpandableListView listView,
//                                   int group) {
//        CustomAdapter listAdapter = (CustomAdapter) listView.getExpandableListAdapter();
//        int totalHeight = 0;
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
//                View.MeasureSpec.EXACTLY);
//        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
//            View groupItem = listAdapter.getGroupView(i, false, null, listView);
//            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//
//            totalHeight += groupItem.getMeasuredHeight();
//
//            if (((listView.isGroupExpanded(i)) && (i != group))
//                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
//                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
//                    View listItem = listAdapter.getChildView(i, j, false, null,
//                            listView);
//                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//
//                    totalHeight += listItem.getMeasuredHeight();
//
//                }
//            }
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        int height = totalHeight
//                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
//        if (height < 10)
//            height = 200;
//        params.height = height;
//        listView.setLayoutParams(params);
//        listView.requestLayout();
//
//    }
}
