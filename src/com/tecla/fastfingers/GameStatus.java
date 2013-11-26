package com.tecla.fastfingers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameStatus {
	private Paint textPaint;
	private int level;
	private long levelStartTime;
	private long passedLevelTime;
	private long passedLevelTimeLastLevel;

	private float smallTextSize = 25;
	private float mediumTextSize = 30;
	private float largeTextSize = 50;

	private static final long GRACE_PERIOD = 5000000000L;

	public GameStatus() {
		textPaint = new Paint();
		textPaint.setColor(Color.LTGRAY);
	}

	public void updateSurfaceDimensions(int width, int height) {
		// 25, 30 and 50 are perfect sizes on a 320 points wide screen
		smallTextSize = width / (320 / 25);
		mediumTextSize = width / (320 / 30);
		largeTextSize = width / (320 / 50);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		levelStartTime = System.nanoTime() + GRACE_PERIOD;
	}

	public long getPassedLevelTime() {
		return passedLevelTime;
	}


	public void update() {
		passedLevelTime = (System.nanoTime() - levelStartTime) / 1000000000L;
	}

	public void drawGanaste(Canvas canvas, float screenHeight, float screenWidth) {
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setTextSize(largeTextSize);
		canvas.drawText("GANASTE", screenWidth / 2,
				(float) (screenHeight * 0.50), textPaint);
		textPaint.setTextSize(smallTextSize);
	}

	public void drawGameOverScreen(Canvas canvas, float screenHeight,
			float screenWidth) {
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setTextSize(largeTextSize);
		canvas.drawText("GAME OVER", screenWidth / 2,
				(float) (screenHeight * 0.50), textPaint);
		textPaint.setTextSize(smallTextSize);

	}
}