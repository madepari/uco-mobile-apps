package Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line extends Shape{
	private float endX;
	private float endY;
	final float strokeWidth = 4;
	public Line(float startX, float startY, float endX, float endY){
		super.setX(startX);
		super.setY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
	}
	
	@Override
	public void Draw(Canvas canvas, Paint paint) {
		paint.setColor(super.getColor());
		paint.setStrokeWidth(strokeWidth);
		canvas.drawLine(super.getX(), super.getY(), getEndX(), getEndY(), paint);	
		paint.setStrokeWidth(2);
	}
	
	public float getEndY() {
		return endY;
	}

	public void setEndY(float endY) {
		this.endY = endY;
	}

	public float getEndX() {
		return endX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
	}
	public void moveLine(float moveX, float moveY){
		super.setX(super.getX() + moveX);
		super.setY(super.getY() + moveY);
		setEndX(getEndX() + moveX);
		setEndY(getEndY() + moveY);
	}
}