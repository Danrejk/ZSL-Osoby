package com.example.zsl_osoby;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView listViewOsoby = findViewById(R.id.listViewOsoby);
        Intent intent = getIntent();
        int liczbaOsob = intent.getIntExtra("LICZBA_OSOB", 1);

        String[] imiona = {"Jan", "Krzysztof", "Andrzej", "Wiktor", "Wacław", "Jakub", "Marzenna", "Bożydar", "Donald", "Donek"};
        String[] nazwiska = {"Teslowski", "Dylewski", "Polkowski", "Pliszka", "Jeryś", "Król", "Geleta", "Kępka", "Kępczyński", "Tusk"};

        List<String> osoby = new ArrayList<>();
        List<Integer> losoweLiczby = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < liczbaOsob; i++) {
            String imie = imiona[random.nextInt(imiona.length)];
            String nazwisko = nazwiska[random.nextInt(nazwiska.length)];
            int liczba = random.nextInt(1000000000) + 1;
            osoby.add(imie + " " + nazwisko);
            losoweLiczby.add(liczba);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, osoby) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, parent, false);
                }

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                if (text1 != null && text2 != null) {
                    text1.setText(osoby.get(position));
                    text1.setTextSize(18);
                    text1.setTypeface(null, android.graphics.Typeface.BOLD);

                    text2.setText(String.valueOf(losoweLiczby.get(position)));
                    text2.setTextSize(16);
                }

                return view;
            }
        };

        listViewOsoby.setAdapter(adapter);

        listViewOsoby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fullName = osoby.get(position);
                int randomNumber = losoweLiczby.get(position);

                new AlertDialog.Builder(SecondActivity.this)
                        .setTitle("Dane osobowe")
                        .setMessage("Imię i nazwisko: " + fullName + "\nLosowa liczba: " + randomNumber)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}
