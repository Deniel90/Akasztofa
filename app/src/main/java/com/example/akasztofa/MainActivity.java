package com.example.akasztofa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnLe, btnFel, btnTippel;
    private TextView textBetu, textSzoveg;
    private String[] betuk, szavak;
    private ArrayList<String> valasztottBetuk = new ArrayList<>();
    private int betuPoz, akasztottAllapot;
    private String kitalalandoSzo, vonalszo;
    private ImageView imgAkasztofa;
    private AlertDialog.Builder uzenet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        UjJatek();

        btnLe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (betuPoz == 0)
                {
                    betuPoz = betuk.length - 1;
                }
                else
                {
                    betuPoz--;
                }
                textBetu.setText(betuk[betuPoz]);

                //színezés
                if (valasztottBetuk.contains(betuk[betuPoz]))
                {
                    textBetu.setTextColor(Color.BLACK);
                }
                else
                {
                    textBetu.setTextColor(Color.RED);
                }
            }
        });

        btnFel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (betuPoz == betuk.length - 1)
                {
                    betuPoz = 0;
                }
                else
                {
                    betuPoz++;
                }
                textBetu.setText(betuk[betuPoz]);

                //színezés
                if (valasztottBetuk.contains(betuk[betuPoz]))
                {
                    textBetu.setTextColor(Color.BLACK);
                }
                else
                {
                    textBetu.setTextColor(Color.RED);
                }
            }
        });

        btnTippel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ha már volt tippelve a betű
                if (valasztottBetuk.contains(betuk[betuPoz]))
                {
                    Toast.makeText(MainActivity.this, "Ezt a betűt már választottad.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    valasztottBetuk.add(betuk[betuPoz]);
                    textBetu.setTextColor(Color.BLACK);
                    String valasztottBetu = String.valueOf(textBetu.getText());
                    //jó tipp
                    if (kitalalandoSzo.contains(valasztottBetu))
                    {
                        Toast.makeText(MainActivity.this, "Jó tipp!", Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < kitalalandoSzo.length(); i++)
                        {
                            if (kitalalandoSzo.charAt(i) == valasztottBetu.charAt(0))
                            {
                                vonalszo = vonalszo.substring(0, i) + valasztottBetu + vonalszo.substring(i + 1);
                                textSzoveg.setText(vonalszo);
                            }
                        }
                    }
                    //rossz tipp
                    else
                    {
                        Toast.makeText(MainActivity.this, "Rossz tipp!", Toast.LENGTH_SHORT).show();

                        akasztottAllapot++;
                    }

                    switch (akasztottAllapot)
                    {
                        case 0:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa00);break;
                        case 1:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa01);break;
                        case 2:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa02);break;
                        case 3:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa03);break;
                        case 4:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa04);break;
                        case 5:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa05);break;
                        case 6:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa06);break;
                        case 7:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa07);break;
                        case 8:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa08);break;
                        case 9:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa09);break;
                        case 10:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa10);break;
                        case 11:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa11);break;
                        case 12:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa12);break;
                        case 13:
                            imgAkasztofa.setImageResource(R.drawable.akasztofa13);break;
                    }

                    //Alertdialog
                    if (akasztottAllapot < 13 && !vonalszo.contains("_"))
                    {
                        uzenet.setTitle("Megmenekültél!");
                        AlertDialog dialog = uzenet.create();
                        dialog.show();
                    }
                    else if (akasztottAllapot == 13 && vonalszo.contains("_"))
                    {
                        uzenet.setTitle("Felakasztottak!");
                        AlertDialog dialog = uzenet.create();
                        dialog.show();
                    }
                }
            }
        });
    }

    private void init()
    {
        btnLe = findViewById(R.id.btnLe);
        btnFel = findViewById(R.id.btnFel);
        btnTippel = findViewById(R.id.btnTippel);
        textBetu = findViewById(R.id.textBetu);
        textSzoveg = findViewById(R.id.textSzoveg);
        imgAkasztofa = findViewById(R.id.imgAkasztofa);
        betuk = new String[]{"A", "Á", "B", "C", "D", "E", "É", "F", "G", "H", "I", "Í", "J", "K", "L", "M", "N",
                "O", "Ó", "Ö", "Ő", "P", "Q", "R", "S", "T", "U", "Ú", "Ü", "Ű", "V", "W", "X", "Y", "Z"};
        //a magyar ABC minden betűje benne van
        szavak = new String[]{"PRÓBA", "TÜCSÖK", "DÉLIBÁB", "ŰRHAJÓ", "MORZE", "FOGÍNY", "QUEEN", "FŐNIX", "WHISKY", "ÚJVÁROS"};

        uzenet = new AlertDialog.Builder(MainActivity.this);
        uzenet.setCancelable(false).setMessage("Szeretnél új játékot játszani?");
        uzenet.setPositiveButton("Igen", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                UjJatek();
            }
        });
        uzenet.setNegativeButton("Nem", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
    }

    private void UjJatek()
    {
        imgAkasztofa.setImageResource(R.drawable.akasztofa00);
        textBetu.setText(String.valueOf("A"));
        valasztottBetuk.clear();
        akasztottAllapot = 0;
        betuPoz = 0;
        kitalalandoSzo = szavak[(int)(Math.random() * szavak.length)];

        vonalszo = "";
        for (int i = 0; i < kitalalandoSzo.length(); i++)
        {
            vonalszo = vonalszo + "_";
        }
        textSzoveg.setText(vonalszo);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putInt("betuPoz", betuPoz);
        outState.putInt("akasztottAllapot", akasztottAllapot);
        outState.putString("kitalalandoSzo", kitalalandoSzo);
        outState.putString("vonalszo", vonalszo);
        outState.putStringArrayList("valasztottBetuk", valasztottBetuk);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        betuPoz = savedInstanceState.getInt("betuPoz");
        textBetu.setText(betuk[betuPoz]);
        akasztottAllapot = savedInstanceState.getInt("akasztottAllapot");
        switch (akasztottAllapot)
        {
            case 0:
                imgAkasztofa.setImageResource(R.drawable.akasztofa00);break;
            case 1:
                imgAkasztofa.setImageResource(R.drawable.akasztofa01);break;
            case 2:
                imgAkasztofa.setImageResource(R.drawable.akasztofa02);break;
            case 3:
                imgAkasztofa.setImageResource(R.drawable.akasztofa03);break;
            case 4:
                imgAkasztofa.setImageResource(R.drawable.akasztofa04);break;
            case 5:
                imgAkasztofa.setImageResource(R.drawable.akasztofa05);break;
            case 6:
                imgAkasztofa.setImageResource(R.drawable.akasztofa06);break;
            case 7:
                imgAkasztofa.setImageResource(R.drawable.akasztofa07);break;
            case 8:
                imgAkasztofa.setImageResource(R.drawable.akasztofa08);break;
            case 9:
                imgAkasztofa.setImageResource(R.drawable.akasztofa09);break;
            case 10:
                imgAkasztofa.setImageResource(R.drawable.akasztofa10);break;
            case 11:
                imgAkasztofa.setImageResource(R.drawable.akasztofa11);break;
            case 12:
                imgAkasztofa.setImageResource(R.drawable.akasztofa12);break;
            case 13:
                imgAkasztofa.setImageResource(R.drawable.akasztofa13);break;
        }
        kitalalandoSzo = savedInstanceState.getString("kitalalandoSzo");
        vonalszo = savedInstanceState.getString("vonalszo");
        textSzoveg.setText(vonalszo);
        valasztottBetuk = savedInstanceState.getStringArrayList("valasztottBetuk");
    }
}