package Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square extends Shape {
	private static final float xINC = 100;
	private static final float yINC = 70;

	@Override
	public void Draw(Canvas canvas, Paint paint) {
		paint.setColor(super.getColor());
		canvas.drawRect(x, getY(), x + xINC, getY() + yINC, paint);
		paint.setColor(Color.RED);
		paint.setTextSize(35);
		canvas.drawText(getNumb() + " ", getCenterX(), getCenterY(), paint);
	}

	public Square(int xloc, int yloc, int n) {
		x = xloc;
		setY(yloc);
		setNumb(n);
	}
	public Square(int xloc, int yloc, int n, int color) {
		x = xloc;
		setY(yloc);
		setNumb(n);
		super.setColor(color);
	}

	public Square(Shape s) {
		x = s.x;
		setY(s.getY());
		setNumb(s.getNumb());
		super.setColor(s.getColor());
	}

	public Square(Shape prev, int n) {
		x = prev.x;
		setY(prev.getY() + yINC + 3);
		setNumb(n);
	}

	public float getCenterY() {
		return getY() + (yINC / 2);
	}

	public float getCenterX() {
		return x;
	}
}