package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.Default;
import AldebaRain.sketchpad.models.anchor.AnchorShapeSet;
import javafx.scene.shape.Circle;

/** 
 * 锚点圆形类(Circle With Anchors).<br> 
 * 是工厂模式的具体产品类
 * 
 * @see AShapeWA
 * @see ANodeWA
 */
public class CircleWA extends AShapeWA{

	/** 构造函数A - 当Circle形状已确认且无缩放时调用 */
	public CircleWA(Circle circle) {
		super(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
		this.type = NodeType.Circle;
	}

	/** 构造函数0 - 仅用于克隆 */
	public CircleWA(Circle circle, AnchorShapeSet circleAnchors) {
		super(circle);
		this.anchors = circleAnchors;
		this.anchors.hide();
		this.type = NodeType.Circle;
	}

	/** 构造函数B（危险） - 当Circle形状未知时调用 */
	@Deprecated
	public CircleWA(Circle circle, double r, double x, double y) {
		super(circle, 2 * r, 2 * r, x, y);
		type = NodeType.Circle;
	}

	@Override
	public String getDescription() {
		return new String("圆形");
	}

	/** 克隆一个Circle类型 */
	private Circle cloneCircle(Circle circle) {
		Circle newCircle = new Circle();
		newCircle.setTranslateX(circle.getTranslateX() + Default.pasteBiasX);
		newCircle.setTranslateY(circle.getTranslateY() + Default.pasteBiasY);
		newCircle.setRadius(circle.getRadius());
		newCircle.setScaleX(circle.getScaleX());
		newCircle.setScaleY(circle.getScaleY());
		newCircle.setStrokeWidth(circle.getStrokeWidth());
		newCircle.setFill(circle.getFill());
		newCircle.setStroke(circle.getStroke());
		return newCircle;
	}
	
	@Override
	public ANodeWA clone() {
		Circle circle = cloneCircle((Circle)node);
		double origin = 2 * circle.getRadius();
		double xLength = origin * circle.getScaleX();
		double yLength = origin * circle.getScaleY();
		AnchorShapeSet circleAnchors = new AnchorShapeSet(circle, origin, origin, xLength, yLength);
		CircleWA circleWA = new CircleWA(circle, circleAnchors);
		return circleWA;
	}

}
