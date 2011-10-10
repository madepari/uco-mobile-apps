package Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

class Line extends Shape{
	private float endX;
	private float endY;
	
	public Line(float startX, float startY, float endX, float endY){
		super.setX(startX);
		super.setY(startY);
		this.endX = endX;
		this.endY = endY;
	}
	
	@Override
	public void Draw(Canvas canvas, Paint paint) {
		canvas.drawLine(getX(), getY(), super.getLeft().getX(), getLeft().getY(), paint);		
	}
	
	@Override
	public void setY(float y){
		super.setY(y);
		endY = y;
	}
}