package com.poseidon.calculation.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.poseidon.calculation.R;
import com.poseidon.calculation.adapter.SpnAdapter;
import com.poseidon.calculation.model.InputData;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

public class NeedSomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    public static final String FLOW_METER_READING = "targetFlowDisplay";
    public static final String COMPARATIVE_STANDARD_METER = "stdReading";
    public static InputData mInputData = new InputData();

    private static boolean mbEditChanged = true;

    public static Activity mActivity;

    Button mBtnCalc;

    EditText mEdtSiteName;
    Spinner mSpnPanelGeneration;
    EditText mEdtAvailableCompressors;
    EditText mEdtCompressorOutput;
    //SegmentedGroup mSegActiveCompressors;
    EditText mEdtActiveCompressors;
    EditText mEdtNumberOfPens;
    EditText mEdtChannelsPerPen;
    //SegmentedGroup mSegActivePens;
    EditText mEdtActivePens;
    EditText mEdtNumberOfWalkwayChannels;
    //SegmentedGroup mSegActiveWalkwayChannels;
    EditText mEdtActiveWalkwayChannels;
    EditText mEdtPannelSetPressure;

    ArrayList<String> mListPanel;
    SpnAdapter mSpnAdapter;
    int mPanelGen = 1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_some);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActivity = this;

        mBtnCalc = (Button) findViewById(R.id.btnCalc);
        mBtnCalc.setOnClickListener(this);

        mEdtSiteName = (EditText) findViewById(R.id.edtSiteName);
        mSpnPanelGeneration = (Spinner) findViewById(R.id.spnPanelGen);

        mEdtAvailableCompressors = (EditText) findViewById(R.id.edtAvailableCompressors);
        mEdtCompressorOutput = (EditText) findViewById(R.id.edtCompressorOutput);
        //mSegActiveCompressors = (SegmentedGroup) findViewById(R.id.segActiveCompressors);
        mEdtActiveCompressors = (EditText) findViewById(R.id.edtActiveCompressors);

        mEdtNumberOfPens = (EditText) findViewById(R.id.edtNumberOfPen);
        mEdtChannelsPerPen = (EditText) findViewById(R.id.edtChannelsPerPen);
        //mSegActivePens = (SegmentedGroup) findViewById(R.id.segActivePens);
        mEdtActivePens = (EditText) findViewById(R.id.edtActivePens);

        mEdtNumberOfWalkwayChannels = (EditText) findViewById(R.id.edtNumberOfWalkway);
        //mSegActiveWalkwayChannels = (SegmentedGroup) findViewById(R.id.segActiveWalkwayChannels);
        mEdtActiveWalkwayChannels = (EditText) findViewById(R.id.edtActiveWalkwayChannels);

        mEdtPannelSetPressure = (EditText) findViewById(R.id.edtPannelSetPressure);

        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(8);
        mEdtAvailableCompressors.setFilters(filterArray);
        mEdtCompressorOutput.setFilters(filterArray);
        mEdtActiveCompressors.setFilters(filterArray);
        mEdtNumberOfPens.setFilters(filterArray);
        mEdtChannelsPerPen.setFilters(filterArray);
        mEdtActivePens.setFilters(filterArray);
        mEdtNumberOfWalkwayChannels.setFilters(filterArray);
        mEdtActiveWalkwayChannels.setFilters(filterArray);
        mEdtPannelSetPressure.setFilters(filterArray);

        // edit text changet listener
        final Context context = (Context) this;
        mEdtAvailableCompressors.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mbEditChanged == false)
                    return;

                String strText = mEdtAvailableCompressors.getText().toString();
                int nCnt = 0;
                if (strText == null || strText.isEmpty() ) {
                    mEdtActiveCompressors.setText("");
                    return;
                } else {
                    nCnt = Integer.decode(strText);
                    if (nCnt == 0) {
                        showAlert(context, "Must be greater than zero.");
                    }
                }

                if (nCnt == 0) {
                    mEdtAvailableCompressors.setText("");
                    mEdtActiveCompressors.setText("");
                } else {
                    mEdtActiveCompressors.setText(Integer.toString(nCnt));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtNumberOfPens.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mbEditChanged)
                    return;

                String strText = mEdtNumberOfPens.getText().toString();
                int nCnt = 0;
                if (strText == null || strText.isEmpty() ) {
                    mEdtActivePens.setText("");
                    return;
                } else {
                    nCnt = Integer.decode(strText);
                    if (nCnt == 0) {
                        showAlert(context, "Must be greater than zero.");
                    }
                }

                if (nCnt == 0) {
                    mEdtNumberOfPens.setText("");
                    mEdtActivePens.setText("");
                } else {
                    mEdtActivePens.setText(Integer.toString(nCnt));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtNumberOfWalkwayChannels.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mbEditChanged)
                    return;

                String strText = mEdtNumberOfWalkwayChannels.getText().toString();
                int nCnt = 0;
                if (strText == null || strText.isEmpty() ) {
                    mEdtActiveWalkwayChannels.setText("");
                } else {
                    nCnt = Integer.decode(strText);
                    if (nCnt < -1) {
                        showAlert(context, "Must be zero and greater than zero.");
                    }
                }

                if (nCnt < 0) {
                    mEdtNumberOfWalkwayChannels.setText("");
                    mEdtActiveWalkwayChannels.setText("");
                } else {
                    mEdtActiveWalkwayChannels.setText(Integer.toString(nCnt));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtActiveCompressors.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mbEditChanged)
                    return;

                String strText = mEdtActiveCompressors.getText().toString();
                String strAvail = mEdtAvailableCompressors.getText().toString();

                if (strAvail == null || strAvail.isEmpty() ) {
                    return;
                }

                if (strText == null || strText.isEmpty() ) {
                    return;
                }

                int nAvail = Integer.decode(strAvail);
                int nCnt = Integer.decode(strText);
                if (nCnt == 0) {
                    showAlert(context, "Must be greater than zero.");
                }

                if (nCnt == 0) {
                    mEdtActiveCompressors.setText("1");
                } else {
                    if (nCnt > nAvail) {
                        String temp = "Max is " + Integer.toString(nAvail);
                        showAlert(context, temp);
                        mEdtActiveCompressors.setText(Integer.toString(nAvail));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtActivePens.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mbEditChanged)
                    return;

                String strText = mEdtActivePens.getText().toString();
                String strAvail = mEdtNumberOfPens.getText().toString();

                if (strAvail == null || strAvail.isEmpty() ) {
                    return;
                }

                if (strText == null || strText.isEmpty() ) {
                    return;
                }

                int nAvail = Integer.decode(strAvail);
                int nCnt = Integer.decode(strText);
                if (nCnt == 0) {
                    mEdtActivePens.setText("1");
                } else {
                    if (nCnt > nAvail) {
                        String temp = "Max is " + Integer.toString(nAvail);
                        showAlert(context, temp);
                        mEdtActivePens.setText(Integer.toString(nAvail));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdtActiveWalkwayChannels.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mbEditChanged)
                    return;

                String strText = mEdtActiveWalkwayChannels.getText().toString();
                String strAvail = mEdtNumberOfWalkwayChannels.getText().toString();

                if (strAvail == null || strAvail.isEmpty() ) {
                    return;
                }

                if (strText == null || strText.isEmpty() ) {
                    return;
                }

                int nAvail = Integer.decode(strAvail);
                int nCnt = Integer.decode(strText);

                if (strText == null || strText.isEmpty() ) {

                } else {
                    nCnt = Integer.decode(strText);
                    if (nCnt < 0) {
                        showAlert(context, "Must be zero and greater than zero.");
                    }
                }

                if (nCnt < 0) {
                    mEdtActiveWalkwayChannels.setText("0");
                } else {
                    if (nCnt > nAvail) {
                        String temp = "Max is " + Integer.toString(nAvail);
                        showAlert(context, temp);
                        mEdtActiveWalkwayChannels.setText(Integer.toString(nAvail));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // focus.
        mEdtSiteName.setOnFocusChangeListener(this);
        mEdtAvailableCompressors.setOnFocusChangeListener(this);
        mEdtCompressorOutput.setOnFocusChangeListener(this);
        mEdtActiveCompressors.setOnFocusChangeListener(this);
        mEdtNumberOfPens.setOnFocusChangeListener(this);
        mEdtChannelsPerPen.setOnFocusChangeListener(this);
        mEdtActivePens.setOnFocusChangeListener(this);
        mEdtNumberOfWalkwayChannels.setOnFocusChangeListener(this);
        mEdtActiveWalkwayChannels.setOnFocusChangeListener(this);
        mEdtPannelSetPressure.setOnFocusChangeListener(this);


        //. default spin
        mListPanel = new ArrayList<String>(); // List of Items
        mListPanel.add("1");
        mListPanel.add("2");
        mListPanel.add("4");

        mSpnAdapter = new SpnAdapter(this, mListPanel);
        mSpnPanelGeneration.setOnItemSelectedListener(this);
        mSpnPanelGeneration.setAdapter(mSpnAdapter);

        //. intent
        Intent intent = getIntent();
        int method = intent.getIntExtra(MainActivity.METHOD, 0);
        if (method == MainActivity.METHOD_RETRIEVE) {
            String siteName = intent.getStringExtra(MainActivity.SITE_NAME);

            for (int i = 0; i < InputData.mArrInputData.size(); i++) {
                InputData inputData = InputData.mArrInputData.get(i);

                if (siteName.equals(inputData.siteName)) {
                    mInputData.siteName = inputData.siteName;
                    mInputData.panelGen = inputData.panelGen;
                    mInputData.availNumComps = inputData.availNumComps;
                    mInputData.compFlow = inputData.compFlow;
                    mInputData.numComps = inputData.numComps;
                    mInputData.numPens = inputData.numPens;
                    mInputData.chanPerPen = inputData.chanPerPen;
                    mInputData.activePens = inputData.activePens;
                    mInputData.chnlWalkway = inputData.chnlWalkway;
                    mInputData.activeChnlWalk = inputData.activeChnlWalk;
                    mInputData.readPressure = inputData.readPressure;

                    initialize();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalc:
                startCalc();
                break;
        }
    }

    private void initialize(){
        mEdtSiteName.setText(mInputData.siteName);

        switch (mInputData.panelGen) {
            case 1:
                //mSegPanelGeneration.check(R.id.btnGen1);
                mSpnPanelGeneration.setSelection(0);
                break;
            case 2:
                //mSegPanelGeneration.check(R.id.btnGen2);
                mSpnPanelGeneration.setSelection(1);
                break;
            case 4:
                //mSegPanelGeneration.check(R.id.btnGen4);
                mSpnPanelGeneration.setSelection(2);
                break;
            default:
                //mSegPanelGeneration.check(R.id.btnGen1);
                break;
        }

        if (mInputData.availNumComps == 0)
            mEdtAvailableCompressors.setText("");
        else
            mEdtAvailableCompressors.setText(Integer.toString(mInputData.availNumComps));

        if (mInputData.compFlow == 0)
            mEdtCompressorOutput.setText("");
        else
            mEdtCompressorOutput.setText(Integer.toString(mInputData.compFlow));

        if (mInputData.numComps == 0)
            mEdtActiveCompressors.setText("");
        else
            mEdtActiveCompressors.setText(Integer.toString(mInputData.numComps));

        if (mInputData.numPens == 0)
            mEdtNumberOfPens.setText("");

        else
            mEdtNumberOfPens.setText(Integer.toString(mInputData.numPens));

        if (mInputData.chanPerPen == 0)
            mEdtChannelsPerPen.setText("");
        else
            mEdtChannelsPerPen.setText(Integer.toString(mInputData.chanPerPen));

        if (mInputData.activePens == 0)
            mEdtActivePens.setText("");
        else
            mEdtActivePens.setText(Integer.toString(mInputData.activePens));

        if (mInputData.chnlWalkway == 0)
            mEdtNumberOfWalkwayChannels.setText("");
        else
            mEdtNumberOfWalkwayChannels.setText(Integer.toString(mInputData.chnlWalkway));

        if (mInputData.activeChnlWalk == 0)
            mEdtActiveWalkwayChannels.setText("");
        else
            mEdtActiveWalkwayChannels.setText(Integer.toString(mInputData.activeChnlWalk));

        if (mInputData.readPressure == 0)
            mEdtPannelSetPressure.setText("");
        else
            mEdtPannelSetPressure.setText(Integer.toString(mInputData.readPressure));
    }

    private void startCalcActivity() {
        Intent intent = new Intent(this, CalcActivity.class);
        startActivity(intent);
    }

    private void clearControl() {

        mbEditChanged = false;

        mEdtSiteName.setText("");
        mEdtAvailableCompressors.setText("");
        mEdtCompressorOutput.setText("");
        mEdtActiveCompressors.setText("");
        mEdtNumberOfPens.setText("");
        mEdtChannelsPerPen.setText("");
        mEdtActivePens.setText("");
        mEdtNumberOfWalkwayChannels.setText("");
        mEdtActiveWalkwayChannels.setText("");
        mEdtPannelSetPressure.setText("");

        mbEditChanged = true;

    }

    private void startCalc() {
        String strSiteName = mEdtSiteName.getText().toString();
        String strAvailableCompressors = mEdtAvailableCompressors.getText().toString();
        String strCompressorOutput = mEdtCompressorOutput.getText().toString();
        String strNumberOfPens = mEdtNumberOfPens.getText().toString();
        String strChannelsPerPen = mEdtChannelsPerPen.getText().toString();
        String strNumberOfWalkwayChannels = mEdtNumberOfWalkwayChannels.getText().toString();
        String strPannelSetPressure = mEdtPannelSetPressure.getText().toString();

        String strActiveCompressors = mEdtActiveCompressors.getText().toString();
        String strActivePens = mEdtActivePens.getText().toString();
        String strActiveWalkways = mEdtActiveWalkwayChannels.getText().toString();

        if (strSiteName == null || strSiteName.isEmpty()) {
            showAlert(this, "Please input the Site Name");
            return;
        }

        if (strAvailableCompressors == null || strAvailableCompressors.isEmpty()) {
            showAlert(this, "Please input the Available Compressors");
            return;
        }

        if (strCompressorOutput == null || strCompressorOutput.isEmpty()) {
            showAlert(this, "Please input the Compressor Output");
            return;
        }

        if (strNumberOfPens == null || strNumberOfPens.isEmpty()) {
            showAlert(this, "Please input the Number of Pens");
            return;
        }

        if (strChannelsPerPen == null || strChannelsPerPen.isEmpty()) {
            showAlert(this, "Please input the Channels per Pen");
            return;
        }

        if (strNumberOfWalkwayChannels == null || strNumberOfWalkwayChannels.isEmpty()) {
            showAlert(this, "Please input the Number of WalkwayChannels");
            return;
        }

        if (strPannelSetPressure == null || strPannelSetPressure.isEmpty()) {
            showAlert(this, "Please input the Pannel Set Pressure");
            return;
        }

        //. active
        if (strActiveCompressors == null || strActiveCompressors.isEmpty()) {
            showAlert(this, "Please input the Active Compressors");
            return;
        }

        if (strActivePens == null || strActivePens.isEmpty()) {
            showAlert(this, "Please input the Active Pens");
            return;
        }

        if (strActiveWalkways == null || strActiveWalkways.isEmpty()) {
            showAlert(this, "Please input the Active Walkway Channels");
            return;
        }

        //.
        int availNumComps = Integer.decode(strAvailableCompressors);
        if (availNumComps <= 0)
        {
            showAlert(this, "AvailableCompressors must be greater than 0");
            return;
        }

        int compFlow = Integer.decode( strCompressorOutput);
        if (compFlow <= 0)
        {
            showAlert(this, "CompressorOutput must be greater than 0");
            return;
        }

        int numPens = Integer.decode( strNumberOfPens);
        if (numPens <= 0)
        {
            showAlert(this, "NumberOfPens must be greater than 0");
            return;
        }

        int chanPerPen = Integer.decode( strChannelsPerPen);
        int chnlWalkway = Integer.decode( strNumberOfWalkwayChannels);
        int readPressure = Integer.decode( strPannelSetPressure);

        //. calc variables
        int pannelGen = mPanelGen;
        /*
        switch (mSegPanelGeneration.getCheckedRadioButtonId() ){
            case R.id.btnGen1:
                pannelGen = 1;
                break;
            case R.id.btnGen2:
                pannelGen = 2;
                break;
            case R.id.btnGen4:
                pannelGen = 4;
                break;
            default:
                    pannelGen = 1;
                    break;
        }*/

        int numComps = Integer.decode(mEdtActiveCompressors.getText().toString());

        int activePens = Integer.decode(mEdtActivePens.getText().toString());

        int activeChnlWalk = Integer.decode(mEdtActiveWalkwayChannels.getText().toString());

        //. calculation
        int totalFlow = compFlow * numComps;
        int activeOutlets = chanPerPen * activePens + activeChnlWalk;
        double flowPerChnl = (double) totalFlow / (double) activeOutlets;
        int calPressure = 0;
        if (pannelGen == 1 )
            calPressure = 90;
        else if (pannelGen == 2)
            calPressure = 60;
        else if (pannelGen == 4)
            calPressure = 0;

        double pressCorr = Math.sqrt((readPressure + 14.7) / (calPressure+14.7));
        double stdCorr = Math.sqrt((readPressure + 14.7) / (0+14.7));
        double targetReadingFlow = (double) flowPerChnl / (double) pressCorr;
        targetReadingFlow = Math.round(targetReadingFlow * 100);
        targetReadingFlow = targetReadingFlow / 100;

        double stdReading = (double) flowPerChnl / (double) stdCorr;
        double maStandardFlow = 16.0;
        double maxFlowRate = maStandardFlow * pressCorr;
        double targetReadingPercent = ((double) (100 * flowPerChnl) / (double) maxFlowRate);
        targetReadingPercent = Math.round(targetReadingPercent *10);
        targetReadingPercent = targetReadingPercent / 10;

        String targetFlowDisplay = "";
        if (pannelGen == 4)
            targetFlowDisplay = Double.toString(targetReadingPercent) + "%";
        else
            targetFlowDisplay = Double.toString(targetReadingFlow);

        //. set variables into static
        mInputData.siteName = strSiteName;
        mInputData.panelGen = pannelGen;
        mInputData.availNumComps = availNumComps;
        mInputData.compFlow = compFlow;
        mInputData.numComps = numComps;
        mInputData.numPens = numPens;
        mInputData.chanPerPen = chanPerPen;
        mInputData.activePens = activePens;
        mInputData.chnlWalkway = chnlWalkway;
        mInputData.activeChnlWalk = activeChnlWalk;
        mInputData.readPressure = readPressure;

        //. clear controls
        clearControl();

        //. start next activity
        Intent intent = new Intent(this, CalcActivity.class);
        intent.putExtra(FLOW_METER_READING, targetFlowDisplay);
        intent.putExtra(COMPARATIVE_STANDARD_METER, stdReading);
        startActivity(intent);

        finish();
    }

    public static void showAlert(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = position;
        mPanelGen = Integer.decode(mListPanel.get(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edtSiteName:
                mEdtSiteName.setSelection(mEdtSiteName.getText().length());
                break;

            case R.id.edtAvailableCompressors:
                mEdtAvailableCompressors.setSelection(mEdtAvailableCompressors.getText().length());
                break;
            case R.id.edtCompressorOutput:
                mEdtCompressorOutput.setSelection(mEdtCompressorOutput.getText().length());
                break;
            case R.id.edtNumberOfPen:
                mEdtNumberOfPens.setSelection(mEdtNumberOfPens.getText().length());
                break;
            case R.id.edtChannelsPerPen:
                mEdtChannelsPerPen.setSelection(mEdtChannelsPerPen.getText().length());
                break;
            case R.id.edtNumberOfWalkway:
                mEdtNumberOfWalkwayChannels.setSelection(mEdtNumberOfWalkwayChannels.getText().length());
                break;

            case R.id.edtPannelSetPressure:
                mEdtPannelSetPressure.setSelection(mEdtPannelSetPressure.getText().length());
                break;

            case R.id.edtActiveCompressors:
                mEdtActiveCompressors.setSelection(mEdtActiveCompressors.getText().length());
                break;

            case R.id.edtActivePens:
                mEdtActivePens.setSelection(mEdtActiveCompressors.getText().length());
                break;

            case R.id.edtActiveWalkwayChannels:
                mEdtActiveWalkwayChannels.setSelection(mEdtActiveCompressors.getText().length());
                break;
        }


    }
}
