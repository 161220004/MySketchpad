package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.Default;
import AldebaRain.sketchpad.hierarchy.PaneManager;
import AldebaRain.sketchpad.models.product.PolygonWA;
import javafx.scene.shape.Polygon;

/** 
 * PolygonWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class PolygonPainter implements IPainter {

	@Override
	public void paint(double x, double y, double vergeNumTmp, double r) {
		int vergeNum = (new Double(vergeNumTmp)).intValue();
		Polygon poly = new Polygon();
		poly.setTranslateX(x);
		if (vergeNum == 3)
			poly.setTranslateY(y + r * Default.triangleBiasY);
		else poly.setTranslateY(y);
		for(int i = 0; i < vergeNum; i++) {
			poly.getPoints().add(x + r * Math.cos(Math.PI / 2 + i * 2 * Math.PI / vergeNum));
			poly.getPoints().add(y + r * Math.sin(Math.PI / 2 + i * 2 * Math.PI / vergeNum));
        }
		PolygonWA polygonWA = new PolygonWA(poly, x, y);
		// 在当前画布的当前图层绘制
		PaneManager.getInstance().getCurrentPane().add(polygonWA);
		// 添加到历史记录
		if (vergeNum == 3)
			App.frameController.getHistoryController().saveAsHistory("绘制正三角形");
		else
			App.frameController.getHistoryController().saveAsHistory("绘制正多边形");
	}

	@Override
	public void paint() {
    	this.paint(Default.shapeOX, Default.shapeOY, Default.polygonVergeNum, Default.polygonRadius);
	}

}
