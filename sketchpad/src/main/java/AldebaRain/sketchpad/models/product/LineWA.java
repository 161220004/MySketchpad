package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** 
 * 锚点Line适配器(Line With Anchors).<br> 
 * 是适配器模式的适配器类
 * 
 * @see ShapeWA
 * @see ANodeWA
 */
public class LineWA extends ShapeWA {

	/** 构造函数A - 当Line形状已确认时调用 */
	public LineWA(Line line) {
		this.type = NodeType.Line;
		this.node = line;
		this.anchors = new AnchorLineSet(line);
		this.anchors.hide();
		this.addMouseEvent();
	}

	@Override
	public String getDescription() {
		return new String("直线");
	}

	/** 获取Start端点，即直线初始化时LU锚点的X坐标 */
	public double getStartX() {
		return anchors.getAnchor(AnchorID.LU).getTranslateX();
	}

	/** 获取Start端点，即直线初始化时LU锚点的Y坐标 */
	public double getStartY() {
		return anchors.getAnchor(AnchorID.LU).getTranslateY();
	}

	/** 获取End端点，即直线初始化时RD锚点的Y坐标 */
	public double getEndY() {
		return anchors.getAnchor(AnchorID.RD).getTranslateY();
	}

	/** 获取End端点，即直线初始化时RD锚点的X坐标 */
	public double getEndX() {
		return anchors.getAnchor(AnchorID.RD).getTranslateX();
	}

	@Override
	public double getStrokeWidth() {
		return node.getStrokeWidth();
	}
	
	@Override
	public Color getFill() {
		return (Color)node.getFill();
	}

	@Override
	public Color getStroke() {
		return (Color)node.getStroke();
	}

	/** 重设Start端点，即直线初始化时LU锚点的X坐标 */
	public void setStartX(double xStart) {
		((AnchorLineSet)anchors).setStartX(xStart);
	}

	/** 重设End端点，即直线初始化时RD锚点的X坐标 */
	public void setEndX(double xEnd) {
		((AnchorLineSet)anchors).setEndX(xEnd);
	}

	/** 重设Start端点，即直线初始化时LU锚点的Y坐标 */
	public void setStartY(double yStart) {
		((AnchorLineSet)anchors).setStartY(yStart);
	}

	/** 重设End端点，即直线初始化时RD锚点的Y坐标 */
	public void setEndY(double yEnd) {
		((AnchorLineSet)anchors).setEndY(yEnd);
	}

	@Override
	public void setStrokeWidth(double width) {
		node.setStrokeWidth(width);
	}

	@Override
	public void setFill(Color color) {
		node.setFill(color);
	}

	@Override
	public void setStroke(Color color) {
		node.setStroke(color);
	}

	/** 克隆一个Line类型 */
	private Line cloneLine() {
		Line newLine = new Line();
		// 少量偏移
		newLine.setStartX(((Line)node).getStartX());
		newLine.setStartY(((Line)node).getStartY());
		newLine.setEndX(((Line)node).getEndX());
		newLine.setEndY(((Line)node).getEndY());
		newLine.setTranslateX(node.getTranslateX());
		newLine.setTranslateY(node.getTranslateY());
		newLine.setStrokeWidth(node.getStrokeWidth());
		newLine.setStroke(node.getStroke());
		return newLine;
	}
	
	@Override
	public ANodeWA clone() {
		Line line = cloneLine();
		LineWA lineWA = new LineWA(line);
		return lineWA;
	}

}
