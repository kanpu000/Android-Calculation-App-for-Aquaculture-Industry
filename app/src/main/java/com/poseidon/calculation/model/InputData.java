package com.poseidon.calculation.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.poseidon.calculation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Puls on 2/26/2019.
 */

public class InputData {
    private static final String prefSiteName = "siteName";
    private static final String prefPanelGen = "panelGen";
    private static final String prefCompFlow = "compFlow";
    private static final String prefAvailNumComps = "availNumComps";
    private static final String prefNumComps = "numComps";
    private static final String prefNumPens = "numPens";
    private static final String prefChanPerPen = "chanPerPen";
    private static final String prefActivePens = "activePens";
    private static final String prefChnlWalkway = "chnlWalkway";
    private static final String prefActiveChnlWalk = "activeChnlWalk";
    private static final String prefReadPressure = "readPressure";

    public String siteName = "";
    public int panelGen;
    public int compFlow;
    public int availNumComps;
    public int numComps;
    public int numPens;
    public int chanPerPen;
    public int activePens;
    public int chnlWalkway;
    public int activeChnlWalk;
    public int readPressure;

    private static final String JSON_FILE_NAME = "Conf.txt";
    public static ArrayList<InputData> mArrInputData = new ArrayList<>();

    public void setPreferences(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_main), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(prefSiteName, siteName);
        editor.putInt(prefPanelGen, panelGen);
        editor.putInt(prefCompFlow, compFlow);
        editor.putInt(prefAvailNumComps, availNumComps);
        editor.putInt(prefNumComps, numComps);
        editor.putInt(prefNumPens, numPens);
        editor.putInt(prefChanPerPen, chanPerPen);
        editor.putInt(prefActivePens, activePens);
        editor.putInt(prefChnlWalkway, chnlWalkway);
        editor.putInt(prefActiveChnlWalk, activeChnlWalk);
        editor.putInt(prefReadPressure, readPressure);

        editor.commit();
    }

    public void getPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_main), Context.MODE_PRIVATE);

        siteName = sharedPref.getString(prefSiteName, "");
        panelGen = sharedPref.getInt(prefPanelGen, 0);
        compFlow = sharedPref.getInt(prefCompFlow, 0);
        availNumComps = sharedPref.getInt(prefAvailNumComps, 0);
        numComps = sharedPref.getInt(prefNumComps, 0);
        numPens = sharedPref.getInt(prefNumPens, 0);
        chanPerPen = sharedPref.getInt(prefChanPerPen, 0);
        activePens = sharedPref.getInt(prefActivePens, 0);
        chnlWalkway = sharedPref.getInt(prefChnlWalkway, 0);
        activeChnlWalk = sharedPref.getInt(prefActiveChnlWalk, 0);
        readPressure = sharedPref.getInt(prefReadPressure, 0);

    }

    public static void writeJsonFile(Context context){

        String data = toJSon();
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(JSON_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean readJsonFile(Context context) {
        String strJson = "";
        mArrInputData.clear();

        try {
            FileInputStream fis = context.openFileInput(JSON_FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);//.append("\n");
            }
            strJson = sb.toString();
        } catch (FileNotFoundException e) {
            return  false;
        } catch (UnsupportedEncodingException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        boolean bRet = getValueFromJson(strJson);
        return bRet;
    }

    private static boolean getValueFromJson(String data){

        try {
            JSONArray arrJson = new JSONArray(data);

            for (int i = 0; i < arrJson.length(); i++) {
                JSONObject jsonObject = arrJson.getJSONObject(i);
                InputData inputData = new InputData();
                inputData.siteName = jsonObject.getString(prefSiteName);
                inputData.panelGen = jsonObject.getInt(prefPanelGen);
                inputData.compFlow = jsonObject.getInt(prefCompFlow);
                inputData.availNumComps = jsonObject.getInt(prefAvailNumComps);
                inputData.numComps = jsonObject.getInt(prefNumComps);
                inputData.numPens = jsonObject.getInt(prefNumPens);
                inputData.chanPerPen = jsonObject.getInt(prefChanPerPen);
                inputData.activePens = jsonObject.getInt(prefActivePens);
                inputData.chnlWalkway = jsonObject.getInt(prefChnlWalkway);
                inputData.activeChnlWalk = jsonObject.getInt(prefActiveChnlWalk);
                inputData.readPressure = jsonObject.getInt(prefReadPressure);

                mArrInputData.add(inputData);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String toJSon() {
        try {
            // Here we convert Java Object to JSON
            JSONArray jsonArr = new JSONArray();

            for (InputData data : mArrInputData ) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put(prefSiteName, data.siteName);
                jsonObj.put(prefPanelGen, data.panelGen);
                jsonObj.put(prefCompFlow, data.compFlow);
                jsonObj.put(prefAvailNumComps, data.availNumComps);
                jsonObj.put(prefNumComps, data.numComps);
                jsonObj.put(prefNumPens, data.numPens);
                jsonObj.put(prefChanPerPen, data.chanPerPen);
                jsonObj.put(prefActivePens, data.activePens);
                jsonObj.put(prefChnlWalkway, data.chnlWalkway);
                jsonObj.put(prefActiveChnlWalk, data.activeChnlWalk);
                jsonObj.put(prefReadPressure, data.readPressure);
                jsonArr.put(jsonObj);
            }


            return jsonArr.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static int getSiteCount(){
        if (mArrInputData != null)
            return mArrInputData.size();
        else
            return 0;
    }


}
