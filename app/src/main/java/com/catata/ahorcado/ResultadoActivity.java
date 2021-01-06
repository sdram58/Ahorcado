package com.catata.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {

    public static final String RESULTADO_TAG ="Resultado tag";
    public static final String ERRORES_TAG ="Errores tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        if(getIntent().hasExtra(RESULTADO_TAG) && getIntent().hasExtra(ERRORES_TAG)){
            if(getIntent().getBooleanExtra(RESULTADO_TAG,false)){
                //Todo Mostrar errores y animación
                Toast.makeText(this," Has ganado con " + getIntent().getIntExtra(ERRORES_TAG,0) + " errores",Toast.LENGTH_SHORT).show();
            }else{
                //Todo Mostrar victoria y animación
                Toast.makeText(this," Has perdido con " + getIntent().getIntExtra(ERRORES_TAG,0) + " errores",Toast.LENGTH_SHORT).show();
            }
        }
    }
}