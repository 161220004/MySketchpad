package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.models.product.*;
import AldebaRain.sketchpad.prefabs.Default;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/** 
 * 锚点CircleWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class CirclePainter implements IPainter {

	private Circle setNodeValue(Node node, double r, double x, double y) {
		Circle circle = (Circle)node;
		circle.setRadius(r);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
		return circle;
	}
	
	@Override
	public ANodeWA paint(Node node, Pane pane) {
		// 确认Circle形状为默认位置默认大小
		Circle circle = setNodeValue(node, Default.circleRadius, Default.circleOX, Default.circleOY);
		// 调用构造函数A型
		return new CircleWA(circle, pane);
	}

	@Override
	public ANodeWA paint(Node node, double xLen, double yLen, Pane pane) {
		// 确认Circle形状为默认位置指定大小
		Circle circle = setNodeValue(node, xLen / 2, Default.circleOX, Default.circleOY);
		// 调用构造函数A型
		return new CircleWA(circle, pane);
	}

	@Override
	public ANodeWA paint(Node node, Pane pane, double x, double y) {
		// 确认Circle形状为指定位置默认大小
		Circle circle = setNodeValue(node, Default.circleRadius, x, y);
		// 调用构造函数A型
		return new CircleWA(circle, pane);
	}

	@Override
	public ANodeWA paint(Node node, double xLen, double yLen, Pane pane, double x, double y) {
		// 确认Circle形状为指定位置指定大小
		Circle circle = setNodeValue(node, xLen / 2, x, y);
		// 调用构造函数A型
		return new CircleWA(circle, pane);
	}

}
