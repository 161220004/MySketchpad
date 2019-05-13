package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.models.product.CircleWA;
import AldebaRain.sketchpad.models.product.IShapeWA;
import AldebaRain.sketchpad.models.scene.AnchorSet;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/** 有锚点圆形绘制器.<br>
 * 是工厂模式的具体工厂类 */
public class CircleWAPainter implements IShapeWAPainter {

	@Override
	public IShapeWA paint(Shape shape, Pane pane) {
		Circle circle = (Circle)shape;
		AnchorSet anchors = new AnchorSet(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
		return new CircleWA(shape, anchors, pane);
	}

	@Override
	public IShapeWA paint(Shape shape, Pane pane, double x, double y) {
		Circle circle = (Circle)shape;
		AnchorSet anchors = new AnchorSet(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
		return new CircleWA(circle, anchors, pane, x, y);
	}

}
