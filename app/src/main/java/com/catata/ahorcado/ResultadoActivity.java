package com.catata.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class ResultadoActivity extends AppCompatActivity {

    public static final String RESULTADO_TAG ="Resultado tag";
    public static final String ERRORES_TAG ="Errores tag";

    LottieAnimationView animacion;

    TextView tvResultado, tvFallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        animacion = (LottieAnimationView) findViewById(R.id.animationView);

        tvFallos = (TextView)findViewById(R.id.tvFallos);
        tvResultado = (TextView)findViewById(R.id.tvResultado);

        if(getIntent().hasExtra(RESULTADO_TAG) && getIntent().hasExtra(ERRORES_TAG)){
            if(getIntent().getBooleanExtra(RESULTADO_TAG,false)){
                tvResultado.setText(getString(R.string.victoria));
                tvResultado.setTextColor(Color.parseColor("#00FF00"));
                tvFallos.setTextColor(Color.parseColor("#00FF00"));
                ((View)tvResultado.getParent()).setBackgroundColor(Color.parseColor("#CC96F1"));
            }else{
                tvResultado.setText(getString(R.string.derrota));
                tvResultado.setTextColor(Color.parseColor("#FF0000"));
                tvFallos.setTextColor(Color.parseColor("#FF0000"));
                ((View)tvResultado.getParent()).setBackgroundColor(Color.parseColor("#CCCCCC"));
            }
            tvFallos.setText("Has tenido " +getIntent().getIntExtra(ERRORES_TAG,0) + " errores");

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animacion.playAnimation();
            }
        },1000);

    }
}