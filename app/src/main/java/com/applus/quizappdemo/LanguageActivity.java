package com.applus.quizappdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class LanguageActivity extends AppCompatActivity {
    private ListView listview1;
    private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        listview1 = findViewById(R.id.listview1);
        backButton = findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!lm.isEmpty()){
            lm.clear();
        }if(getIntent().getStringExtra("cat").equals("word")) {

            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Multi Dil");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Kelime Listem");
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
            if (getIntent().getStringExtra("cat").equals("gramers")){


            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İngilizce");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Almanca");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "Fransızca");
                lm.add(_item);
            }
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("lanname", "İspanyolca");
                lm.add(_item);
            }
            }else {
                {
                    HashMap<String, Object> _item = new HashMap<>();
                    _item.put("lanname", "İngilizce");
                    lm.add(_item);
                }
            }
        }


        listview1.setAdapter(new Listview1Adapter(lm));

    }

    public class Listview1Adapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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

            final LinearLayout linear1 = _view.findViewById(R.id.linear1);
            final TextView textview1 = _view.findViewById(R.id.textview1);
            final LinearLayout linear2 = _view.findViewById(R.id.linear2);

            final TextView level = _view.findViewById(R.id.level);
            final TextView lannam = _view.findViewById(R.id.lannam);

            lannam.setText(_data.get((int)_position).get("lanname").toString());
//            final long poss = _position + 1;
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_view.getContext());
//            int level1 = sharedPreferences.getInt(_data.get((int)_position).get("lanname").toString(), -1);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            int levelToSave = 1;
//            int levelin;

//            if (level1 == -1) {
//
//
//
//                // Kaydetmek istediğiniz level değeri
//                editor.putInt(_data.get((int)_position).get("lanname").toString(), levelToSave);
//                editor.commit();
//                levelin = 1;
//                level.setText("Level: ".concat(String.valueOf(levelin)));
//            }else{
//                levelin = level1;
//                level.setText("Level: ".concat(String.valueOf(levelin)));
//            }

int pos = _position + 1;
            textview1.setText(String.valueOf(pos).concat("."));



            linear1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getIntent().getStringExtra("cat").equals("word")) {
                        if (_data.get(_position).get("lanname").toString().equals("Multi Dil")){
                            String[] listItems = {"Bütün Diller", "İngilizce & İspanyolca"};
                            int[] checkedItem = {-1};
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(LanguageActivity.this);
                            alertDialog.setTitle("Seçiniz");
                            alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {

                                checkedItem[0] = which;
                                if (listItems[checkedItem[0]].equals("Bütün Diller")){
                                    Intent i = new Intent();
                                    i.putExtra("lan", _data.get((int) _position).get("lanname").toString());
                                    i.putExtra("ispriv", "false");
                                    i.setClass(LanguageActivity.this, LevelsActivity.class);
                                    startActivity(i);
                                }else{
                                    Intent i = new Intent();
                                    i.putExtra("lan", _data.get((int) _position).get("lanname").toString());
                                    i.putExtra("ispriv", "true");
                                    i.setClass(LanguageActivity.this, LevelsActivity.class);
                                    startActivity(i);
                                }
                                dialog.dismiss();
                            });
                            alertDialog.setNegativeButton("Geri", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Geri düğmesine tıklama işlemi burada işlenir
                                    dialog.dismiss(); // AlertDialog'ı kapatmak için
                                }
                            });


// AlertDialog'ı oluştur ve göster
                            AlertDialog customAlertDialog = alertDialog.create();
                            customAlertDialog.show();







                        }else {
                            Intent i = new Intent();
                            i.putExtra("lan", _data.get((int) _position).get("lanname").toString());
                            i.setClass(LanguageActivity.this, LevelsActivity.class);
                            startActivity(i);
                        }
                    }else {
                        Intent i = new Intent();
                        i.putExtra("lan", _data.get((int) _position).get("lanname").toString());
                        i.putExtra("type", getIntent().getStringExtra("cat"));
                        i.setClass(LanguageActivity.this, GramersActivity.class);
                        startActivity(i);
                    }







                }
            });
            return _view;
        }
    }
}