package com.tecla.fastfingers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.midas.attraction.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

public class WordHandler {
	public List<Word> words;
	private Random random;
	private boolean yaAgregado = false;
	private boolean ganaste = false;
	private boolean colision = false;
	private ArrayList<Word> palabras;
	private int palAct = 0;
	private String[] nivel1 = { "BACTERIA", "HOLA", "CONEJO", "TESORO","EMPUJAR","DOCTOR", "FRUTILLA", "LADRILLO", "MERCADO", "INTERNET",
			"VENTANA", "IGUANA","TORNO", "PUERTA", "GATO","ACERO", "MEDIA", "DERECHA", "PELADO", "PUMA", "ARCILLA", "POEMA", 
			"PESCADO", "CHAU", "LENGUA", "KOALA", "CASA", "OBSERVAR", "PALABRA", "JUEGO","NEGRO", "CHICLE", "TRUCO",
			"HORMIGA", "REMERA", "PARTIDO", "LUZ", "ZAPATILLA", "PASTO", "JUGADOR", "CABEZA", "PODER",
			"CAMELLO", "CASI", "AHORA", "SONIDO", "MADERA", "COMIDA", "FUERZA", "PASTILLA", "PATILLA", "PALILLA",
			"CUIDADO", "SE", "VIENE", "LO", "COMPLICADO","TIRANOSAURIO","KAMCHATKA", "SCHWARZENEGGER"};
	private ArrayList<Float> vectorX = new ArrayList<Float>();
	private GameStatus gameStatus;
	private String ultRec;
	private TextView entrada;
	private Resources resources;

	public WordHandler(Resources resources, int level, float screenWidth,
			float screenHeight, GameStatus gStatus) {
		random = new Random();
		this.gameStatus = gStatus;
		this.resources = resources;
		SGI.setResources(resources);
		entrada = SGI.getEntrada();
		vectorX.add((float) 5);
		vectorX.add((float) 240);
		vectorX.add((float) 300);
		vectorX.add((float) 80);
		vectorX.add((float) 130);
		vectorX.add((float) 190);
		vectorX.add((float) 350);
		words = new ArrayList<Word>();
		float x;
		float y;
		double velocity;
		double direction;
		int rotation;
		Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ass);

		palabras = new ArrayList<Word>();

			agregar();

	}

	private void agregar() {
		float x;
		float y;
		x = 10;
		//x = vectorX.get(random_index(vectorX.size()));
		y = 25;
		if(palAct<nivel1.length){
		palabras.add(new Word(nivel1[palAct], x, y, resources));
		palAct+=1;
		}else{
			
		}
	
	}
	public boolean estaColisionado(){
		return colision;
	}
	public boolean Ganaste(){
		return ganaste;
	}
	private int random_index(int length) {
		return (int) (Math.random() * (length));
	}

	public void update(float screenWidth, float screenHeight) {
		long tiempo = gameStatus.getPassedLevelTime();
		if (tiempo==2 && yaAgregado==false){
			agregar();
			yaAgregado=true;
		}
		String entradaTexto = entrada.getText().toString();
		if (!entradaTexto.equals(ultRec)) {
			ultRec = entradaTexto;
			Log.d("Tecla", "Recibido texto == " + ultRec);

		}
		Iterator<Word> iterador = palabras.iterator();
		Word word;
		int add = 0;
		while(iterador.hasNext()) {
			word = iterador.next();

			if (tiempo < 15) {
				word.aumentarY(2);
			}else if(tiempo < 25){
				word.aumentarY(2.2f);			
			}else{
				word.aumentarY(2.5f);
			}
			
			if (word.getY() >= screenHeight){
				colision = true;
			}
			if (word.getPalabra().equalsIgnoreCase(ultRec)) {
				SGI.setText("");
				if(word.getPalabra().equalsIgnoreCase(nivel1[nivel1.length-1])){
					ganaste = true;
				}
				iterador.remove();
				add++;
			}
			

		}
		
		
		
		
		
		palabras.trimToSize();
		for(int i = 0;i<add;i++){
			agregar();
		}
		
	}

	public void draw(Canvas canvas) {
		for (Word word : palabras) {
			if (word != null) {
				word.draw(canvas);
			}
		}

	}

}