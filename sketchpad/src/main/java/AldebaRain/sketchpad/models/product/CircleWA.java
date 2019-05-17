package AldebaRain.sketchpad.models.product;

import javafx.scene.shape.Circle;

/** 
 * 锚点圆形类(Circle With Anchors).<br> 
 * 是工厂模式的具体产品类
 * 
 * @see AShapeWA
 * @see ANodeWA
 */
public class CircleWA extends AShapeWA{

	/** 构造函数A - 当Circle形状已确认时调用 */
	public CircleWA(Circle circle) {
		super(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
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

}
