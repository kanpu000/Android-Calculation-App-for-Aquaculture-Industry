package com.poseidon.calculation.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poseidon.calculation.R;
import com.poseidon.calculation.listener.OnRetrieveListener;
import com.poseidon.calculation.ui.MainActivity;
import com.poseidon.calculation.ui.NeedSomeActivity;

import java.util.ArrayList;

/**
 * Created by Puls on 2/28/2019.
 */

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder> {

    private Context mContext;
    private OnRetrieveListener mOnRetrieveListener;

    private ArrayList<String> mArrSite;

    public SiteAdapter(Context context) {
        mContext = context;

        mArrSite = new ArrayList<String>();
    }

    public void setSiteList(ArrayList<String> siteList) {
        mArrSite = siteList;
    }

    @Override
    public SiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_site_list, parent, false);

        SiteViewHolder viewHolder = new SiteViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SiteViewHolder holder, final int position) {
        final SiteViewHolder wHolder = holder;
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        wHolder.setBgColor(R.color.item_select);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                        wHolder.setBgColor(R.color.item_bg);
                        break;
                }

                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRetrieveListener.onNext(mArrSite.get(position));
            }
        });

        holder.setSiteName(mArrSite.get(position));

        //. add background
        /*
        int nCnt = getPerfCount(mTheatreList.get(position).mId);
        if (nCnt > 0) {
            holder.setBgColor(R.color.text_item_select);
        } else {
            holder.setBgColor(R.color.white);
        }*/

    }

    @Override
    public int getItemCount() {
        return mArrSite.size();
    }

    public class SiteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvSite;
        private View mItemView;
        //private CircularImageView mIvIcon;

        public SiteViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
            mTvSite = (TextView) itemView.findViewById(R.id.tvSite);
        }

        public void setBgColor (int colorId) {
            Resources res = mContext.getResources();
            mItemView.setBackgroundColor(res.getColor(colorId));
        }

        public void setSiteName(String siteName) {
            mTvSite.setText(siteName);
        }
    }

    public void setOnRetrieveListener(OnRetrieveListener onRetrieveListener) {
        mOnRetrieveListener = onRetrieveListener;
    }
}

