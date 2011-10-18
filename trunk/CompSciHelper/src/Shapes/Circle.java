package Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle extends Shape {

	public Circle(int number) {
		setX(300);
		setY(300);
		setRadius(40);
		setNumb(number);
	}

	public Circle(int x, int y, int radius, int number) {
		setX(x);
		setY(y);
		setRadius(radius);
		this.setNumb(number);
	}

	public float getTop() {
		return getY() - getRadius();
	}

	public float getBottom() {
		return getY() + getRadius();
	}

	@Override
	public void Draw(Canvas canvas, Paint paint) {
		paint.setColor(super.getColor());
		canvas.drawCircle(getX(), getY(), (float) getRadius(), paint);
		paint.setTextSize(35);
		paint.setColor(Color.RED);
		canvas.drawText(Integer.toString(super.getNumb()), super.getX(),
				super.getY(), paint);
	}

	@Override
	public void setY(float y) {
		super.setY(y);
	}

}