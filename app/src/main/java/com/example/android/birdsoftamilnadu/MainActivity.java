package com.example.android.birdsoftamilnadu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView birdsText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.loading_birds);
        birdsText = findViewById(R.id.birds_textView);

        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BirdAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BirdAPI birdAPI = retrofit.create(BirdAPI.class);
        Call<List<BirdModel>> call = birdAPI.getBirds();

        call.enqueue(new Callback<List<BirdModel>>() {
            @Override
            public void onResponse(Call<List<BirdModel>> call, Response<List<BirdModel>> response) {

                List<BirdModel> list = response.body();

                for(int i=0; i<list.size(); i++) {
                    birdsText.append(list.get(i).getSno() + "\n"
                        + list.get(i).getCommon_name() + "\n"
                        + list.get(i).getScientific_name() + "\n\n"
                    );
                }

                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<List<BirdModel>> call, Throwable t) {

                birdsText.setText(t.getCause().toString());

            }
        });

    }
}
