package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.AnchorEllipseSet;
import javafx.scene.shape.Ellipse;

/** 
 * 锚点圆形类(Circle With Anchors).<br> 
 * 是适配器模式的适配器类；
 * 是工厂模式的具体产品类
 * 
 * @see ShapeWA
 * @see ANodeWA
 */
public class EllipseWA extends ShapeWA {

	/** 构造函数A - 当Ellipse形状已确认时调用 */
	public EllipseWA(Ellipse ellipse) {
		this.type = NodeType.Ellipse;
		this.node = ellipse;
		this.anchors = new AnchorEllipseSet(ellipse);
		this.anchors.hide();
		this.addMouseEvent();
	}

	@Override
	public String getDescription() {
		return new String("椭圆");
	}

	/** 克隆一个Circle类型 */
	private Ellipse cloneEllipse() {
		Ellipse ellipse = (Ellipse)node;
		Ellipse newEllipse = new Ellipse();
		newEllipse.setTranslateX(ellipse.getTranslateX());
		newEllipse.setTranslateY(ellipse.getTranslateY());
		newEllipse.setRadiusX(ellipse.getRadiusX());
		newEllipse.setRadiusY(ellipse.getRadiusY());
		newEllipse.setStrokeWidth(ellipse.getStrokeWidth());
		newEllipse.setFill(ellipse.getFill());
		newEllipse.setStroke(ellipse.getStroke());
		return newEllipse;
	}
	
	@Override
	public ANodeWA clone() {
		Ellipse ellipse = cloneEllipse();
		EllipseWA ellipseWA = new EllipseWA(ellipse);
		return ellipseWA;
	}

}
