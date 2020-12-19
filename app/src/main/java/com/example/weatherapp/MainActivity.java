package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Retrofit.APIclient;
import com.example.weatherapp.Retrofit.APIinterface;
import com.example.weatherapp.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView searchText, temp, desc, humidity, minMax;
    ImageView searchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.searchText);
        temp = findViewById(R.id.temp);
        desc = findViewById(R.id.desc);
        humidity = findViewById(R.id.humidity);
        searchImage = findViewById(R.id.searchImage);
        minMax = findViewById(R.id.minMax);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWeatherData(searchText.getText().toString().trim());
                
            }
        });
    }

    private void getWeatherData(String name){

        APIinterface apIinterface = APIclient.getClient().create(APIinterface.class);
        Call<Example> call = apIinterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                String cityName = searchText.getText().toString().toUpperCase();

                temp.setText(cityName+" "+"~"+" "+response.body().getMain().getTemp().toString()+"°");
                desc.setText("Feel Like:"+" "+response.body().getMain().getFeels_like().toString()+"°");
                humidity.setText("Humidity:"+" "+response.body().getMain().getHumidity().toString());
                minMax.setText("Min/Max"+":"+" "+response.body().getMain().getTemp_min().toString()+"/"+ response.body().getMain().getTemp_max().toString()+"°");
                searchText.setText(null);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}