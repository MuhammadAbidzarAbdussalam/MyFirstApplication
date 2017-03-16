package com.zarslamgants25.updatesport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WIN10 on 21/02/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> listData;

    public MyAdapter(Context context, ArrayList<HashMap<String, String>> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        holder.tvheading.setText(listData.get(position).get("id"));
        holder.tvdesc.setText(listData.get(position).get("judul"));
        final String id_articel = listData.get(position).get("id");


        Picasso.with(context)
                .load(listData.get(position).get("gambar"))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kirimKeDetail = new Intent(view.getContext(), DetailArtikelActivity.class);
                kirimKeDetail.putExtra("id_artikel", id_articel);
                view.getContext().startActivity(kirimKeDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvheading;
        public TextView tvdesc;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tvheading = (TextView) itemView.findViewById(R.id.textViewHeading);
            tvdesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageView = (ImageView) itemView.findViewById(R.id.imgIcon);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

        }
    }


//===============================================TANPA HASHMAP============================================================
//    private List<ListItem> listItems;
//    private Context context;
//
//        public MyAdapter(List<ListItem> listItems, Context context) {
//        this.listItems = listItems;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        ListItem listItem = listItems.get(position);
//
//        holder.tvheading.setText(listItem.getHead());
//        holder.tvdesc.setText(listItem.getDesc());
//
//        Picasso.with(context)
//                .load(listItem.getImageUrl())
//                .into(holder.imageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listItems.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvheading;
//        public TextView tvdesc;
//        public ImageView imageView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            tvheading = (TextView) itemView.findViewById(R.id.textViewHeading);
//            tvdesc = (TextView) itemView.findViewById(R.id.textViewDesc);
//            imageView = (ImageView) itemView.findViewById(R.id.imgIcon);
//        }
//    }
//===============================================TANPA HASHMAP============================================================
}
