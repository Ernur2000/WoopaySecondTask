package com.bolatovyernur.woopaysecondtask.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TopServiceAdapter extends RecyclerView.Adapter<TopServiceAdapter.ViewHolder> {
    private final ArrayList<TopServiceResponse> mTopServiceResponse;
    private final Context mContext;

    public TopServiceAdapter(ArrayList<TopServiceResponse> topServiceResponses, Context context) {
        mTopServiceResponse = topServiceResponses;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mTopServiceResponse.get(position).getService().getPicture_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mTopServiceResponse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_picture);
        }
    }
}
