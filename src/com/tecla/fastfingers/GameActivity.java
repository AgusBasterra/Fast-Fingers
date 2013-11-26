package com.tecla.fastfingers;

import com.midas.attraction.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends Activity {
private TextView entrada;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        SGI.setNompaquete(getPackageName());
         entrada = (TextView) findViewById(R.id.entradaText);
        SGI.setEntrada(entrada);
        SGI.setActivity(this);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    public void setText(String text){
    	entrada.setText(text);
    }
}
