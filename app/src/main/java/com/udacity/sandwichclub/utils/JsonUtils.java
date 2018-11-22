package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject rootJSON = new JSONObject(json);
            JSONObject nameJSON = rootJSON.getJSONObject("name");
            String mainName = nameJSON.getString("mainName");
            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray alsoKnownAsArray = nameJSON.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }
            String placeOfOrigin = rootJSON.getString("placeOfOrigin");
            String description = rootJSON.getString("description");
            String imageURL = rootJSON.getString("image");
            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientsArray = rootJSON.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageURL, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
