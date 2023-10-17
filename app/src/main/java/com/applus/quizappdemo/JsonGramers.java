package com.applus.quizappdemo;


import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.applus.quizappdemo.JSONReader;
import java.util.Random;


public class JsonGramers {

    public ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    public int arraySize;
    public int num;
    public String en, tr;



    public void initJson(Context context, String type) {
        String jsonFileName = type.concat(".json");
        String json = JSONReader.readJSONFromAsset(context, jsonFileName);

        if (json != null) {
            listmap = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>() {
            }.getType());
            arraySize = listmap.size() - 1;

        } else {
            Toast.makeText(context, "Json Formatlanırken Bir Hata Meydana Geldi.", Toast.LENGTH_SHORT).show();
        }
    }


    public void randomQues(String langu, int _pos){
        try{
            Random rastgele = new Random();

            if(_pos == 10000){
                num = rastgele.nextInt(listmap.size());
            }else{
                num = _pos;
            }
            tr = listmap.get(num).get("Türkçe").toString();
            en = listmap.get(num).get(langu).toString();
        }catch (Exception e){
            System.out.println(e.toString());
        }



    }

}
