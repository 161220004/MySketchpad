package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.models.product.IShapeWA;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/** 有锚点图形绘制器.<br>
 * 是工厂模式的抽象工厂类 */
public interface IShapeWAPainter {

	/** 绘制ShapeWA（默认位置） */
	public IShapeWA paint(Shape shape, Pane pane);

	/** 绘制ShapeWA（指定位置） */
	public IShapeWA paint(Shape shape, Pane pane, double x, double y);
	
}
