package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.models.product.*;
import AldebaRain.sketchpad.prefabs.Default;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/** 
 * 锚点LineWA绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class LinePainter implements IPainter {

	private Line setNodeValue(Node node, double xStart, double yStart, double xEnd, double yEnd) {
		Line line = (Line)node;
		line.setTranslateX((xStart + xEnd) / 2);
		line.setTranslateY((yStart + yEnd) / 2);
		line.setStartX(xStart);
		line.setStartY(yStart);
		line.setEndX(xEnd);
		line.setEndY(yEnd);
		return line;
	}
	
	@Override
	public ANodeWA paint(Node node, Pane pane) {
		// 确认Line形状为默认值
		Line line = setNodeValue(node, Default.lineStartX, Default.lineStartY , Default.lineEndX, Default.lineEndY);
		// 调用构造函数A型
		return new LineWA(line, pane);
	}

	/** 不提供默认位置指定大小的LineWA */
	@Override
	public ANodeWA paint(Node node, double xLen, double yLen, Pane pane) {
		return paint(node, pane);
	}

	/** 不提供默认大小指定位置的LineWA */
	@Override
	public ANodeWA paint(Node node, Pane pane, double x, double y) {
		return paint(node, pane);
	}

	@Override
	public ANodeWA paint(Node node, double xEnd, double yEnd, Pane pane, double xStart, double yStart) {
		// 确认Line形状为指定值（将x, y看作Start，xLen, yLen看作End）
		Line line = setNodeValue(node, xStart, yStart, xEnd, yEnd);
		// 调用构造函数A型
		return new LineWA(line, pane);
	}

}
