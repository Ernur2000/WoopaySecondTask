package com.bolatovyernur.woopaysecondtask.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bolatovyernur.woopaysecondtask.R;
import com.bolatovyernur.woopaysecondtask.db.Category;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final ArrayList<Category> categoryResponses;
    private final Context mContext;

    public CategoryAdapter(Context context, ArrayList<Category> categoryResponses) {
        this.mContext = context;
        this.categoryResponses = categoryResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(categoryResponses.get(position).title);
        Glide.with(mContext)
                .asBitmap()
                .load(categoryResponses.get(position).picture_url)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return categoryResponses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
