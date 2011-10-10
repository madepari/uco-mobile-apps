package Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle extends Shape {
	Line leftLine;
	Line rightLine;
	public Circle(int number){
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
	
	@Override
	public void addChild(Circle NewCircle){
		if(getNumb() > NewCircle.getNumb()){
			if(getLeft() == null){
				this.setLeft(NewCircle);
				NewCircle.setX(this.getX() - 90);
				NewCircle.setY(this.getY() + 150);
				
				this.leftLine = new Line(this.getX(), this.getBottom(), NewCircle.getX(), NewCircle.getTop());
			}
		}
	}

	public float getTop() {
		return getY() - getRadius();
	}

	public float getBottom() {
		return getY() + getRadius();
	}

	@Override
	public void Draw(Canvas canvas, Paint paint) {
		canvas.drawCircle(getX(), getY(), (float) getRadius(), paint);
		if(leftLine != null){
			leftLine.Draw(canvas, paint);
		}
		if(rightLine != null){
			rightLine.Draw(canvas, paint);
		}
		paint.setTextSize(35);
		paint.setColor(Color.RED);
		canvas.drawText(Integer.toString(super.getNumb()), super.getX(), super.getY(), paint);
	}
	
	@Override
	public void setY(float y){
		super.setY(y);
		if(leftLine != null){
			leftLine.setY(y);
		}
		if(rightLine != null){
			rightLine.setY(y);
		}
	}
}