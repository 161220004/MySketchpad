package AldebaRain.sketchpad.models.scene;

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
		addAnchors(parentNode, (xEnd - xStart), (yEnd - yStart));
		addMouseEvent();
	}

	/** 构造函数初始化 - 添加锚点 */
	@Override
	protected void addAnchors(Node node, double xLength, double yLength) {
		anchors = new ArrayList<>();
		anchors.add(new Anchor(AnchorID.C, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.LU, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.RD, node, xLength, yLength));
	}

	/** 重写Line的锚点拖拽事件- 拖拽前初始化 */
	@Override
	protected void initBeforeDrag(MouseEvent e) {
    	oxMouse = e.getSceneX();
    	oyMouse = e.getSceneY();
    	oxParent = parentLine.getTranslateX();
    	oyParent = parentLine.getTranslateY();
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
    	setOriginPositions();
    	xStartBefore = xStartDrag;
    	yStartBefore = yStartDrag;
    	xEndBefore = xEndDrag;
    	yEndBefore = yEndDrag;
	}
	
}
