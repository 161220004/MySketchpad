package AldebaRain.sketchpad.models.anchor;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

/**
 * LineWA中的锚点(3个)，附着于Line类型.<br>
 * 
 * @see AnchorSet
 */
public class AnchorLineSet extends AnchorSet {

	/** 锚点所在直线 */
	private Line parentLine;	
	
	/** 锚点所在直线 - 拖拽前Start */
	private double xStartBefore, yStartBefore;
	
	/** 锚点所在直线 - 拖拽时Start */
	private double xStartDrag, yStartDrag;
	
	/** 锚点所在直线 - 拖拽前End */
	private double xEndBefore, yEndBefore;
	
	/** 锚点所在直线 - 拖拽时End */
	private double xEndDrag, yEndDrag;

	/** 构造函数A - 当Line形状已确认时调用 */
	public AnchorLineSet(Line node) {
		parentNode = node;
		parentLine = (Line)parentNode;
		xStartBefore = node.getStartX();
		yStartBefore = node.getStartY();
		xEndBefore = node.getEndX();
		yEndBefore = node.getEndY();
		anchors = new ArrayList<>();
		addAnchors(parentNode, (xEndBefore - xStartBefore), (yEndBefore - yStartBefore));
		addMouseEvent();
	}

	/** 构造函数B（危险） - 当Line形状未知时调用 */
	@Deprecated
	public AnchorLineSet(Line node, double xStart, double yStart, double xEnd, double yEnd) {
		parentNode = node;
		parentNode.setTranslateX((xStart + xEnd) / 2);
		parentNode.setTranslateY((yStart + yEnd) / 2);
		parentLine = (Line)parentNode;
		xStartBefore = xStart;
		yStartBefore = yStart;
		xEndBefore = xEnd;
		yEndBefore = yEnd;
		anchors = new ArrayList<>();
		addAnchors(parentNode, (xEnd - xStart), (yEnd - yStart));
		addMouseEvent();
	}

	/** 构造函数初始化 - 添加锚点 */
	protected void addAnchors(Node node, double xLength, double yLength) {
		anchors.add(new Anchor(AnchorID.C, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.LU, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.RD, node, xLength, yLength));
	}

	/** 重写Line的锚点拖拽事件- 拖拽前初始化 */
	@Override
	protected void initBeforeDrag(MouseEvent e) {
		super.initBeforeDrag(e);
    	xStartDrag = xStartBefore;
    	yStartDrag = yStartBefore;
    	xEndDrag = xEndBefore;
    	yEndDrag = yEndBefore;
	}
	
	/** 重写Line的锚点拖拽事件 - 正在拖拽 */
	@Override
	protected void followMouseDrag(MouseEvent e, Anchor anchor) {
		AnchorID movId = anchor.getAnchorId();
    	if (movId == AnchorID.C) return;
    	// 光标位移
    	double dx = e.getSceneX() - oxMouse;
        double dy = e.getSceneY() - oyMouse;
        // 各锚点位移tx, ty
		for (Anchor other: anchors) {
			double tx = dx, ty = dy;
			if (movId.isOpposite(other.getAnchorId())) {
				tx = 0;
				ty = 0;
			} else if (other.getAnchorId() == AnchorID.C) {
				tx /= 2;
				ty /= 2;
			}
			other.setTranslateX(tx + other.getOriginX());
			other.setTranslateY(ty + other.getOriginY());
		}
		// 图形位移和变换
        parentNode.setTranslateX(dx / 2 + oxParent);
        parentNode.setTranslateY(dy / 2 + oyParent);
		if (movId == AnchorID.LU) {
			xStartDrag = dx + xStartBefore;
			yStartDrag = dy + yStartBefore;
			parentLine.setStartX(xStartDrag);
			parentLine.setStartY(yStartDrag);
		} else { 
			xEndDrag = dx + xEndBefore;
			yEndDrag = dy + yEndBefore;
			parentLine.setEndX(xEndDrag);
			parentLine.setEndY(yEndDrag);
		}
	}

	/** 重写Line的锚点拖拽事件 - 结束拖拽 */
	@Override
	protected void exitMouseDrag(Anchor anchor) {
		super.exitMouseDrag(anchor);
    	xStartBefore = xStartDrag;
    	yStartBefore = yStartDrag;
    	xEndBefore = xEndDrag;
    	yEndBefore = yEndDrag;
	}

