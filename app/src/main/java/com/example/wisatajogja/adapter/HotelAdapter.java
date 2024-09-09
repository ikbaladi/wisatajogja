package com.example.wisatajogja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wisatajogja.R;
import com.example.wisatajogja.model.ModelHotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<ModelHotel> items;
    private HotelAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelHotel modelNews);
    }

    public HotelAdapter(Context context, List<ModelHotel> items, HotelAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_hotel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelHotel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarHotel())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgHotel);

        holder.tvKabupaten.setText(data.getTxtKabupaten());
        holder.tvHotel.setText(data.getTxtNamaHotel());
        holder.cvHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKabupaten;
        public TextView tvHotel;
        public CardView cvHotel;
        public ImageView imgHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            cvHotel = itemView.findViewById(R.id.cvHotel);
            tvHotel = itemView.findViewById(R.id.tvNamaHotel);
            tvKabupaten = itemView.findViewById(R.id.tvKabupaten);
            imgHotel = itemView.findViewById(R.id.imgHotel);
        }
    }

}
