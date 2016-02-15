package com.example.sashok.nytimy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by asmurthy on 2/13/16.
 */
@Parcel
public class Article {


    String webUrl;

    public Article() {
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    String headline;
    String thumbNail;

    public Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbNail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }

        } catch (JSONException e) {

        }
    }

    public static ArrayList<Article> fromJsonArray(JSONArray array){
        ArrayList<Article> results = new ArrayList<>();

        for (int x = 0; x < array.length() ; x++){
            try {
                Article article = new Article(array.getJSONObject(x));
                results.add(article);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
