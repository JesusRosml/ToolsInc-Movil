package com.example.toolsinc_proyect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Splash_presentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_presentacion);

        Button button = (Button) findViewById(R.id.buttonSig);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash_presentacion.this, loginUser.class);
                startActivity(intent);
            }
        });
    }
}