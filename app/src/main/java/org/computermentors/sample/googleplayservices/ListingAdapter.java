package org.computermentors.sample.googleplayservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.computermentors.sample.googleplayservices.model.ActiveListings;
import org.computermentors.sample.googleplayservices.model.Listing;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingHolder>
implements Callback<ActiveListings> {

    private LayoutInflater inflater;
    private ActiveListings activeListings;

    public ListingAdapter (Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListingHolder(inflater.inflate(R.layout.layout_listing, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListingHolder holder, int position) {
        final Listing listing = activeListings.results[position];
        holder.titleView.setText(listing.title);
        holder.priceView.setText(listing.price);
        holder.shopNameView.setText(listing.Shop.shop_name);

        Picasso.get()
                .load(listing.Images[0].url_570xN)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (activeListings == null)
            return 0;

        if (activeListings.results == null)
            return 0;

        return activeListings.results.length;
    }

    @Override
    public void success(ActiveListings activeListings, Response response) {
        this.activeListings = activeListings;
        notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {

    }

    public class ListingHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView titleView;
        public TextView shopNameView;
        public TextView priceView;


        public ListingHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.listing_image);
            titleView = itemView.findViewById(R.id.listing_title);
            shopNameView = itemView.findViewById(R.id.listing_shop_name);
            priceView = itemView.findViewById(R.id.listing_price);
        }
    }
}
