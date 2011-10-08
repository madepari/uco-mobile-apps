package com.uco.compsci;

import android.graphics.Canvas;
import android.graphics.Paint;

class Line extends Shape{
	@Override
	void Draw(Canvas canvas, Paint paint) {
		canvas.drawLine(x, getY(), prev.x, prev.getY(), paint);		
	}

}