package com.fabinpaul.notizia.feature.headlines.components;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabinpaul.notizia.R;
import com.fabinpaul.notizia.feature.headlines.HeadlinesContract;
import com.fabinpaul.notizia.feature.headlines.data.ArticlesItem;
import com.fabinpaul.notizia.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private static final String TAG = "ArticlesAdapter";
    private static final String PUBLISHED_AT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private List<ArticlesItem> mArticles;
    private HeadlinesContract.Presenter mPresenter;

    public ArticlesAdapter(List<ArticlesItem> articles, HeadlinesContract.Presenter presenter) {
        this.mArticles = articles;
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_article, viewGroup, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int i) {
        articlesViewHolder.bind(mArticles.get(i));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mHeadlineImage;
        private TextView mHeadlineTitle;
        private TextView mNewsSource;
        private TextView mTimeSince;

        ArticlesViewHolder(@NonNull View itemView) {
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
            try {
                String hrSince = Utils.getTimeInHrSince(article.getPublishedAt(), PUBLISHED_AT_DATE_FORMAT);
                mTimeSince.setText(itemView.getContext().getString(R.string.published_since, hrSince));
            } catch (ParseException e) {
                Log.e(TAG, "Unable to parse published time", e);
            }
        }

        @Override
        public void onClick(View v) {
            mPresenter.onNewsArticleClick(mArticles.get(getAdapterPosition()));
        }
    }
}
