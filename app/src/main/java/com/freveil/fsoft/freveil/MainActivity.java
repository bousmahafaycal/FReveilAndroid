package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bouton = null; //
    Button bouton2 = null; //
    Button bouton3 = null; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boutons declaration et ajout des listeners
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(listenerbouton);

        bouton2 = (Button) findViewById(R.id.button2);
        bouton2.setOnClickListener(listenerbouton2);

        bouton3 = (Button) findViewById(R.id.button3);
        bouton3.setOnClickListener(listenerbouton3);

        SharedPreferences settings;
        settings = getSharedPreferences("FPref", 0);
        String adresseIp = settings.getString("adresseIp", "");
        String port = settings.getString("port", "");
        Communication.changeServeur(adresseIp,port);
    }


    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MainActivity.this , MenuReveil.class);
            startActivity(intent) ;
        }

    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MainActivity.this , Configuration.class);
            startActivity(intent) ;
        }

    };

    private View.OnClickListener listenerbouton3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MainActivity.this , Parametres.class);
            startActivity(intent) ;
        }

    };
}
