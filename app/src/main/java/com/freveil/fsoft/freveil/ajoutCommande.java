package com.freveil.fsoft.freveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ajoutCommande extends AppCompatActivity {
    String isModifie = "b";
    ArrayList<String> listeCommande = new ArrayList<String>();
    Spinner spinner;
    String rap;
    Rappel r = new Rappel();
    LinearLayout linearLayout;
    ArrayList<TextView> listeText = new ArrayList<TextView>();
    ArrayList<EditText> listeEditText = new ArrayList<EditText>();
    EditText eid;
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_commande);

        eid = (EditText)findViewById(R.id.editText);
        eid.addTextChangedListener(textWatcher);
        isModifie = "b";
        Intent intent2 = getIntent();
        if (intent2 != null){
            try{
                rap = intent2.getStringExtra("rappel");
                r.open(rap);
            }catch (Exception e){

            }try{
                isModifie = intent2.getStringExtra("modifie");
            }catch (Exception e){

            }

        }
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        TextView id1;
        EditText ed1;





        spinner   = (Spinner)findViewById(R.id.spinner);
        Button bouton = null;
        bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(  listenerbouton  );

        Button bouton2 = null;
        bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(  listenerbouton2  );

        // NORMALEMENT : communiquation serveur et récuperation
        //Communication.envoieCommande(Communication.createCommande("getCommande",r.toString()));
        //listeCommande = Communication.getArrayListResponse();
        listeCommande.add("musique");
        listeCommande.add("citation");
        listeCommande.add("synthese");
        listeCommande.add("synthese_heure");
        listeCommande.add("synthese_meteo");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listeCommande);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


    private View.OnClickListener listenerbouton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            boolean part1 = true;
            RadioGroup radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
            if (radiogroup.getCheckedRadioButtonId()!= R.id.radioButton ){
                part1 = false;
            }
            ArrayList<String> liste = new ArrayList<String>();
            for (int i = 0; i < listeEditText.size();i++){
                if (! listeEditText.get(i).getText().toString().equals(""))
                    liste.add(listeEditText.get(i).getText().toString());
            }
            try{
                r.addCommande(part1,spinner.getSelectedItem().toString(),liste);
                Toast toast= Toast.makeText(ajoutCommande.this,"Commande ajoutée !",Toast.LENGTH_LONG);
                toast.show();
            }catch (Exception e){
                Toast toast= Toast.makeText(ajoutCommande.this,"Ajout de commande annulé !",Toast.LENGTH_LONG);
                toast.show();
            }

            Intent intent = new Intent (ajoutCommande.this , ajoutRappel.class);
            settings = getSharedPreferences("FPref", 0);
            if (settings.getBoolean("modifie", false))
                intent = new Intent (ajoutCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };

    private View.OnClickListener listenerbouton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (ajoutCommande.this , ajoutRappel.class);
            Toast toast= Toast.makeText(ajoutCommande.this,"Ajout de commande annulé",Toast.LENGTH_LONG);
            toast.show();

            if (isModifie.equals("a"))
                intent = new Intent (ajoutCommande.this , modificationRappel.class);
            intent.putExtra("rappel",r.toString());
            startActivity(intent) ;


        }



    };


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //after text changed
            try{
                int a = Integer.parseInt(eid.getText().toString());
                int size = listeText.size();
                if (listeText.size() > a){
                    for (int i = size-1;i>= a;i--){
                        linearLayout.removeView(listeText.get(i));
                        linearLayout.removeView(listeEditText.get(i));
                        listeText.remove(i);
                        listeEditText.remove(i);
                    }
                }else if (listeText.size()<a){
                    TextView id1;
                    EditText ed1;
                    for (int i = 0; i < a-size;i++){
                        id1 = new TextView(getBaseContext());
                        ed1 = new EditText(getBaseContext());
                        ed1.setTextColor(Color.BLACK);
                        id1.setText("Donnez l'argument n°"+(size+i+1));
                        listeText.add(id1);
                        ed1.setPadding(0,0,0,20);
                        listeEditText.add(ed1);
                        linearLayout.addView(id1);
                        linearLayout.addView(ed1);
                    }
                }
            }
            catch (Exception e){

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
