package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.Default;
import AldebaRain.sketchpad.hierarchy.PaneManager;
import AldebaRain.sketchpad.models.product.*;
import javafx.scene.shape.Ellipse;

/** 
 * 锚点EllipseWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class EllipsePainter implements IPainter {

	private Ellipse createEllipse(double xR, double yR, double x, double y) {
		Ellipse ellipse = new Ellipse();
		ellipse.setRadiusX(xR);
		ellipse.setRadiusY(yR);
		ellipse.setTranslateX(x);
		ellipse.setTranslateY(y);
		return ellipse;
	}
	
	@Override
	public void paint(double x, double y, double xLen, double yLen) {
		Ellipse ellipse = createEllipse(xLen / 2, yLen / 2, x, y);
		EllipseWA ellipseWA = new EllipseWA(ellipse);
		// 在当前画布的当前图层绘制
		PaneManager.getInstance().getCurrentPane().add(ellipseWA);
		// 添加到历史记录
		App.frameController.getHistoryController().saveAsHistory("绘制椭圆");
	}

	@Override
	public void paint() {
    	this.paint(Default.shapeOX, Default.shapeOY, 2 * Default.circleRadius, 2 * Default.circleRadius);
	}

}
