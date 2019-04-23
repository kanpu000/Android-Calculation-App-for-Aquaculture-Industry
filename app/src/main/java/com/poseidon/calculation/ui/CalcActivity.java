package com.poseidon.calculation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.poseidon.calculation.R;
import com.poseidon.calculation.model.InputData;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnSave;
    Button mBtnNew;
    TextView mTvFlowMeterReading;
    TextView mTvComparativeReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnSave = (Button) findViewById(R.id.btnSave);
        mBtnNew = (Button) findViewById(R.id.btnNew);

        mBtnSave.setOnClickListener(this);
        mBtnNew.setOnClickListener(this);

        mTvFlowMeterReading = (TextView) findViewById(R.id.tvMeter);
        mTvComparativeReading = (TextView) findViewById(R.id.tvComparativeReading);

        Intent intent = getIntent();
        String strFlowMeterReading = intent.getStringExtra(NeedSomeActivity.FLOW_METER_READING);
        double stdReading = intent.getDoubleExtra(NeedSomeActivity.COMPARATIVE_STANDARD_METER, 0.0);
        if (stdReading > 0) {
            stdReading = stdReading * 10;
            stdReading = Math.round(stdReading);
            stdReading = stdReading / 10;
        }

        mTvFlowMeterReading.setText(strFlowMeterReading);
        mTvComparativeReading.setText(Double.toString(stdReading));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNew:
                newCalc();
                break;

            case R.id.btnSave:
                saveCalc();
                break;

        }
    }

    private void saveCalc() {
        //NeedSomeActivity.mInputData.setPreferences(this);
        boolean bExist = false;
        for (int i = 0; i < InputData.mArrInputData.size(); i++) {
            InputData inputData = InputData.mArrInputData.get(i);
            if (inputData.siteName.equals(NeedSomeActivity.mInputData.siteName)) {
                InputData.mArrInputData.remove(i);
                InputData.mArrInputData.add(NeedSomeActivity.mInputData);
                bExist = true;
                break;
            }
        }

        if (!bExist)
            InputData.mArrInputData.add(NeedSomeActivity.mInputData);

        InputData.writeJsonFile(this);
        //RetrieveActivity.mActivity.finish();
        //NeedSomeActivity.mActivity.finish();
        finish();


    }

    private void newCalc() {

        //RetrieveActivity.mActivity.finish();
        finish();
        Intent intent = new Intent(this, NeedSomeActivity.class);
        startActivity(intent);
    }
}
