package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.models.product.ANodeWA;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/** 
 * 锚点图形绘制器.<br>
 * 是工厂模式的抽象工厂类
 */
public interface IPainter {

	/** 绘制ShapeWA（默认位置，默认大小） */
	public ANodeWA paint(Node node, Pane pane);

	/** 绘制ShapeWA（默认位置，指定大小） */
	public ANodeWA paint(Node node, double xLen, double yLen, Pane pane);

	/** 绘制ShapeWA（指定位置，默认大小） */
	public ANodeWA paint(Node node, Pane pane, double x, double y);

	/** 绘制ShapeWA（指定位置，指定大小） */
	public ANodeWA paint(Node node, double xLen, double yLen, Pane pane, double x, double y);
	
}
