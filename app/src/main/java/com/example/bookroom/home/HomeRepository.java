package com.example.bookroom.home;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.Common.Social;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeRepository {
    ArrayList<Social> socialArrayList;
    MutableLiveData<List<Social>> listMutableLiveData = new MutableLiveData<>();
    Application application;
    SocialDatabase socialDatabase;

    public HomeRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Social>> getSocial() {
        socialDatabase = SocialDatabase.getInstance(application);
        socialArrayList=socialDatabase.getAll();
        Log.e("tag",socialArrayList.toString()+"");

        if (socialArrayList.size()==0) {
            try {
                JSONObject object = new JSONObject(loadJSONFromAsset());
                JSONArray jsonArray = object.getJSONArray("net");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Social social = new Social(jsonObject.getString("name"), jsonObject.getString("icon"), jsonObject.getString("link"));
                    socialDatabase.insertSocial(social);
                }

                socialArrayList = socialDatabase.getAll();
                listMutableLiveData.postValue(socialArrayList);
                Log.e("tag","first");
            } catch (JSONException e) {
                e.printStackTrace();
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                t.
            }
        } else {
            socialArrayList = socialDatabase.getAll();
            listMutableLiveData.postValue(socialArrayList);

        }
        return listMutableLiveData;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream stream = application.getAssets().open("switch.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
