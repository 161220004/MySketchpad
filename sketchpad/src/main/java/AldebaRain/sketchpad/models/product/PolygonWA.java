package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.AnchorPolygonSet;
import javafx.scene.shape.Polygon;

/** 
 * 锚点多边形类(Polygon With Anchors).<br> 
 * 是工厂模式的具体产品类
 * 
 * @see ANodeWA
 */
public class PolygonWA extends AShapeWA {

	/** 构造函数A - 当Polygon形状已确认时调用 */
	public PolygonWA(Polygon polygon, double x, double y) {
		this.node = polygon;
		this.anchors = new AnchorPolygonSet(polygon, x, y);
		this.anchors.hide();
		if (((AnchorPolygonSet)anchors).getVergeNum() == 3)
			this.type = NodeType.Triangle;
		else this.type = NodeType.Polygon;
		this.addMouseEvent();
	}

	@Override
	public String getDescription() {
		if (((AnchorPolygonSet)anchors).getVergeNum() == 3)
			return new String("三角形");
		else return new String("多边形 (v = " + ((AnchorPolygonSet)anchors).getVergeNum() + ")");
	}

	/** 获取外接圆半径 */
	public double getRadius() {
		return ((AnchorPolygonSet)anchors).getRadius();
	}

	/** 重设外接圆半径 */
	public void setRadius(double r) {
		((AnchorPolygonSet)anchors).setRadius(r);
	}
	
	private Polygon clonePolygon() {
		Polygon poly = (Polygon)node;
		Polygon newPoly = new Polygon();
		for (Double point: poly.getPoints()) {
			newPoly.getPoints().add(point);
		}
		newPoly.setTranslateX(poly.getTranslateX());
		newPoly.setTranslateY(poly.getTranslateY());
		newPoly.setStrokeWidth(poly.getStrokeWidth());
		newPoly.setFill(poly.getFill());
		newPoly.setStroke(poly.getStroke());
		return newPoly;
	}
	
	@Override
	public ANodeWA clone() {
		Polygon polygon = clonePolygon();
		PolygonWA polygonWA = new PolygonWA(polygon, anchors.getTranslateX(), anchors.getTranslateY());
		return polygonWA;
	}

}
