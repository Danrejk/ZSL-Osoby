package com.example.zsl_osoby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputLiczbaOsob = findViewById(R.id.inputLiczbaOsob);
        Button buttonGeneruj = findViewById(R.id.buttonGeneruj);

        buttonGeneruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liczbaOsobStr = inputLiczbaOsob.getText().toString();

                if (!liczbaOsobStr.isEmpty()) {
                    try {
                        int liczbaOsob = Integer.parseInt(liczbaOsobStr);
                        if (liczbaOsob > 0) {
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            intent.putExtra("LICZBA_OSOB", liczbaOsob);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Wpisz liczbę większą od 0", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Wprowadź prawidłową liczbę", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wpisz liczbę osób", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
