package com.fabinpaul.notizia.feature.headlines.components;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabinpaul.notizia.R;
import com.fabinpaul.notizia.feature.headlines.HeadlinesContract;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<ArticlesItem> mArticles;
    private HeadlinesContract.Presenter mPresenter;

    public ArticlesAdapter(List<ArticlesItem> articles, HeadlinesContract.Presenter presenter) {
        this.mArticles = articles;
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_article, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mArticles.get(i));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mHeadlineImage;
        private TextView mHeadlineTitle;
        private TextView mNewsSource;
        private TextView mTimeSince;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeadlineImage = itemView.findViewById(R.id.headlineImg);
            mHeadlineTitle = itemView.findViewById(R.id.headlineTitle);
            mNewsSource = itemView.findViewById(R.id.headlineSource);
            mTimeSince = itemView.findViewById(R.id.headlineTimeSince);
            itemView.setOnClickListener(this);
        }

        void bind(ArticlesItem article) {
            mHeadlineTitle.setText(article.getTitle());
            mNewsSource.setText(article.getNewsSource() == null ? "" : article.getNewsSource().getName());
            Picasso.get().load(article.getUrlToImage()).into(mHeadlineImage);
        }

        @Override
        public void onClick(View v) {
            mPresenter.onNewsArticleClick(mArticles.get(getAdapterPosition()));
        }
    }
}
