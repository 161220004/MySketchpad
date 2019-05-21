package AldebaRain.sketchpad.models.anchor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class AnchorPolygonSet extends AnchorSet {

	/** 多边形边数 */
	private final int vergeNum;

	/** 多边形中心 */
	private double xCenter, yCenter;
	
	/** 多边形外接圆半径 */
	private double r;
	
	/** 构造函数 */
	public AnchorPolygonSet(Polygon node, double x, double y) {
		parentNode = node;
		anchors = new ArrayList<>();
		addAnchors(node);
		xCenter = x;
		yCenter = y;
		this.vergeNum = node.getPoints().size() / 2;
		double randPx = anchors.get(0).getTranslateX();
		double randPy = anchors.get(0).getTranslateY();
		r = Math.sqrt(Math.pow(randPx - x, 2) + Math.pow(randPy - y, 2));
		addMouseEvent();
	}
	
	/** 多边形每个顶点是一个锚点 */
	private void addAnchors(Polygon polygon) {
		List<Double> points = polygon.getPoints();
		int xyNum = points.size(); // = 2 * vNum
		for (int i = 0; i < xyNum; i += 2) {
			double x = points.get(i);
			double y = points.get(i + 1);
			anchors.add(new Anchor(x, y, i / 2));
		}
	}
	
	@Override
	protected void followMouseDrag(MouseEvent e, Anchor anchor) {
		// 拖拽的锚点原先和中心点的x,y方向距离
		double oxPre = anchor.getOriginX() - xCenter;
		double oyPre = anchor.getOriginY() - yCenter;
    	// 光标位移（若拖拽边锚点忽略其他方向的光标位移）
    	double dx = e.getSceneX() - oxMouse;
        double dy = e.getSceneY() - oyMouse;
		// 当前拖拽位置和中心点的x,y方向距离
        double ox = oxPre + dx;
        double oy = oyPre + dy;
        r = Math.sqrt(ox * ox + oy * oy);
		// 多边形变换
		List<Double> points = ((Polygon)parentNode).getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double xDrag = xCenter + r * Math.cos(Math.PI / 2 + i * Math.PI / vergeNum);
			double yDrag = yCenter + r * Math.sin(Math.PI/2 + i * Math.PI / vergeNum);
			points.set(i, xDrag);
			points.set(i + 1, yDrag);
			this.getAnchor(i / 2).setTranslateX(xDrag);
			this.getAnchor(i / 2).setTranslateY(yDrag);
		}
	}

	/** 获取边数 */
	public int getVergeNum() {
		return vergeNum;
	}

	@Override
	public double getTranslateX() {
		return xCenter;
	}
	
	@Override
	public double getTranslateY() {
		return yCenter;
	}
	
	/** 获取外接圆半径 */
	public double getRadius() {
		return r;
	}
	
	@Override
	public void setTranslateX(double x) {
		double dx = x - xCenter;
		xCenter = x;
		parentNode.setTranslateX(x);
		List<Double> points = ((Polygon)parentNode).getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double px = points.get(i) + dx;
			// 处理图形
			points.set(i, px);
			// 处理锚点
			this.getAnchor(i / 2).setTranslateX(px);
		}
		this.setOriginPositions();
	}
	
	@Override
	public void setTranslateY(double y) {
		double dy = y - yCenter;
		yCenter = y;
		parentNode.setTranslateY(y);
		List<Double> points = ((Polygon)parentNode).getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double py = points.get(i + 1) + dy;
			// 处理图形
			points.set(i + 1, py);
			// 处理锚点
			this.getAnchor(i / 2).setTranslateY(py);
		}
		this.setOriginPositions();
	}
	
	/** 重设多边形外接圆半径 */
	public void setRadius(double newR) {
		r = newR;
		List<Double> points = ((Polygon)parentNode).getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double newX = xCenter + newR * Math.cos(Math.PI / 2 + i * Math.PI / vergeNum);
			double newY = yCenter + newR * Math.sin(Math.PI/2 + i * Math.PI / vergeNum);
			points.set(i, newX);
			points.set(i + 1, newY);
			this.getAnchor(i / 2).setTranslateX(newX);
			this.getAnchor(i / 2).setTranslateY(newY);
		}
	}
	
}
