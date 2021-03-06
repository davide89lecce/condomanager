package com.gambino_serra.condomanager_amministratore.View.SezioneStabile.Stabile_Interventi.Intervento.CambiaFornitoreIntervento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gambino_serra.condomanager_amministratore.tesi.R;

import java.util.ArrayList;


public class CategoriaCambiaFornitore extends AppCompatActivity{

    private static final String MY_PREFERENCES = "preferences";
    private static final String LOGGED_USER = "username";

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private ArrayList<DataCategoriaCambiaFornitore> data;
    String username;
    DataCategoriaCambiaFornitore categoria;
    FloatingActionButton fab;
    public int row;
    Button btnAvanti;
    Button btnIndietro;
    Bundle bundle;

    static String idStabile;
    static String foto;
    String idIntervento;
    String idSegnalazione;
    String segnalazione;
    String usernameCondomino;
    String idCondominio;
    String condominio;
    String condomino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleziona_categoria_intervento);

        final SharedPreferences sharedPrefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        // recupera dalle shared i dettagli circa l'id stabile e la foto da allegare nel caso in cui ci sia
        bundle = getIntent().getExtras();
        idStabile = bundle.get("idStabile").toString();
        idIntervento = bundle.get("idInterventoRifiutato").toString();
        foto = bundle.get("foto").toString();


        btnAvanti = (Button) findViewById(R.id.btnAvanti);
        btnIndietro = (Button) findViewById(R.id.btnIndietro);


        removedItems = new ArrayList<Integer>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_categoria);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataCategoriaCambiaFornitore>();
        categoria = new DataCategoriaCambiaFornitore("elettricista","lavori di tipo elettrici ");
        data.add(categoria);
        categoria = new DataCategoriaCambiaFornitore("fabbro","lavori fabbrili (porte, ringhiere, cancelli)");
        data.add(categoria);
        categoria = new DataCategoriaCambiaFornitore("idraulico","lavori idraulici (tubature, autoclave, riscaldamento)");
        data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("edilizia","lavori di muratura, pavimentazioni/piastrelleria, tetto/solaio, tinteggiatura");
        //data.add(categoria);
        categoria = new DataCategoriaCambiaFornitore("giardiniere","lavori di giardinaggio");
        data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("ascensorista","lavori riguardante l'ascensore");
        //data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("servizi di pulizia","lavori di pulizia interna allo stabile e del cortile condominiale");
        //data.add(categoria);
        categoria = new DataCategoriaCambiaFornitore("falegname","lavori di falegnameria");
        data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("vetraio","lavori di vetreria");
        //data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("antennista","lavori riguardanti l'antenna TV");
        //data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("automazione","lavori riguardanti automazione per serramenti, controllo accessi, domotica");
        //data.add(categoria);
        //categoria = new DataCategoriaCambiaFornitore("sicurezza","lavori riguardanti impianti antintrusione, videosorveglianza, rilevazione incendi, estintori");
        //data.add(categoria);
        categoria = new DataCategoriaCambiaFornitore("disinfestatore","lavori riguardanti impianti antintrusione, videosorveglianza, rilevazione incendi, estintori");
        data.add(categoria);

        adapter = new AdapterCategoriaCambiaFornitore(data,this,this);
        recyclerView.setAdapter(adapter);


        btnAvanti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bundle = new Bundle();
                bundle.putString("idStabile", idStabile);
                bundle.putString("foto", foto);
                bundle.putString("idInterventoRifiutato", idIntervento);
                bundle.putString("categoria", sharedPrefs.getString("categoria", ""));

                Log.d("Ciao", " " + idIntervento);

                Intent intent = new Intent(getApplicationContext(), FornitoreCambiaFornitore.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btnIndietro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }



}
