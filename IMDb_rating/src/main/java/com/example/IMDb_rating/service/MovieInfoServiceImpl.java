package com.example.IMDb_rating.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class MovieInfoServiceImpl implements  MovieInfoService {
    @Override
    public String getMovieRating(String movie_title) {
        try{
            String api_key="28f8f9ed";
            //movie_title.replace(' ','+');
            URL url = new URL("http://www.omdbapi.com/?t="+ URLEncoder.encode(movie_title, String.valueOf(StandardCharsets.UTF_8))+"&apikey="+api_key);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode!=200){
                throw new RuntimeException("HttpResponseCode : "+responseCode);
            }else{
                String data="";
                Scanner sc=new Scanner(url.openStream());

                while(sc.hasNext()){
                    data+=sc.nextLine();
                }

                sc.close();

                JSONParser parser=new JSONParser();
                JSONObject object=(JSONObject) parser.parse(data);
                JSONArray array=(JSONArray) object.get("Ratings");
                JSONObject obj=(JSONObject) array.get(0);
                return obj.get("Value").toString();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
