package Trees;

import android.graphics.Canvas;
import android.graphics.Paint;
import Shapes.*;

public class Node {
	private Circle node;
	private Node leftChild, rightChild = null;
	private Line leftLine, rightLine = null;

	public Node(Circle c) {
		node = c;
	}

	public Node(int x, int y, int radius, int number) {
		node.setX(x);
		node.setY(y);
		node.setRadius(radius);
		node.setNumb(number);
	}

	public float getX() {
		return node.getX();
	}

	public void setX(float x) {
		node.setX(x);
	}

	public float getY() {
		return node.getY();
	}

	public void setY(float y) {
		node.setY(y);
	}

	public int getNumber() {
		return node.getNumb();
	}

	public Circle getNode() {
		return node;
	}

	public void setNode(Circle node) {
		this.node = node;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Line getLeftLine() {
		return leftLine;
	}

	public void setLeftLine(Line leftLine) {
		this.leftLine = leftLine;
	}

	public Line getRightLine() {
		return rightLine;
	}

	public void setRightLine(Line rightLine) {
		this.rightLine = rightLine;
	}

	public void Draw(Canvas c, Paint p) {
		node.Draw(c, p);
		if (leftLine != null)
			leftLine.Draw(c, p);
		if (rightLine != null)
			rightLine.Draw(c, p);
	}

	public float getBottom() {
		return node.getBottom();
	}

	public float getTop() {
		return node.getTop();
	}
}
