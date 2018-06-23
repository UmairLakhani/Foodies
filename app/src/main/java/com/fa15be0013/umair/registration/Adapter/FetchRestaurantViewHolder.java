package com.fa15be0013.umair.registration.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fa15be0013.umair.registration.R;
import com.fa15be0013.umair.registration.ShowMenu;
import com.squareup.picasso.Picasso;

/**
 * Created by Hamza Gaya on 12/20/2017.
 */

public class FetchRestaurantViewHolder extends RecyclerView.ViewHolder {

    public ImageView restImage;
    public TextView RestName, RestId;

     Context context;
    // View mView;
    public FetchRestaurantViewHolder(View itemView) {
        super(itemView);
        RestName = (TextView) itemView.findViewById(R.id.resName);
        RestId = (TextView) itemView.findViewById(R.id.resUid);
        restImage = (ImageView) itemView.findViewById(R.id.ImgHotel);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListner.onItemClick(view, RestId);
            }
        });

    }
    public void setOnClickListener(FetchRestaurantViewHolder.ClickListner clickListener){
        mClickListner = clickListener;
    }
    public interface ClickListner{
        public void onItemClick(View view, TextView RestsId);
    }
    private FetchRestaurantViewHolder.ClickListner mClickListner;
    //        public void setTitle(String name)
//        {
//            RestName.setText(name);
//        }
    public void setImage(Context ctx, String image) {
        Picasso.with(ctx).load(image).into(restImage);
    }
}




//    public FetchRestaurantViewHolder(List<Restaurant> mRestaurant, Context mContext) {
//        this.mRestaurant = mRestaurant;
//        this.mContext = mContext;
//        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View rowView = inflater.inflate(R.layout.hotelview,null);
//        myHolder Myholder = new myHolder(rowView);
//
//
//
//        return Myholder;
//    }
//
//    @Override
//    public void onBindViewHolder(myHolder holder, final int position) {
//
//        holder.imageView.setImageResource(Integer.parseInt(mRestaurant.get(position).getImage()));
//        holder.name.setText(mRestaurant.get(position).getRestaurantName());
//      //  holder.name.setText(mRestaurant.get(position).getHotelName());
//
//
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent intent = new Intent(mContext,ShowMenu.class);
////                intent.putExtra("name", mRestaurant.get(position).getHotelName());
////                mContext.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() {
//        return mRestaurant.size();
//    }
//
//    public static class myHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//        TextView name;
//        TextView time;
//
//        public myHolder(View itemView) {
//            super(itemView);
//
//
//            imageView = (ImageView)itemView.findViewById(R.id.ImgHotel);
//            name = (TextView)itemView.findViewById(R.id.resName);
//        }
//
//    }
