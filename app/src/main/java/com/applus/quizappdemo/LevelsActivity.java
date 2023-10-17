package com.applus.quizappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class LevelsActivity extends AppCompatActivity {
    private ListView listview1;
    private String langu;
    private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
    private ImageView backButton;
    private Boolean isListe;
private TextView leveltitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        listview1 = findViewById(R.id.listview1);
        backButton = findViewById(R.id.backbutton);
        leveltitle = findViewById(R.id.leveltitle);
        langu = getIntent().getStringExtra("lan");
        if(langu.equals("Kelime Listem")){
            isListe = true;
            System.out.println("kelime listem true");
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Multi Dil");
                _item.put("id", "all");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İngilizce & İspanyolca Multi Dil");
                _item.put("id", "en_is");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İngilizce");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İspanyolca");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İtalyanca");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Fransızca");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Almanca");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Latince");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Rusça");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Japonca");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Çince");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Norveççe");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Flemenkçe");
                lm.add(_item);
            }

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İbranice");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Farsça");
                lm.add(_item);
            }

        }else {
            System.out.println("kelime lisem false");
isListe = false;
            for (int i = 1; i < 22; i++) {
                if (i != 0) {
                    {
                        HashMap<String, Object> _item = new HashMap<>();
                        _item.put("level", i);
                        lm.add(_item);
                    }
                }
            }
        }
        listview1.setAdapter(new Listview2Adapter(lm));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }








    public class Listview2Adapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = getLayoutInflater();
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.layout, null);
            }
            final TextView textview1 = _view.findViewById(R.id.textview1);
            final LinearLayout linear1 = _view.findViewById(R.id.linear1);
            final TextView lannam = _view.findViewById(R.id.lannam);
            if (isListe){
                textview1.setText(lm.get(_position).get("lanname").toString());
                lannam.setText(" ");
                leveltitle.setText("Özel Listem");

            }else{
                textview1.setText(lm.get(_position).get("level").toString().concat("."));
            }

            linear1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isListe){
                        try {
                            String key = lm.get(_position).get("lanname").toString();
                            if (lm.get(_position).get("lanname").toString().contains("Multi")){
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String jsonValue=  sharedPreferences.getString(lm.get(_position).get("id").toString(), null);
                                if(jsonValue == null){
                                    Toast.makeText(LevelsActivity.this, "Kelime Listeniz Boş", Toast.LENGTH_SHORT).show();
                                }else {
                                    ArrayList<HashMap<String, Object>> privList = new Gson().fromJson(jsonValue, new TypeToken<ArrayList<HashMap<String, Object>>>() {
                                    }.getType());
                                    if (privList.size() == 0) {
                                        Toast.makeText(LevelsActivity.this, "Kelime Listeniz Boş", Toast.LENGTH_SHORT).show();
                                    } else {


                                        Intent i = new Intent();
                                        i.setClass(LevelsActivity.this, PrivMultiActivity.class);
                                        i.putExtra("key", lm.get(_position).get("id").toString());
                                        startActivity(i);
                                    }
                                }
                            }else{
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String jsonValue=  sharedPreferences.getString(lm.get(_position).get("lanname").toString(), null);
                                if(jsonValue == null){
                                    Toast.makeText(LevelsActivity.this, "Kelime Listeniz Boş", Toast.LENGTH_SHORT).show();
                                }else {
                                    ArrayList<HashMap<String, Object>> privList = new Gson().fromJson(jsonValue, new TypeToken<ArrayList<HashMap<String, Object>>>() {
                                    }.getType());
                                    if (privList.size() == 0) {
                                        Toast.makeText(LevelsActivity.this, "Kelime Listeniz Boş", Toast.LENGTH_SHORT).show();
                                    } else {


                                        Intent i = new Intent();
                                        i.setClass(LevelsActivity.this, PrivWordsActivity.class);
                                        i.putExtra("language", lm.get(_position).get("lanname").toString());
                                        startActivity(i);
                                    }
                                }
                            }

                        }catch (Exception e){
                            System.out.println(e.toString());
                        }

                    }else{


                    if(langu.equals("Multi Dil")){
                        Intent i = new Intent();
                        i.setClass(LevelsActivity.this, MultiLangActivity.class);
i.putExtra("ispriv", getIntent().getStringExtra("ispriv"));
                        i.putExtra("level", lm.get(_position).get("level").toString());
                        startActivity(i);
                    }else {

                        Intent i = new Intent();
                        i.setClass(LevelsActivity.this, WordsActivity.class);
                        i.putExtra("lan", langu);
                        i.putExtra("level", lm.get(_position).get("level").toString());
                        startActivity(i);
                    }
                    }
                }
            });



            return _view;
        }
    }


}