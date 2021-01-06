package com.catata.ahorcado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, IMensaje {
    Spinner spinnerLetras;
    Button btnJugar;
    ImageView ivCuadroJuego;
    final static String URL_IMAGE ="https://webcarlos.com/fotosexamenes/ahorcado/";

    String[] letras;
    String[] palabras;
    String palabraSelccionada;

    String letrasDichas = "";
    String curLetras ="";
    int numError = 0;
    final int MAX_ERRORS = 6;
    final int TIMEOUT = 2000;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();



        ivCuadroJuego = (ImageView)findViewById(R.id.cuadro_juego);

        spinnerLetras = (Spinner) findViewById(R.id.spinner_letras);
        spinnerLetras.setOnItemSelectedListener(this);

        btnJugar = (Button) findViewById(R.id.btn_jugar);
        btnJugar.setOnClickListener(this);

        /*Poblamos el spinner*/
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.letras_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerLetras.setAdapter(adapter);

        restablecerPartida();

    }

    private void restablecerPartida() {
        letras = getResources().getStringArray(R.array.letras_array);
        palabras = getResources().getStringArray(R.array.frutas_array);
        curLetras = letras[0];
        palabraSelccionada = palabras[aleatorio(0,palabras.length-1)];
        numError = 0;

        ponerFotoGlide(numError + 1);

        fragmentManager.beginTransaction()
                .replace(R.id.contenedor_letras, LetrasFragment.newInstance(""))
                .addToBackStack(null)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.contenedor_palabra, PalabrasFragment.newInstance(palabraSelccionada,""))
                .addToBackStack(null)
                .commit();



    }

    private int aleatorio(int i, int i1) {
        return (int)Math.floor(Math.random()*(i1+1)) + i;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_start:
                restablecerPartida();
                return true;
            case R.id.menu_quit:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        letrasDichas += curLetras +"*";

        if(noEstaLetra(curLetras)){
            ponerFotoGlide(++numError + 1);
        }

        if(numError>= MAX_ERRORS){
            //Esperar 2 segundos y mostrar resultado.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, ResultadoActivity.class);
                    Bundle args = new Bundle();
                    args.putBoolean(ResultadoActivity.RESULTADO_TAG, false);
                    args.putInt(ResultadoActivity.ERRORES_TAG, numError);
                    i.putExtras(args);
                    startActivity(i);
                }
            },TIMEOUT);
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor_letras, LetrasFragment.newInstance(letrasDichas))
                    .addToBackStack(null)
                    .commit();

            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor_palabra, PalabrasFragment.newInstance(palabraSelccionada,letrasDichas))
                    .addToBackStack(null)
                    .commit();
        }
    }

    private boolean noEstaLetra(String i) {
        return palabraSelccionada.toUpperCase().indexOf(i)<0;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        curLetras = letras[position];

        //Toast.makeText(this,"Seleccionada letra " + curLetras, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void ponerFotoGlide(int numFoto){
        String url = URL_IMAGE + numFoto + ".png";
        Glide.with(this).load(url)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_loading)
                .into(ivCuadroJuego);
    }

    @Override
    public void onFinishWord(boolean res) {
        if(res){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, ResultadoActivity.class);
                    Bundle args = new Bundle();
                    args.putBoolean(ResultadoActivity.RESULTADO_TAG, true);
                    args.putInt(ResultadoActivity.ERRORES_TAG, numError);
                    i.putExtras(args);
                    startActivity(i);
                }
            },TIMEOUT);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
