package AldebaRain.sketchpad.models.anchor;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 * ShapeWA中的锚点(9个)，附着于Shape类型.<br>
 * 
 * @see AnchorSet
 */
public class AnchorShapeSet extends AnchorSet {

	/** 锚点所在图形 */
	private Shape parentShape;
	/** 锚点所在图形 - 最初大小 */
	private double xLengthOrigin, yLengthOrigin;
	/** 锚点所在图形 - 拖拽前大小 */
	private double xLengthBefore, yLengthBefore;
	/** 锚点所在图形 - 拖拽时大小 */
	private double xLengthDrag, yLengthDrag;

	/** 构造函数A - 当Shape形状已确认时调用 */
	public AnchorShapeSet(Shape node, double xLength, double yLength) {
		parentNode = node;
		parentShape = (Shape)parentNode;
		xLengthOrigin = xLength;
		yLengthOrigin = yLength;
		xLengthBefore = xLength;
		yLengthBefore = yLength;
		addAnchors(node, xLength, yLength);
		addMouseEvent();
	}

	/** 构造函数B（危险） - 当Shape形状未知时调用 */
	@Deprecated
	public AnchorShapeSet(Shape node, double xLength, double yLength, double x, double y) {
		parentNode = node;
		parentNode.setTranslateX(x);
		parentNode.setTranslateY(y);
		parentShape = (Shape)parentNode;
		xLengthOrigin = xLength;
		yLengthOrigin = yLength;
		xLengthBefore = xLength;
		yLengthBefore = yLength;
		addAnchors(node, xLength, yLength);
		addMouseEvent();
	}

	/** 构造函数初始化 - 添加锚点 */
	@Override
	protected void addAnchors(Node node, double xLength, double yLength) {
		anchors = new ArrayList<>();
		anchors.add(new Anchor(AnchorID.C, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.L, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.R, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.U, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.D, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.LU, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.LD, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.RU, node, xLength, yLength));
		anchors.add(new Anchor(AnchorID.RD, node, xLength, yLength));
	}

	/** 锚点拖拽事件- 拖拽前初始化 */
	@Override
	protected void initBeforeDrag(MouseEvent e) {
    	oxMouse = e.getSceneX();
    	oyMouse = e.getSceneY();
    	oxParent = parentShape.getTranslateX();
    	oyParent = parentShape.getTranslateY();
    	xLengthDrag = xLengthBefore;
    	yLengthDrag = yLengthBefore;
    }

	/** 锚点拖拽事件 - 正在拖拽 */
	@Override
	protected void followMouseDrag(MouseEvent e, Anchor anchor) {
		AnchorID movId = anchor.getAnchorId();
    	if (movId == AnchorID.C) return;
    	// 光标位移（若拖拽边锚点忽略其他方向的光标位移）
    	double dx = e.getSceneX() - oxMouse;
        double dy = e.getSceneY() - oyMouse;
        if (movId.isHorizontal()) dy = 0;
        if (movId.isVertical()) dx = 0;
        // 各锚点位移tx, ty
		for (Anchor other: anchors) {
			double tx = dx, ty = dy;
			AnchorID othId = other.getAnchorId();
			// 若拖拽边锚点
			if (movId.isEdge()) {
				if (movId.isHorizontal()) {
					ty = 0;
					if (movId.isOpposite(othId)) tx = 0;
					else if (!movId.isSide(othId)) tx /= 2;
				} else {
					tx = 0;
					if (movId.isOpposite(othId)) ty = 0;
					else if (!movId.isSide(othId)) ty /= 2;
				}
			}
			// 若拖拽角锚点
			else if (movId.isCorner() && !(movId == othId)) {
				if (movId.isOpposite(othId)) {
					tx = 0;
					ty = 0;
				} else if (movId.isOppositeHorizontal(othId)) {
					ty = 0;
					if (othId.isEdge()) tx /= 2;
				} else if (movId.isOppositeVertical(othId)) {
					tx = 0;
					if (othId.isEdge()) ty /= 2;
				} else if (movId.isSideHorizontal(othId)) {
					tx /= 2;
				} else if (movId.isSideVertical(othId)) {
					ty /= 2;
				} else {
					tx /= 2;
					ty /= 2;
				}
			}
			other.setTranslateX(tx + other.getOriginX());
			other.setTranslateY(ty + other.getOriginY());
		}
		// 图形位移和变换
        parentShape.setTranslateX(dx / 2 + oxParent);
        parentShape.setTranslateY(dy / 2 + oyParent);
        xLengthDrag = xLengthBefore + dx * movId.getXDir();
        yLengthDrag = yLengthBefore + dy * movId.getYDir();
        parentShape.setScaleX(xLengthDrag / xLengthOrigin);
        parentShape.setScaleY(yLengthDrag / yLengthOrigin);
    }

	/** 锚点拖拽事件 - 结束拖拽 */
	@Override
	protected void exitMouseDrag(Anchor anchor) {
    	setOriginPositions();
    	xLengthBefore = xLengthDrag;
    	yLengthBefore = yLengthDrag;
    }
	
}
