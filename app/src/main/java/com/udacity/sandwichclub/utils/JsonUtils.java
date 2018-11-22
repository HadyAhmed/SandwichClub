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
            JSONObject rootJSON = new JSONObject(json); // the root json form
            JSONObject nameJSON = rootJSON.getJSONObject("name");
            String mainName = nameJSON.getString("mainName");
            JSONArray alsoKnownAsArray = nameJSON.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = parseStringList(alsoKnownAsArray);
            String placeOfOrigin = rootJSON.getString("placeOfOrigin");
            String description = rootJSON.getString("description");
            String imageURL = rootJSON.getString("image");
            List<String> ingredients = parseStringList(rootJSON.getJSONArray("ingredients"));

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageURL, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method will extract the string in JSONArray Object
     *
     * @return Parsed String List.
     */
    private static List<String> parseStringList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }
}
