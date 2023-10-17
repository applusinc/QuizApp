package com.applus.quizappdemo;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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


public class JsonWords {

    public ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    public int arraySize;
    public String question, answer, wrongAnswer, _yd;
    private int random1, random2, random3;
    public int yon;
    Random indexrandom = new Random();
    Random wrongRandom = new Random();
    Random trans = new Random();
    public List<Integer> randomNumbers;
    private int currentIndex;
    public Boolean currentTurn;
    public String _en, _tr, _is, _it, _fr, _al, _la, _ru, _ja, _ch, _no, _fl, _ib, _fa;
    public int pos, rastgeleSayi;
    public int sayac;
    private Boolean isInit = false;
    private Context _context;
    private ArrayList<HashMap<String, Object>> privList = new ArrayList<>();
    private Boolean isGet = false;


    public void initJson(Context context, String _currentLanguage){

            _context = context;
            String jsonFileName = "words.json";
            String json = JSONReader.readJSONFromAsset(context, jsonFileName);

            if (json != null) {
                listmap = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

                arraySize = listmap.size() - 1;

            } else {
                Toast.makeText(context, "Json Formatlanırken Bir Hata Meydana Geldi.", Toast.LENGTH_SHORT).show();
            }

            currentTurn = false;
            if (_currentLanguage != null){


        SharedPreferences sharedPreferencess = PreferenceManager.getDefaultSharedPreferences(context);
        privList = new Gson().fromJson(sharedPreferencess.getString(_currentLanguage, null), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
            }

        }
        public void randomQues(String langu, int level, int _pos){
            try{
            int maxItem = calculateItemRange(level);
            int minItem = maxItem - 159;
            if (!isInit){
                sayac = minItem;
                isInit = true;
            }
            if (sayac > maxItem){
                Toast.makeText(_context, "Başa Dönüldü", Toast.LENGTH_SHORT).show();
                sayac = minItem;
            }


            System.out.println(maxItem);
            System.out.println(minItem);

            Random rastgele = new Random();
            Random wrastgele = new Random();
            Random yrastgele = new Random();
            Random zrastgele = new Random();

                System.out.println(privList);
            if(_pos == 10000){
                int curNum = sayac++;
                String arananKelime = listmap.get(curNum).get(langu).toString();
                System.out.println(arananKelime);
                isGet = false;
                if (privList != null) {
                    for (HashMap<String, Object> map : privList) {
                        if (map.containsValue(arananKelime)) {
                            System.out.println("Kelime bulundu.");

                            isGet = true;
                            break; // İlk eşleşmeyi bulduktan sonra döngüyü sonlandırabilirsiniz.
                        }
                    }
                }
                rastgeleSayi = curNum;
                //rastgele.nextInt(maxItem - minItem + 1) + minItem;
                pos = rastgeleSayi;
                System.out.println("pos == 10000");
                System.out.println("_pos".concat(String.valueOf(_pos)));
                System.out.println("pos".concat(String.valueOf(pos)));
            }else{
                pos = rastgeleSayi;
                rastgeleSayi = _pos;


            }
                if (isGet){
                    randomQues(langu, level, 10000);
                }else {
                    System.out.println(minItem);
                    System.out.println(maxItem);

                    int rastgelew = wrastgele.nextInt(listmap.size());
                    yon = yrastgele.nextInt(2);
                    int trans = zrastgele.nextInt(2);
                    _yd = listmap.get(rastgeleSayi).get(langu).toString();
                    System.out.println("rasglele sayi".concat(String.valueOf(rastgeleSayi)));
                    if (currentTurn) {
                        question = listmap.get(rastgeleSayi).get(langu).toString();
                        answer = listmap.get(rastgeleSayi).get("Türkçe").toString();
                        wrongAnswer = listmap.get(rastgelew).get("Türkçe").toString();
                        currentTurn = false;

                    } else {
                        question = listmap.get(rastgeleSayi).get("Türkçe").toString();
                        answer = listmap.get(rastgeleSayi).get(langu).toString();
                        wrongAnswer = listmap.get(rastgelew).get(langu).toString();
                        currentTurn = true;
                    }
                    System.out.println(listmap.get(pos).get("Türkçe").toString());
                    System.out.println(pos);
                    System.out.println(rastgeleSayi);
                    _tr = listmap.get(rastgeleSayi).get("Türkçe").toString();
                    _is = listmap.get(rastgeleSayi).get("İspanyolca").toString();
                    _it = listmap.get(rastgeleSayi).get("İtalyanca").toString();
                    _en = listmap.get(rastgeleSayi).get("İngilizce").toString();
                    _fr = listmap.get(rastgeleSayi).get("Fransızca").toString();
                    _al = listmap.get(rastgeleSayi).get("Almanca").toString();
                    _la = listmap.get(rastgeleSayi).get("Latince").toString();
                    _ru = listmap.get(rastgeleSayi).get("Rusça").toString();
                    _ja = listmap.get(rastgeleSayi).get("Japonca").toString();
                    _ch = listmap.get(rastgeleSayi).get("Çince").toString();
                    _no = listmap.get(rastgeleSayi).get("Norveççe").toString();
                    _fl = listmap.get(rastgeleSayi).get("Flemenkçe").toString();
                    _ib = listmap.get(rastgeleSayi).get("İbranice").toString();
                    _fa = listmap.get(rastgeleSayi).get("Farsça").toString();


                }

        }catch (Exception e){
                System.out.println(e.toString());
        }

    }
    public String getWrongAnswer2(String lan){
        Random rstgl = new Random();
        int rstglsayi = rstgl.nextInt(listmap.size());
        return listmap.get(rastgeleSayi).get(lan).toString();
    }
    public int getSizeMap(){
        return listmap.size();
    }
private int calculateItemRange(int level) {
    int minItem = (level - 1) * 159;
    int maxItem = minItem + 159;

    return maxItem;
}

    public static List<Integer> generateRandomNumbers(int count, int minItem, int maxItem) {
        if (count > (maxItem - minItem + 1)) {
            throw new IllegalArgumentException("Aralıktaki item sayısından fazla bir sayı isteniyor.");
        }

        List<Integer> itemList = new ArrayList<>();
        for (int i = minItem; i <= maxItem; i++) {
            itemList.add(i);
        }

        // Itemleri karıştırın
        Collections.shuffle(itemList);

        // İstenen item sayısına kadar olan kısmı alın
        List<Integer> uniqueItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            uniqueItems.add(itemList.get(i));
        }

        return uniqueItems;
    }
    public void ques(){

    }

}

