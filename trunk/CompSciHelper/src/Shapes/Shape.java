package Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape{
	private int c = Color.WHITE;
	private float x;
	private float y;
	private int numb;
	private Shape left;
	private Shape right;
	private int radius;
	
	public abstract void Draw(Canvas canvas, Paint paint);
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getColor() {
		return c;
	}
	public void setColor(int c) {
		this.c = c;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public Shape getLeft() {
		return left;
	}
	public void setLeft(Shape prev) {
		this.left = prev;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Shape getRight() {
		return right;
	}
	public void setRight(Shape right) {
		this.right = right;
	}
	public void addChild(Circle c){}
}