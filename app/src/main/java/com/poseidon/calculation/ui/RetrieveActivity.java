package com.poseidon.calculation.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.poseidon.calculation.R;
import com.poseidon.calculation.adapter.SiteAdapter;
import com.poseidon.calculation.listener.OnRetrieveListener;
import com.poseidon.calculation.model.InputData;

import java.util.ArrayList;

public class RetrieveActivity extends AppCompatActivity implements OnRetrieveListener{

    public static Activity mActivity;
    RecyclerView mRcySites;
    SiteAdapter mSiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActivity = this;
        mRcySites = (RecyclerView) findViewById(R.id.rcySites);

        addRecyclerViewItemDivider(mRcySites);
        mRcySites.setHasFixedSize(true);
        mRcySites.setLayoutManager(new LinearLayoutManager(this));
        mSiteAdapter = new SiteAdapter(this);
        mSiteAdapter.setOnRetrieveListener(this);
        ArrayList<String> arrSites = new ArrayList<>();
        for (int i = 0; i < InputData.mArrInputData.size(); i++) {
            InputData inputData = InputData.mArrInputData.get(i);
            arrSites.add(inputData.siteName);
        }
        mSiteAdapter.setSiteList(arrSites);
        mRcySites.setAdapter(mSiteAdapter);
    }

    private void addRecyclerViewItemDivider(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 1;
            }
        });

    }

    @Override
    public void onNext(String siteName) {
        Intent intent = new Intent(this, NeedSomeActivity.class);
        intent.putExtra(MainActivity.METHOD, MainActivity.METHOD_RETRIEVE);
        intent.putExtra(MainActivity.SITE_NAME, siteName);
        startActivity(intent);
        finish();
    }
}
