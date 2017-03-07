package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class affichageCommande extends AppCompatActivity {
    String rap;
    Rappel r = new Rappel();
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_commande);

        Intent intent2 = getIntent();
        if (intent2 != null){
            try{
                rap = intent2.getStringExtra("rappel");
                r.open(rap);
            }catch (Exception e){

            }

        }

        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(listenerbouton);

        String chaine = "";
        for (int i = 0; i < r.part1.size();i++){
            chaine += (i+1)+" : "+r.part1.get(i)+"\n";
        }
        TextView id1 = (TextView)findViewById(R.id.textView3);
        id1.setText(chaine);

        chaine = "";
        for (int i = 0; i < r.part2.size();i++){
            chaine += (i+1)+" : "+r.part2.get(i)+"\n";
        }
        TextView id2 = (TextView)findViewById(R.id.textView5);
        id2.setText(chaine);
    }

    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (affichageCommande.this , ajoutRappel.class);
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (affichageCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };
}
