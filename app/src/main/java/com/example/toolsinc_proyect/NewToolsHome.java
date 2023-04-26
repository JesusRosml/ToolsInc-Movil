package com.example.toolsinc_proyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class NewToolsHome extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress;
    private Button qrGeneratorButton;
    private ImageView qrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tools_home);

        getSupportActionBar().hide(); // Oculta el toolbar

        Button btnTrabajadores = findViewById(R.id.id_herramientas);
        btnTrabajadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewToolsHome.this, NewHome.class);
                startActivity(intent);
            }
        });

        // Obtener las referencias de los elementos de la vista
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        qrGeneratorButton = findViewById(R.id.qr_generador);
        qrImageView = findViewById(R.id.testexample);

        // Agregar el listener del botón qr_generador
        qrGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos de los EditText
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String address = editTextAddress.getText().toString();

                // Validar que se hayan ingresado todos los datos
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(NewToolsHome.this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Generar el texto que se va a codificar en el QR
                String qrText = "Nombre: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Teléfono: " + phone + "\n" +
                        "Dirección: " + address;

                // Generar el código QR a partir del texto
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 300, 300);
                    int width = bitMatrix.getWidth() + 20; // Aumentar el ancho y la altura en 20 píxeles
                    int height = bitMatrix.getHeight() + 20;
                    Bitmap qrBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            qrBitmap.setPixel(x, y, Color.WHITE); // Establecer los píxeles del borde en blanco
                        }
                    }
                    for (int x = 0; x < bitMatrix.getWidth(); x++) {
                        for (int y = 0; y < bitMatrix.getHeight(); y++) {
                            qrBitmap.setPixel(x + 10, y + 10, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE); // Establecer los píxeles del código QR en negro
                        }
                    }

                    // Guardar la imagen del QR en la carpeta de descargas
                    String fileName = "qrtrabajador_" + System.currentTimeMillis() + ".jpg";
                    File downloadDir = new File(getApplicationContext().getExternalFilesDir(null), "downloads");
                    if (!downloadDir.exists()) {
                        downloadDir.mkdirs();
                    }
                    File file = new File(downloadDir, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    qrBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // Guardar la imagen en formato JPEG

                    // Mostrar la imagen del QR en el ImageView
                    qrImageView.setImageBitmap(qrBitmap);

                    // Mostrar un mensaje indicando la ubicación de la imagen descargada
                    Toast.makeText(NewToolsHome.this, "Imagen descargada en: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

                } catch (WriterException | FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}