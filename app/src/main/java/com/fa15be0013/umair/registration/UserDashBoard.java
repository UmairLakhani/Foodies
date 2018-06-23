package com.fa15be0013.umair.registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fa15be0013.umair.registration.Adapter.FetchRestaurantViewHolder;
import com.fa15be0013.umair.registration.Model.FetchRestaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserDashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    //FetchRestaurantViewHolder adapter;
    Context contextt;

    FirebaseDatabase database;
    DatabaseReference reference;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("/Restaurant");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                   onLIstGet();
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder>(
                        FetchRestaurant.class,
                        R.layout.hotelview,
                        FetchRestaurantViewHolder.class,
                        reference) {
                    @Override
                    protected void populateViewHolder(FetchRestaurantViewHolder viewHolder, final FetchRestaurant model, int position) {
                        viewHolder.RestName.setText(model.getRestaurantName());
                        viewHolder.RestId.setText(model.getUid());
                        viewHolder.setImage(getApplicationContext(), model.getimage());
                        viewHolder.setOnClickListener(new FetchRestaurantViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, TextView RestsId) {
                                Intent intent = new Intent(UserDashBoard.this,ShowMenu.class);
                                intent.putExtra("Id",model.getUid());
                                intent.putExtra("Name",model.getRestaurantName());
                                startActivity(intent);
                                //Toast.makeText(UserDashBoard.this, model.getRestaurantName()+" selected", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //Toast.makeText(UserDashBoard.this, model.getRestaurantName(), Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
                    }
//                    @Override
//                    public FetchRestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        FetchRestaurantViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//                        viewHolder.setOnClickListener(new FetchRestaurantViewHolder.ClickListner() {
//                            FetchRestaurant fetchRestaurant = new FetchRestaurant();
//                            @Override
//                            public void onItemClick(View view, TextView id) {
//                                Intent intent = new Intent(UserDashBoard.this,ShowMenu.class);
//                                //intent.putExtra("id",);
//                                Toast.makeText(UserDashBoard.this, "Item clicked "+id, Toast.LENGTH_SHORT).show();
//                            }
//
//                        });
//                        return viewHolder;
//                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        // database.getReference("/Restaurant/");

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        showProgressDialog();
//        FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder> firebaseRecyclerAdapter =
//                new FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder>(
//                        FetchRestaurant.class,
//                        R.layout.hotelview,
//                        FetchRestaurantViewHolder.class,
//                        reference) {
//                    @Override
//                    protected void populateViewHolder(FetchRestaurantViewHolder viewHolder, FetchRestaurant model, int position) {
//                        viewHolder.RestName.setText(model.getRestaurantName());
//                        viewHolder.setImage(getApplicationContext(), model.getimage());
////                    viewHolder.RestName.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            final FetchRestaurant res = new FetchRestaurant();
////                            Intent intent = new Intent(contextt, ShowMenu.class);
////                            intent.putExtra("Uid", res.getUid());
////                            intent.putExtra("name", res.getRestaurantName());
////                            contextt.startActivity(intent);
////                        }
////                    });
//
//                        Toast.makeText(UserDashBoard.this, model.getRestaurantName(), Toast.LENGTH_SHORT).show();
//                        hideProgressDialog();
//                    }
//
//                };
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
//
//    }


//    protected void onLIstGet() {
//        FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder> firebaseRecyclerAdapter =
//                new FirebaseRecyclerAdapter<FetchRestaurant, FetchRestaurantViewHolder>(
//                        FetchRestaurant.class,
//                        R.layout.hotelview,
//                        FetchRestaurantViewHolder.class,
//                        reference) {
//                    @Override
//                    protected void populateViewHolder(FetchRestaurantViewHolder viewHolder, FetchRestaurant model, int position) {
//                        viewHolder.RestName.setText(model.getRestaurantName());
//                        viewHolder.setImage(getApplicationContext(), model.getimage());
////                    viewHolder.RestName.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            final FetchRestaurant res = new FetchRestaurant();
////                            Intent intent = new Intent(contextt, ShowMenu.class);
////                            intent.putExtra("Uid", res.getUid());
////                            intent.putExtra("name", res.getRestaurantName());
////                            contextt.startActivity(intent);
////                        }
////                    });
//
//                        Toast.makeText(UserDashBoard.this, model.getRestaurantName(), Toast.LENGTH_SHORT).show();
//                        hideProgressDialog();
//                    }
//
//                };
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
//
//    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Your Internet is Slow ... please wait");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


//    public static class FetchRestaurantViewHolder extends RecyclerView.ViewHolder {
//        public ImageView restImage;
//        public TextView RestName;
//
//        //private Context context;
//        // View mView;
//        public FetchRestaurantViewHolder(View itemView) {
//            super(itemView);
//            RestName = (TextView) itemView.findViewById(R.id.resName);
//            restImage = (ImageView) itemView.findViewById(R.id.ImgHotel);
////            itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    // bhago();
////                }
////            });
//
//        }
//
//        //        public void setTitle(String name)
////        {
////            RestName.setText(name);
////        }
//        public void setImage(Context ctx, String image) {
//            Picasso.with(ctx).load(image).into(restImage);
//        }
//    }
//        private void onClicks() {
//
//            Toast.makeText(, "", Toast.LENGTH_SHORT).show();
//            final FetchRestaurant res = new FetchRestaurant();
//           Intent intent = new Intent(UserDashBoard.this,ShowMenu.class);
//           intent.putExtra("Uid",res.getRestId());
//            intent.putExtra("name",res.getRestName());
//            startActivity(intent);
//        }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
//            Intent intent = new Intent(contextt,)
            Toast.makeText(UserDashBoard.this, "Update Work in Progress", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gallery) {

            Toast.makeText(UserDashBoard.this, "Update Work in Progress", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {

            Toast.makeText(UserDashBoard.this, "Feature is currently disabled", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(UserDashBoard.this, "Update Work in Progress", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(UserDashBoard.this, LoginScreen.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
