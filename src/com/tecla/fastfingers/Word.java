package com.tecla.fastfingers;

import java.util.ArrayList;
import java.util.Iterator;

import android.R;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Word extends GfxObject {
	private ArrayList<Letter> letras;
	// private Iterator<Letter> iterador;
	private Paint paint;
	private boolean antI = false;
	private String palabra;

	public void aumentarY(float y) {
		for (int i = 0; i < letras.size(); i++) {

			letras.get(i).addY(y);
		}
		this.addY(y);
	}

	public void addY(float i) {
		this.y += i;

	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public float getY() {
		return y;
	}

	public Word(String palabra, float x, float y, Resources resources) {
		this.x = x;

		this.palabra = palabra;
		// bitmaps[1] = BitmapFactory.decodeResource(resources, id);
		//pelele
		letras = new ArrayList<Letter>();
		this.y = y;
		this.paint = new Paint();
		String[] abecedario = SGI.getAbecedario();
		int[] recursos = SGI.getRecursos();
		for (int i = 0; i < palabra.length(); i++) {
			String letra = palabra.charAt(i) + "";
			int recurso = 0;
			for (int subi = 0; subi < abecedario.length; subi++) {
				recurso = recursos[subi];
				if (letra.equalsIgnoreCase(abecedario[subi]))
						letras.add(new Letter(BitmapFactory.decodeResource(resources, recurso), (x + (i * (BitmapFactory.decodeResource(resources, 0x7f020011).getWidth()))), y));
					}
				}
			}

			/*
			 * letras.add(new Letter(BitmapFactory.decodeResource(resources,
			 * R.drawable.a), x, 20));
			 */
		// iterador = letras.iterator();
	//}

	public void draw(Canvas canvas) {
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		canvas.rotate(angle, this.x, this.y);

		for (int i = 0; i < letras.size(); i++) {
			Bitmap bitmapletra = letras.get(i).bitmap;

			canvas.drawBitmap(bitmapletra, letras.get(i).x /*
															 * -
															 * bitmapletra.getWidth
															 * () / 2
															 */,
					letras.get(i).y - bitmapletra.getHeight() / 2, this.paint);

		}

		canvas.restore();
	}

}