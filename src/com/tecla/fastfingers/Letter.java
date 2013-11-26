package com.tecla.fastfingers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Letter extends GfxObject {
	private Paint paint;
	public Letter(Bitmap bitmap, float x, float y) {
		this.bitmap = bitmap;
		this.x = x;
		paint = new Paint();
		this.y = y;
		
	}
	public void addY(float i) {
		this.y += i;
		
	}
	
	public void draw(Canvas canvas){
		draw(canvas, x, y, paint);
	}

		


		


		
	

	
}