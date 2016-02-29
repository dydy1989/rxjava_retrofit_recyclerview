package com.halzhang.android.example.rxexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by DongYue on 2016/2/23.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<ArticlesData.Aritcle> mArticles;
    private Context mContext;
    public NewsAdapter(List<ArticlesData.Aritcle> data,Context context){
        this.mArticles =data;
        this.mContext = context;
    }
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslist_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mArticles.get(position).title);
        Glide.with(mContext)
                .load(mArticles.get(position).imageUrl)
                .crossFade()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if(mArticles==null){
            return  0;
        }else {
            return mArticles.size();
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            title = (TextView) itemView.findViewById(R.id.tv_title);
        }
        // create a new view


    }

    public void setArticles(List<ArticlesData.Aritcle> articles){
        this.mArticles = articles;
    }
}
