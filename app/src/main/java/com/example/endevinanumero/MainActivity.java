package com.example.endevinanumero;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    int intentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        super.onCreate(savedInstanceState);
        Random random=new Random();
        int randomNumber = 1; //random.nextInt(101);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            EditText inp = findViewById(R.id.editTextNumber2);
            int num = Integer.parseInt(inp.getText().toString());
            if (randomNumber < num) {
                Toast.makeText(context, "El numero que has posat es massa gran", duration).show();
                intentos++;
            } if (randomNumber > num) {
                Toast.makeText(context, "El numero que has posat es massa petit", duration).show();
                intentos++;
            } if (randomNumber == num) {
                intentos++;
                Log.i("pepe",String.valueOf(intentos));
                Intent intent = new Intent(this, UserName.class);
                intent.putExtra("intentos",intentos);
                startActivity(intent);
            }
        });
    }
}