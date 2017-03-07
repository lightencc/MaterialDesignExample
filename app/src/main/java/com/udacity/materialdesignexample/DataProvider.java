package com.udacity.materialdesignexample;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 陈超 on 2017/3/7.
 */

public class DataProvider {

    public static List<ArticleModel> getArticles(Context context){
        Gson gson = new Gson();
        String json = "";
        try {
            InputStream input = context.getAssets().open("data.json");
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = input.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            json = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ArticleModel> articles = gson.fromJson(json, new TypeToken<List<ArticleModel>>(){}.getType());
        return articles;
    }
}
