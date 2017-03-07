package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Parametres extends AppCompatActivity {
    Thread m_objThreadClient;
    Socket clientSocket;
    TextView serverMessage;
    String reconnaissance = null;
    Thread ta;
    EditText champ, champ1;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        serverMessage=(TextView)findViewById(R.id.textView);
        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);


        // On attribue un listener adapté aux vues qui en ont besoin
        bouton.setOnClickListener(listenerbouton);
        bouton2.setOnClickListener(listenerbouton2);


        settings = getSharedPreferences("FPref", 0);
        String adresseIp = settings.getString("adresseIp", "");
        String port = settings.getString("port", "");
        EditText editText = (EditText)findViewById(R.id.champ);
        editText.setText(adresseIp);
        editText = (EditText)findViewById(R.id.champ1);
        editText.setText(port);


        champ= (EditText)findViewById(R.id.champ);
        champ1= (EditText)findViewById(R.id.champ1);
    }

    // Bouton Menu Principal
    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (Parametres.this , MainActivity.class);
            startActivity(intent) ;


        }



    };

    // Bouton connexion (bouton2)
    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences settings = getSharedPreferences("FPref", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("adresseIp", champ.getText().toString());
            editor.putString("port", champ1.getText().toString());
            editor.commit();
            Communication.changeServeur(champ.getText().toString(),champ1.getText().toString());
            Communication.connexion();


            Thread ta;
            ta =new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {

                        @Override

                        public void run() {

                            TextView a = (TextView)findViewById(R.id.textView7);

                            if (Communication.connexionState){
                                System.out.println("[F]reponse : "+Communication.reponse);
                                a.setText("connecté");
                                a.setTextColor(Color.parseColor("#1fbf3a"));
                            } else{
                                a.setText("déconnecté");
                                a.setTextColor(Color.parseColor("#ba2025"));
                            }

                        }});
                }
            });


            ta.start();





        }



    };
}
