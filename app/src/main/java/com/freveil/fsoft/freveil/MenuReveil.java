package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuReveil extends AppCompatActivity {

    Button bouton = null; //
    Button bouton2 = null; //
    Button bouton3 = null; //
    Button bouton4 = null; //
    Button bouton5 = null; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reveil);

        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(listenerbouton);

        bouton2 = (Button) findViewById(R.id.button2);
        bouton2.setOnClickListener(listenerbouton2);

        bouton3 = (Button) findViewById(R.id.button3);
        bouton3.setOnClickListener(listenerbouton3);

        bouton4 = (Button)findViewById(R.id.button4);
        bouton4.setOnClickListener(listenerbouton4);

        bouton5 = (Button) findViewById(R.id.button5);
        bouton5.setOnClickListener(listenerbouton5);

    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MenuReveil.this , MainActivity.class);

            startActivity(intent) ;
        }

    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast toast= Toast.makeText(MenuReveil.this,"Fonctionnalité pas encore dévelopée !",Toast.LENGTH_SHORT);
            toast.show();
        }

    };

    private View.OnClickListener listenerbouton3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Rappel r = new Rappel();
            String rap = r.toString();
            Intent intent = new Intent (MenuReveil.this , ajoutRappel.class);
            intent.putExtra("rappel",rap);
            Toast toast= Toast.makeText(MenuReveil.this,"Ajout d'un rappel",Toast.LENGTH_LONG);
            // toast.show();
            startActivity(intent) ;
        }

    };
    private View.OnClickListener listenerbouton4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MenuReveil.this , modifieRappel.class);
            startActivity(intent) ;
        }

    };

    private View.OnClickListener listenerbouton5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (MenuReveil.this , supprimeRappel.class);
            startActivity(intent) ;
        }

    };

}
