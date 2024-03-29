package com.tecla.fastfingers;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GfxObject {

	protected Bitmap bitmap;
	protected Bitmap bitmapHighlighted;

	protected float x;
	protected float y;

	private double velocity = 0;
	private double direction = 0;

	private double dX = 0;
	private double dY = 0;

	private boolean looped = false;

	protected int rotation = 0;
	protected int angle = 0;

	protected boolean collided = false;

	public boolean getLooped() {
		return looped;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
		calculateDXDY();
	}

	public double getVelocity() {
		return velocity;
	}

	public void setDirection(double direction) {
		this.direction = direction;
		if (this.direction > 360)
			this.direction -= 360;
		if (this.direction < 0)
			this.direction += 360;
		calculateDXDY();
	}

	public double getDirection() {
		return direction;
	}

	private void calculateDXDY() {
		double radians = Math.toRadians(direction - 90);
		dX = Math.cos(radians) * velocity;
		dY = Math.sin(radians) * velocity;
	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	public void move(float screenWidth, float screenHeight) {
		float minX = 0 - bitmap.getWidth() / 2;
		float maxX = screenWidth + bitmap.getWidth() / 2;
		float minY = 0 - bitmap.getHeight() / 2;
		float maxY = screenHeight + bitmap.getHeight() / 2;

		looped = false;
		x += dX;
		y += dY;
		if (x > maxX) {
			x = minX;
			looped = true;
		}
		if (x < minX) {
			x = maxX;
			looped = true;
		}
		if (y > maxY) {
			y = minY;
			looped = true;
		}
		if (y < minY) {
			y = maxY;
			looped = true;
		}

		angle += rotation;
		if (Math.abs(angle) >= 360)
			angle = 0;
	}

	public void addVelocity(double increment, int angle) {
		double radians = Math.toRadians(angle - 90);
		dX += Math.cos(radians) * increment;
		dY += Math.sin(radians) * increment;
		velocity = Math.sqrt(dX * dX + dY * dY);
		direction = Math.toDegrees(Math.atan2(dX, -dY));
	}

	public void draw(Canvas canvas, float x, float y, Paint paint) {
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		canvas.rotate(angle, x, y);
		canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2,
				y - bitmap.getHeight() / 2, paint);
		canvas.restore();
	}

}