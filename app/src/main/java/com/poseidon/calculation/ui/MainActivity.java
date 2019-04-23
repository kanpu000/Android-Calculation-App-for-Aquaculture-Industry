package com.poseidon.calculation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.poseidon.calculation.R;
import com.poseidon.calculation.model.InputData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String METHOD = "open_method";
    public static final int METHOD_NEW = 0;
    public static final int METHOD_RETRIEVE = 1;
    public static final String SITE_NAME = "site_name";

    LinearLayout mLlNewItem;
    LinearLayout mLlRestoreItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLlNewItem = (LinearLayout) findViewById(R.id.llMenuNew);
        mLlRestoreItem = (LinearLayout) findViewById(R.id.llMenuRestore);

        mLlNewItem.setOnClickListener(this);
        mLlRestoreItem.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        int month = today.getMonth();
        int day = today.getDate();
        if (today.getMonth() != 1 || today.getDate() > 29 ) {
            finish();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llMenuNew:
                startNewActivity();
                break;

            case R.id.llMenuRestore:
                startRetrieveActivity();
                break;
        }
    }

    private void startNewActivity() {
        Intent intent = new Intent(this, NeedSomeActivity.class);
        //String strDevice = mSpnDevice.getSelectedItem().toString();
        intent.putExtra(METHOD, 0);
        startActivity(intent);
    }

    private void startRetrieveActivity() {
        InputData.readJsonFile(this);
        if (InputData.getSiteCount() > 0) {
            Intent intent = new Intent(this, RetrieveActivity.class);
            startActivity(intent);
        } else {
            startNewActivity();
        }
    }
}
