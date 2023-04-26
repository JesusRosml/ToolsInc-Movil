package com.example.toolsinc_proyect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class loginUser extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        // Obtener referencias a los elementos de la vista
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.my_button);

        // Configurar el listener del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (email.equals("jesusperezhidalgo50@gmail.com") && password.equals("1234567890")) {
                    // Iniciar sesión correctamente y pasar a la siguiente panta
                    Intent intent = new Intent(loginUser.this, NewHome.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Mostrar un mensaje de error indicando que los credenciales son incorrectos
                    Toast.makeText(loginUser.this, "Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}





