package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/** 
 * 锚点Shape适配器，抽象类(Shape With Anchors).<br> 
 * 是适配器模式的适配器类
 * 
 * @see ANodeWA
 */
public abstract class AShapeWA extends ANodeWA {

	/** 构造函数A - 当Shape形状已确认时调用 */
	public AShapeWA(Shape shape, double xLength, double yLength) {
		this.node = shape;
		this.anchors = new AnchorShapeSet(shape, xLength, yLength);
		this.anchors.hide();
		this.addMouseEvent();
	}
	
	/** 构造函数B（危险） - 当Shape形状未知时调用 */
	@Deprecated
	public AShapeWA(Shape shape, double xLength, double yLength, double x, double y) {
		shape.setTranslateX(x);
		shape.setTranslateY(y);
		this.node = shape;
		this.anchors = new AnchorShapeSet(shape, xLength, yLength, x, y);
		this.anchors.hide();
		this.addMouseEvent();
	}

	@Override
	public double getLengthX() {
		return anchors.getLengthX();
	}

	@Override
	public void setLengthX(double xLen) {
		anchors.setLengthX(xLen);
	}
	
	@Override
	public double getLengthY() {
		return anchors.getLengthY();
	}

	@Override
	public void setLengthY(double yLen) {
		anchors.setLengthY(yLen);
	}
	
	@Override
	public double getStrokeWidth() {
		return ((Shape)node).getStrokeWidth();
	}
	
	@Override
	public void setStrokeWidth(double width) {
		((Shape)node).setStrokeWidth(width);
	}
	
	@Override
	public Color getFill() {
		return (Color)((Shape)node).getFill();
	}

	@Override
	public void setFill(Color color) {
		((Shape)node).setFill(color);
	}
	
	@Override
	public Color getStroke() {
		return (Color)((Shape)node).getStroke();
	}

	@Override
	public void setStroke(Color color) {
		((Shape)node).setStroke(color);
	}

}
