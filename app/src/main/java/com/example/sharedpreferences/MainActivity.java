package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //    ViewBinding (Check buildFeatures in Module-level Gradle file)
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.editText.getText().toString();
                binding.textViewA.setText(text);
                saveData();
            }
        });
    }

    private void saveData() {
        //                Saving data to SharedPreferences-->
        SharedPreferences preferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("textSaved", binding.textViewA.getText().toString());
        editor.apply();
        Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        //        Creating Shared Preferences and Loading data-->
        SharedPreferences preferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        String savedText = preferences.getString("textSaved", "");
        if (!savedText.equals("")) {
            binding.textViewA.setText(savedText);
        }

    }
}