	@Override
	public void setTranslateX(double x) {
		super.setTranslateX(x);
		// 处理图形
		parentLine.setStartX(this.getAnchor(AnchorID.LU).getTranslateX());
		parentLine.setEndX(this.getAnchor(AnchorID.RD).getTranslateX());
		// 重新初始化拖拽前变量
		xStartBefore = parentLine.getStartX();
		xEndBefore = parentLine.getEndX();
	}
	
	@Override
	public void setTranslateY(double y) {
		super.setTranslateY(y);
		// 处理图形
		parentLine.setStartY(this.getAnchor(AnchorID.LU).getTranslateY());
		parentLine.setEndY(this.getAnchor(AnchorID.RD).getTranslateY());
		// 重新初始化拖拽前变量
		yStartBefore = parentLine.getStartY();
		yEndBefore = parentLine.getEndY();
	}
	
	@Override
	public void setLengthX(double xLen) {
		super.setLengthX(xLen);
		// 处理图形
		parentLine.setStartX(this.getAnchor(AnchorID.LU).getTranslateX());
		parentLine.setEndX(this.getAnchor(AnchorID.RD).getTranslateX());
		// 重新初始化拖拽前变量
		xStartBefore = parentLine.getStartX();
		xEndBefore = parentLine.getEndX();
	}
	
	@Override
	public void setLengthY(double yLen) {
		super.setLengthY(yLen);
		// 处理图形
		parentLine.setStartY(this.getAnchor(AnchorID.LU).getTranslateY());
		parentLine.setEndY(this.getAnchor(AnchorID.RD).getTranslateY());
		// 重新初始化拖拽前变量
		yStartBefore = parentLine.getStartY();
		yEndBefore = parentLine.getEndY();
	}
	
	/** 重设锚点集Start端X坐标 */
	public void setStartX(double xStart) {
		// 处理锚点集
		double xEnd = this.getAnchor(AnchorID.RD).getTranslateX();
		this.getAnchor(AnchorID.LU).setTranslateX(xStart);
		this.getAnchor(AnchorID.C).setTranslateX((xStart + xEnd) / 2);
		this.setOriginPositions();
		// 处理图形
		parentLine.setTranslateX((xStart + xEnd) / 2);
		parentLine.setStartX(xStart);
		// 重新初始化拖拽前变量
		xStartBefore = parentLine.getStartX();
		xEndBefore = parentLine.getEndX();
	}

	/** 重设锚点集Start端Y坐标 */
	public void setStartY(double yStart) {
		// 处理锚点集
		double yEnd = this.getAnchor(AnchorID.RD).getTranslateY();
		this.getAnchor(AnchorID.LU).setTranslateY(yStart);
		this.getAnchor(AnchorID.C).setTranslateY((yStart + yEnd) / 2);
		this.setOriginPositions();
		// 处理图形
		parentLine.setTranslateY((yStart + yEnd) / 2);
		parentLine.setStartY(yStart);
		// 重新初始化拖拽前变量
		yStartBefore = parentLine.getStartY();
		yEndBefore = parentLine.getEndY();
	}

	/** 重设锚点集End端X坐标 */
	public void setEndX(double xEnd) {
		// 处理锚点集
		double xStart = this.getAnchor(AnchorID.LU).getTranslateX();
		this.getAnchor(AnchorID.RD).setTranslateX(xEnd);
		this.getAnchor(AnchorID.C).setTranslateX((xStart + xEnd) / 2);
		this.setOriginPositions();
		// 处理图形
		parentLine.setTranslateX((xStart + xEnd) / 2);
		parentLine.setEndX(xEnd);
		// 重新初始化拖拽前变量
		xStartBefore = parentLine.getStartX();
		xEndBefore = parentLine.getEndX();
	}

	/** 重设锚点集End端Y坐标 */
	public void setEndY(double yEnd) {
		// 处理锚点集
		double yStart = this.getAnchor(AnchorID.LU).getTranslateY();
		this.getAnchor(AnchorID.RD).setTranslateY(yEnd);
		this.getAnchor(AnchorID.C).setTranslateY((yStart + yEnd) / 2);
		this.setOriginPositions();
		// 处理图形
		parentLine.setTranslateY((yStart + yEnd) / 2);
		parentLine.setEndY(yEnd);
		// 重新初始化拖拽前变量
		yStartBefore = parentLine.getStartY();
		yEndBefore = parentLine.getEndY();
	}
	
}
