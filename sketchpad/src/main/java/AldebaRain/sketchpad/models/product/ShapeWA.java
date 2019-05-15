package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/** 
 * 锚点Shape适配器，抽象类(Shape With Anchors).<br> 
 * 是适配器模式的适配器类
 * 
 * @see ANodeWA
 */
public abstract class ShapeWA extends ANodeWA {

	/** 构造函数A - 当Shape形状已确认时调用 */
	public ShapeWA(Shape shape, Pane pane, double xLength, double yLength) {
		this.node = shape;
		this.anchors = new AnchorShapeSet(shape, xLength, yLength);
		this.pane = pane;
		this.addtoPane(pane);
		this.addMouseEvent();
	}
	
	/** 构造函数B（危险） - 当Shape形状未知时调用 */
	@Deprecated
	public ShapeWA(Shape shape, Pane pane, double xLength, double yLength, double x, double y) {
		shape.setTranslateX(x);
		shape.setTranslateY(y);
		this.node = shape;
		this.anchors = new AnchorShapeSet(shape, xLength, yLength, x, y);
		this.pane = pane;
		this.addtoPane(pane);
		this.addMouseEvent();
	}

	@Override
	public double getTranslateX() {
		return node.getTranslateX();
	}

	@Override
	public double getTranslateY() {
		return node.getTranslateY();
	}

	@Override
	public double getLengthX() {
		return anchors.getLengthX();
	}

	@Override
	public double getLengthY() {
		return anchors.getLengthY();
	}

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

}
