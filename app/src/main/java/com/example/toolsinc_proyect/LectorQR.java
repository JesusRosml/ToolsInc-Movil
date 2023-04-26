package com.example.toolsinc_proyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.widget.Button;

public class LectorQR extends AppCompatActivity {

    Button btn;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);

        btn = findViewById(R.id.buttonLector);
        tv1 = findViewById(R.id.tv1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(LectorQR.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector .QR");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });


        getSupportActionBar().hide(); // Oculta el toolbar

        //Boton para ir a la seccion de trabajadores
        Button btnTrabajadores = findViewById(R.id.id_trabajadores);
        btnTrabajadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LectorQR.this, NewToolsHome.class);
                startActivity(intent);
            }
        });

        //Boton para ir a la seccion de trabajadores
        Button btnHerramientas = findViewById(R.id.id_herramientas);
        btnHerramientas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LectorQR.this, NewHome.class);
                startActivity(intent);
            }
        });

        //New codigo


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Escaneado" + result.getContents(), Toast.LENGTH_SHORT).show();
                tv1.setText("Este codigo QR pertenece : \n" + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}