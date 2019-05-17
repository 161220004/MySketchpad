package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.models.product.*;
import javafx.scene.shape.Circle;

/** 
 * 锚点CircleWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class CirclePainter implements IPainter {

	private Circle createCircle(double r, double x, double y) {
		Circle circle = new Circle();
		circle.setRadius(r);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
		return circle;
	}
	
	@Override
	public void paint(double x, double y, double xLen, double yLen) {
		Circle circle = createCircle(xLen / 2, x, y);
		CircleWA circleWA = new CircleWA(circle);
		// 在当前画布的当前图层绘制
		PaneManager.getCurrentPane().getCurrentLayer().add(circleWA);
	}

}
