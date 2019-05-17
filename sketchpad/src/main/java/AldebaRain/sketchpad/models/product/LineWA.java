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

	@Override
	public double getTranslateX() {
		return (((Line)node).getEndX() + ((Line)node).getStartX()) / 2;
	}

	@Override
	public double getTranslateY() {
		return (((Line)node).getEndY() + ((Line)node).getStartY()) / 2;
	}

	/** 获取直线左侧端点X坐标 */
	public double getStartX() {
		Anchor aLU = anchors.getAnchor(AnchorID.LU);
		Anchor aRD = anchors.getAnchor(AnchorID.RD);
		return ((aLU.getTranslateX() < aRD.getTranslateX()) ? aLU.getTranslateX() : aRD.getTranslateX());
	}

	/** 获取直线左侧端点Y坐标 */
	public double getStartY() {
		Anchor aLU = anchors.getAnchor(AnchorID.LU);
		Anchor aRD = anchors.getAnchor(AnchorID.RD);
		return ((aLU.getTranslateY() < aRD.getTranslateY()) ? aLU.getTranslateY() : aRD.getTranslateY());
	}

	/** 获取直线右侧端点X坐标 */
	public double getEndX() {
		Anchor aLU = anchors.getAnchor(AnchorID.LU);
		Anchor aRD = anchors.getAnchor(AnchorID.RD);
		return ((aLU.getTranslateX() > aRD.getTranslateX()) ? aLU.getTranslateX() : aRD.getTranslateX());
	}

	/** 获取直线右侧端点Y坐标 */
	public double getEndY() {
		Anchor aLU = anchors.getAnchor(AnchorID.LU);
		Anchor aRD = anchors.getAnchor(AnchorID.RD);
		return ((aLU.getTranslateY() > aRD.getTranslateY()) ? aLU.getTranslateY() : aRD.getTranslateY());
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

}
