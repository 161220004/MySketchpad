package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.anchor.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** 
 * 锚点Line适配器(Line With Anchors).<br> 
 * 是适配器模式的适配器类
 * 
 * @see ANodeWA
 */
public class LineWA extends ANodeWA {

	/** 构造函数A - 当Line形状已确认时调用 */
	public LineWA(Line line) {
		this.type = NodeType.Line;
		this.node = line;
		this.anchors = new AnchorLineSet(line);
		this.anchors.hide();
		this.addMouseEvent();
	}

	/** 构造函数B（危险） - 当Line形状未知时调用 */
	@Deprecated
	public LineWA(Line line, double xStart, double yStart, double xEnd, double yEnd) {
		line.setTranslateX((xStart + xEnd) / 2);
		line.setTranslateY((yStart + yEnd) / 2);
		line.setStartX(xStart);
		line.setStartY(yStart);
		line.setEndX(xEnd);
		line.setEndY(yEnd);
		this.type = NodeType.Line;
		this.node = line;
		this.anchors = new AnchorLineSet(line, xStart, yStart, xEnd, yEnd);
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
	public double getLengthX() {
		return Math.abs(((Line)node).getEndX() - ((Line)node).getStartX());
	}

	@Override
	public double getLengthY() {
		return Math.abs(((Line)node).getEndY() - ((Line)node).getStartY());
	}

	@Override
	public double getStrokeWidth() {
		return ((Line)node).getStrokeWidth();
	}
	
	@Override
	public Color getFill() {
		return (Color)((Line)node).getFill();
	}

	@Override
	public Color getStroke() {
		return (Color)((Line)node).getStroke();
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
	public void setLengthX(double xLen) {
		anchors.setLengthX(xLen);
	}

	@Override
	public void setLengthY(double yLen) {
		anchors.setLengthY(yLen);
	}

	@Override
	public void setStrokeWidth(double width) {
		((Line)node).setStrokeWidth(width);
	}

	@Override
	public void setFill(Color color) {
		((Line)node).setFill(color);
	}

	@Override
	public void setStroke(Color color) {
		((Line)node).setStroke(color);
	}

	@Override
	public ANodeWA clone() {
		LineWA lineWA = new LineWA((Line)node);
		// 少量偏移
		
		return lineWA;
	}

}
