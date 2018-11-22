package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    // a place holder for text if wasn't found within the data passed from json
    private static final String NOT_PROVIDED = "Not Provided";
    private TextView alsoKnownAs, placeOfOrigin, description, ingredients;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = 0;
        if (intent != null) {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
            if (position == DEFAULT_POSITION) {
                // EXTRA_POSITION not found in intent
                closeOnError();
                return;
            }
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (sandwich.getAlsoKnownAs().size() == 0) {
            alsoKnownAs.setText(NOT_PROVIDED);
        } else {
            for (String item : sandwich.getAlsoKnownAs()) {
                alsoKnownAs.append(item + " ");
            }
        }
        if (sandwich.getIngredients().size() == 0) {
            ingredients.setText(NOT_PROVIDED);
        } else {
            for (String item : sandwich.getIngredients()) {
                ingredients.append(item + " ");
            }
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOrigin.setText(NOT_PROVIDED);
        } else {
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        }
        if (sandwich.getDescription().isEmpty()) {
            description.setText(NOT_PROVIDED);
        } else {
            description.setText(sandwich.getDescription());
        }
    }
}
