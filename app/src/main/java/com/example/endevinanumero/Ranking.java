package com.example.endevinanumero;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Ranking extends Activity {
    static ArrayList<Integer> intentosarray = new ArrayList<Integer>();
    static ArrayList<String> jugadores = new ArrayList<String>();
    static ArrayList<Uri> imagen = new ArrayList<Uri>();

    class Record {
        public int intents;
        public String nom;
        public Uri foto;

        public Record(String _nom, Uri uri,int _intents ) {
            intents = _intents;
            nom = _nom;
            foto = uri;

        }
    }

    ArrayList<Record> records;
    ArrayAdapter<Record> adapter;
    public Ranking() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        Bundle bundle = getIntent().getExtras();
        String Nick = bundle.getString("nick");
        String Foto = bundle.getString("imagen");
        int intentos = bundle.getInt("intentos");
        intentosarray.add(intentos);
        jugadores.add(Nick);
        imagen.add(Uri.fromFile(new File(Foto)));
        records = new ArrayList<Record>();
        for(int z = 0; z < jugadores.size() ; z++){
            records.add(new Record(jugadores.get(z),imagen.get(z),intentosarray.get(z)));
        }
        adapter = new ArrayAdapter<Record>( this, R.layout.listaranking, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                if( convertView==null ) {
                    convertView = getLayoutInflater().inflate(R.layout.listaranking, container, false);
                }
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                ((ImageView) convertView.findViewById(R.id.imageView)).setImageURI(getItem(pos).foto);

                return convertView;
            }

        };

        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);
    }
}
