package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.AnchorRectangleSet;
import javafx.scene.shape.Rectangle;

/** 
 * 锚点矩形类(Rectangle With Anchors).<br> 
 * 是适配器模式的适配器类；
 * 是工厂模式的具体产品类
 * 
 * @see ShapeWA
 * @see ANodeWA
 */
public class RectangleWA extends ShapeWA {

	/** 构造函数A - 当Rectangle形状已确认时调用 */
	public RectangleWA(Rectangle rect) {
		this.type = NodeType.Rectangle;
		this.node = rect;
		this.anchors = new AnchorRectangleSet(rect);
		this.anchors.hide();
		this.addMouseEvent();
	}

	protected RectangleWA() {}
	
	@Override
	public String getDescription() {
		return new String("矩形");
	}

	/** 克隆一个Rectangle类型 */
	protected Rectangle cloneRectangle() {
		Rectangle rect = (Rectangle)node;
		Rectangle newRect = new Rectangle();
		newRect.setTranslateX(rect.getTranslateX());
		newRect.setTranslateY(rect.getTranslateY());
		newRect.setWidth(rect.getWidth());
		newRect.setHeight(rect.getHeight());
		newRect.setStrokeWidth(rect.getStrokeWidth());
		newRect.setFill(rect.getFill());
		newRect.setStroke(rect.getStroke());
		return newRect;
	}
	
	@Override
	public ANodeWA clone() {
		Rectangle rect = cloneRectangle();
		RectangleWA rectWA = new RectangleWA(rect);
		return rectWA;
	}

}
