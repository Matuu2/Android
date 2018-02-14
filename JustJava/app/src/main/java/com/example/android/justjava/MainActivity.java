package com.example.android.justjava;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;





public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.aikaspinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.aika_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public class kysymys{
        private Random randomGenerator;
        private ArrayList<String> catalogue;
        private Spinner aika_spinner = (Spinner) findViewById(R.id.aikaspinner);

        public kysymys()
        {
            catalogue = new ArrayList<String>();
            String aika = aika_spinner.getSelectedItem().toString();
            if (aika.equals("0")){
                catalogue.add("CS?");
                catalogue.add("Onko teillä hetki aikaa puhua CS:stä ja pelata CS:ää?");
                catalogue.add("Kohta cs?");
                catalogue.add("CS&ES&TS?");
                catalogue.add("Miten ois cs?");
                catalogue.add("Pelattaisko vähän cs:ää?");
                catalogue.add("Yks cs?");
                catalogue.add("Oisko cs?");
                catalogue.add("JACKPOT!!! Olette voittaneet kärryajelun CS:ssä!!!" +
                        " Milloin haluatte lunastaa voittonne?");
                catalogue.add("Haluatko menettää rankkisi?");
                catalogue.add("cs? :)");
                catalogue.add("Olisitteko mahdollisesti kiinnostuneet pelaamaan" +
                        " vähän Counter Strike Global offensivea?");
                catalogue.add("cs?");
            }else if (aika.equals("60")) {
                catalogue.add("Oisko tunnin päästä cs?");
            }else if (aika.equals("90")){
                catalogue.add("Miten ois 1,5h päästä cs?");
            }else if (aika.equals("120")){
                catalogue.add("Parin tunnin päästä cs?");
            }else{
                catalogue.add("About " + aika + " minuutin päästä cs?");
                catalogue.add(aika + " min ja sit cs?");
                catalogue.add("cs hetken päästä (" + aika + " min)");
            }
            randomGenerator = new Random();
        }

        public String arvokysymys()
        {
            int index = randomGenerator.nextInt(catalogue.size());
            String kysymys = catalogue.get(index);
            return kysymys;
        }
    }
    public void laheta_viesti(View view) {
        String toNumber = "+358 44 4444444"; // contains spaces.
        toNumber = toNumber.replace("+", "").replace(" ", "");

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
        kysymys kysymyslista = new kysymys();
        sendIntent.putExtra(Intent.EXTRA_TEXT, kysymyslista.arvokysymys());
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}