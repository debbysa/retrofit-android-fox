package com.debbysa.tugasretrofitapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.debbysa.tugasretrofitapi.generator.ServiceGenerator;
import com.debbysa.tugasretrofitapi.model.Fox;
import com.debbysa.tugasretrofitapi.services.FoxService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private TextView textAnswer;

    private ImageView iconImage;
    private FoxService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAnswer = findViewById(R.id.textView);
        iconImage = findViewById(R.id.iconImage);
        service = ServiceGenerator.createService(FoxService.class);

        Button moreButton = findViewById(R.id.button_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreNasa();
            }
        });


    }

    private void moreNasa() {
        Call<Fox> foxResponse = service.getRandomNasa();
        foxResponse.enqueue(new Callback<Fox>() {
            @Override
            public void onResponse(Call<Fox> call, Response<Fox> response) {
                Fox nasa = response.body();
                Picasso.get().load(nasa.getImage()).into(iconImage);
                textAnswer.setText(nasa.getLink());
            }

            @Override
            public void onFailure(Call<Fox> call, Throwable t) {
                Log.e(TAG, t.toString());
                String message = "Failed to get more yesno, please check your connection.";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
