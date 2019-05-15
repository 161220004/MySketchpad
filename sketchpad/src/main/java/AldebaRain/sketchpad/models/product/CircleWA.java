package AldebaRain.sketchpad.models.product;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/** 
 * 锚点圆形类(Circle With Anchors).<br> 
 * 是工厂模式的具体产品类
 * 
 * @see ShapeWA
 * @see ANodeWA
 */
public class CircleWA extends ShapeWA{

	/** 构造函数A - 当Circle形状已确认时调用 */
	public CircleWA(Circle circle, Pane pane) {
		super(circle, pane, 2 * circle.getRadius(), 2 * circle.getRadius());
	}

	/** 构造函数B（危险） - 当Circle形状未知时调用 */
	@Deprecated
	public CircleWA(Circle circle, Pane pane, double r, double x, double y) {
		super(circle, pane, 2 * r, 2 * r, x, y);
	}

	@Override
	public String getDescription() {
		return new String("圆形");
	}

}
