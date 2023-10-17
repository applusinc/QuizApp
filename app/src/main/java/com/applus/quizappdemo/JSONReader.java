package com.applus.quizappdemo;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class JSONReader {
    // JSON dosyasını okumak için kullanılacak metot
    public static String readJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            // JSON dosyasını aç
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];

            // Dosya içeriğini tampona oku
            inputStream.read(buffer);
            inputStream.close();

            // Tampon içeriğini string olarak dönüştür
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}