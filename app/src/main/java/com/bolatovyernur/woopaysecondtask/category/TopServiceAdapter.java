package com.bolatovyernur.woopaysecondtask.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.model.TopServiceResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TopServiceAdapter extends RecyclerView.Adapter<TopServiceAdapter.ViewHolder> {
    private final ArrayList<TopServiceResponse> mTopServiceResponse;
    private Context mContext;

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


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mTopServiceResponse.get(position).getName());
        holder.platform.setText(mTopServiceResponse.get(position).getPlatform());
        Glide.with(mContext)
                .asBitmap()
                .load(mTopServiceResponse.get(position).getService().getPicture_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mTopServiceResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView platform;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_service_name);
            platform = itemView.findViewById(R.id.tv_platform);
            imageView = itemView.findViewById(R.id.iv_picture);
        }
    }
}
