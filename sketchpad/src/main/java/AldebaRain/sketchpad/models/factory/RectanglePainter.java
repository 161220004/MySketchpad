package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.models.product.RectangleWA;
import javafx.scene.shape.Rectangle;

/** 
 * 锚点RectangleWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class RectanglePainter implements IPainter {

	private Rectangle createRectangle(double w, double h, double x, double y) {
		Rectangle rect = new Rectangle();
		rect.setWidth(w);
		rect.setHeight(h);
		rect.setTranslateX(x);
		rect.setTranslateY(y);
		return rect;
	}
	
	@Override
	public void paint(double x, double y, double xLen, double yLen) {
		Rectangle rect = createRectangle(xLen, yLen, x, y);
		RectangleWA rectWA = new RectangleWA(rect);
		// 在当前画布的当前图层绘制
		PaneManager.getCurrentPane().getCurrentLayer().add(rectWA);
	}

}
