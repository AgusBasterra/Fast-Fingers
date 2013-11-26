package com.tecla.fastfingers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class GameEngine {

	Context context;
	Resources resources;

	public float screenWidth;
	public float screenHeight;

	private Paint blackPaint;
	private GameStatus gameStatus;

	private WordHandler wordHandler;

	private final int WAITING_FOR_SURFACE = 0;
	private final int RUNNING = 1;
	private final int GAMEOVER = 3;
	private final int GANASTE = 2;
	private int mode = WAITING_FOR_SURFACE;

	public void Init(Context context) {
		this.context = context;
		resources = context.getResources();

		blackPaint = new Paint();
		blackPaint.setColor(Color.WHITE);
		blackPaint.setStyle(Style.FILL);

		gameStatus = new GameStatus();
	}

	private void setLevel(int level) {
		gameStatus.setLevel(level);
		wordHandler = new WordHandler(resources, level, screenWidth, screenHeight, gameStatus);
	}

	public void onDestroy() {
		try {
		} catch (Exception e) {
		}
	}
	public void setSurfaceDimensions(int width, int height) {
		screenWidth = width;
		screenHeight = height;
		if (mode == WAITING_FOR_SURFACE) {
			setLevel(1);
		}
		mode = RUNNING;
		gameStatus.updateSurfaceDimensions(width, height);
	}

	public void update() {
		switch (mode) {
		case (WAITING_FOR_SURFACE):
			break;// There is no screen to do something

		case (RUNNING):
			gameStatus.update();
			if (gameStatus.getPassedLevelTime() >= -1)
			//if (gameStatus.getPassedLevelTime() > 0) {
				wordHandler.update(screenWidth, screenHeight);
				/*if (wordHandler.words.isEmpty()) {
					setLevel(gameStatus.getLevel() + 1);
				}*/
				if(wordHandler.estaColisionado()){
					mode = GAMEOVER;
				}
				if(wordHandler.Ganaste()){				
					mode = GANASTE;
								
				}
				break;

			//}
		case (GAMEOVER):
			// Nothing here yet.
			break;
		}

	}

	public void draw(Canvas canvas) {

		switch (mode) {
		case (WAITING_FOR_SURFACE): {
			break;// There is no screen to do something///
		}
		case (RUNNING): {
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
					blackPaint);
			wordHandler.draw(canvas);
			break;
		}
		case GAMEOVER: {
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
					blackPaint);
			wordHandler.draw(canvas);
			gameStatus.drawGameOverScreen(canvas, screenHeight, screenWidth);
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
					blackPaint);
			gameStatus.drawGameOverScreen(canvas, screenHeight, screenWidth);
			break;
		}
		case GANASTE: {
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
					blackPaint);
			wordHandler.draw(canvas);
			gameStatus.drawGanaste(canvas, screenHeight, screenWidth);
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
					blackPaint);
			gameStatus.drawGanaste(canvas, screenHeight, screenWidth);
			break;
		}
		}
	}
}