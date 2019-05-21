package AldebaRain.sketchpad.models.product;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/** 
 * 锚点Shape适配器，抽象类(Shape With Anchors).<br> 
 * 是适配器模式的适配器类
 * 
 * @see ANodeWA
 */
public abstract class AShapeWA extends ANodeWA {

	@Override
	public double getStrokeWidth() {
		return ((Shape)node).getStrokeWidth();
	}

	@Override
	public Color getFill() {
		return (Color)((Shape)node).getFill();
	}

	@Override
	public Color getStroke() {
		return (Color)((Shape)node).getStroke();
	}

	@Override
	public void setStrokeWidth(double width) {
		((Shape)node).setStrokeWidth(width);
	}
	
	@Override
	public void setFill(Color color) {
		((Shape)node).setFill(color);
	}
	
	@Override
	public void setStroke(Color color) {
		((Shape)node).setStroke(color);
	}

	public double getRotate() {
		return node.getRotate();
	}
	
}
