package com.tecla.fastfingers;

import android.content.res.Resources;
import android.widget.TextView;

public abstract class SGI {
	private static String nompaquete = "";
	private static Resources resources; 
	private static TextView entrada;
	private static GameActivity activity;
	private static String[] abecedario = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };
	private static int[] recursos = { 0x7f020000, 0x7f020007, 0x7f020008,
			0x7f02000a, 0x7f02000b, 0x7f02000d, 0x7f02000e, 0x7f02000f,
			0x7f020011, 0x7f020013, 0x7f020014, 0x7f020015, 0x7f020016,
			0x7f020018, 0x7f02000c, 0x7f020019, 0x7f02001a, 0x7f02001c,
			0x7f02001d, 0x7f02001e, 0x7f020021, 0x7f020023, 0x7f020024,
			0x7f020025, 0x7f020026, 0x7f020027, 0x7f020028 };

	public static String getNompaquete() {
		return nompaquete;
	}
	public static void setActivity(GameActivity game){
		SGI.activity = game;
	}
	public static void setText(final String text){
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				activity.setText(text);
				
			}
		});
	}
	public static void setNompaquete(String nompaquete) {
		SGI.nompaquete = nompaquete;
	}	
	public static TextView getEntrada() {
		return entrada;
	}

	public static void setEntrada(TextView nompaquete) {
		SGI.entrada = nompaquete;
	}

	public static int[] getRecursos() {
		return recursos;
	}

	public static void setRecursos(int[] recursos) {
		SGI.recursos = recursos;
	}

	public static String[] getAbecedario() {
		return abecedario;
	}

	public static void setAbecedario(String[] abecedario) {
		SGI.abecedario = abecedario;
	}

	public static Resources getResources() {
		return resources;
	}

	public static void setResources(Resources resources) {
		SGI.resources = resources;
	}

}
