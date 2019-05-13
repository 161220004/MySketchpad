package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.scene.AnchorSet;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/** 有锚点圆形(CircleWithAnchors).<br> 
 * 是工厂模式的具体产品类 */
public class CircleWA extends AShapeWA {
	
	/** 构造函数（默认位置） */
	public CircleWA(Shape shape, AnchorSet anchors, Pane pane) {
		this.shape = shape;
		this.anchors = anchors;
		this.pane = pane;
		init();
	}

	/** 构造函数（指定位置） */
	public CircleWA(Shape shape, AnchorSet anchors, Pane pane, double x, double y) {
		this.shape = shape;
		this.anchors = anchors;
		this.pane = pane;
		this.setTranslateX(x);
		this.setTranslateY(y);
		init();
	}
	
}
