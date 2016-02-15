package com.example.sashok.nytimy;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asmurthy on 2/13/16.
 */
public class ArticleArrayAdapter extends ArrayAdapter<Article> {


    public ArticleArrayAdapter(Context context, ArrayList<Article> articles) {
        super(context,0, articles);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // get item from position
        Article article = this.getItem(position);

        // check for recycle view

        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_article_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.tvTitle.setText(article.getHeadline());

    //    holder.ivImage.setImageResource(0);

        String thumbnail = article.getThumbNail();

        if(!TextUtils.isEmpty(thumbnail)) {
            Glide.with(getContext())
                    .load(thumbnail)
                    .into(holder.ivImage);

        }

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tvTitle)  TextView tvTitle;
        @Bind(R.id.ivImage) ImageView ivImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
