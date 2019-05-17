package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.models.product.*;
import javafx.scene.shape.Line;

/** 
 * 锚点LineWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class LinePainter implements IPainter {

	private Line createLine(double xStart, double yStart, double xEnd, double yEnd) {
		Line line = new Line();
		line.setTranslateX((xStart + xEnd) / 2);
		line.setTranslateY((yStart + yEnd) / 2);
		line.setStartX(xStart);
		line.setStartY(yStart);
		line.setEndX(xEnd);
		line.setEndY(yEnd);
		return line;
	}
	
	@Override
	public void paint(double xStart, double yStart, double xEnd, double yEnd) {
		Line line = createLine(xStart, yStart, xEnd, yEnd);
		LineWA lineWA = new LineWA(line);
		// 在当前画布的当前图层绘制
		PaneManager.getCurrentPane().getCurrentLayer().add(lineWA);
	}

}
