package AldebaRain.sketchpad.models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/** 锚点集，一个图形9个锚点 */
public class AnchorSets {

	/** 锚点列表集，顺序是：中央，左，右，上，下，左上，左下，右上，右下 */
	private List<Anchor> anchors;

	/** 原位置- 光标 */
	private double oxMouse, oyMouse;
	/** 原位置 - 图形 */
	private double oxParent, oyParent;

	/** 锚点所在图形 */
	private Node parentNode;
	/** 锚点所在图形 - 最初大小 */
	private final double xLengthOrigin, yLengthOrigin;
	/** 锚点所在图形 - 拖拽前大小 */
	private double xLengthBefore, yLengthBefore;
	/** 锚点所在图形 - 拖拽时大小 */
	private double xLengthDrag, yLengthDrag;

	/** 锚点集初始化 */
	public AnchorSets(Node node, double xLength, double yLength) {
		
		// 写入9个锚点
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

		// 初始化拖拽相关信息
		parentNode = node;
		xLengthOrigin = xLength;
		yLengthOrigin = yLength;
		xLengthBefore = xLength;
		yLengthBefore = yLength;
		
		// 添加锚点拖拽事件
		for (Anchor anchor: anchors) {
			anchor.setOnMousePressed(e -> {
				initBeforeDrag(e);
			});
			anchor.setOnMouseDragged(e -> {
				followMouseDrag(e, anchor);
			});
			anchor.setOnMouseReleased(e -> {
				exitMouseDrag(anchor);
			});
		}
	}
	
	/** 显示全部锚点 */
	public void show() {
		for (Anchor anchor: anchors) {
			anchor.show();
		}
	}
	
	/** 隐藏全部锚点 */
	public void hide() {
		for (Anchor anchor: anchors) {
			anchor.hide();
		}
	}

	/** 添加锚点集到Pane */
	public void addtoPane(Pane pane) {
		for (Anchor anchor: anchors) {
			anchor.addtoPane(pane);
		}
	}

	/** 设置锚点集位置 - X */
	public void setTranslateX(double value) {
		for (Anchor anchor: anchors) {
			anchor.setTranslateX(value + anchor.getAnchorId().getXDir() * xLengthOrigin / 2);
		}
	}
	
	/** 设置锚点集位置 - Y */
	public void setTranslateY(double value) {
		for (Anchor anchor: anchors) {
			anchor.setTranslateY(value + anchor.getAnchorId().getYDir() * yLengthOrigin / 2);
		}
	}
	
	/** 所有锚点均位移(dx, dy) */
	public void moveAllAnchors(double dx, double dy) {
		for (Anchor anchor: anchors) {
			anchor.moveAnchor(dx, dy);
		}
	}

	/** 刷新所有锚点的拖拽前位置 */
	public void setOriginPositions() {
		for (Anchor anchor: anchors) {
			anchor.setOriginX(anchor.getTranslateX());
			anchor.setOriginY(anchor.getTranslateY());
		}
    }
	/** 锚点拖拽事件- 拖拽前初始化 */
    private void initBeforeDrag(MouseEvent e) {
    	oxMouse = e.getSceneX();
    	oyMouse = e.getSceneY();
    	setOriginPositions();
    	oxParent = parentNode.getTranslateX();
    	oyParent = parentNode.getTranslateY();
    	xLengthDrag = xLengthBefore;
    	yLengthDrag = yLengthBefore;
    }

	/** 锚点拖拽事件 - 正在拖拽 */
    private void followMouseDrag(MouseEvent e, Anchor anchor) {
		AnchorID movId = anchor.getAnchorId();
    	if (movId == AnchorID.C)
    		return;
    	
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
        parentNode.setTranslateX(dx / 2 + oxParent);
        parentNode.setTranslateY(dy / 2 + oyParent);
        xLengthDrag = xLengthBefore + dx * movId.getXDir();
        yLengthDrag = yLengthBefore + dy * movId.getYDir();
        parentNode.setScaleX(xLengthDrag / xLengthOrigin);
        parentNode.setScaleY(yLengthDrag / yLengthOrigin);
    }

	/** 锚点拖拽事件 - 结束拖拽 */
    private void exitMouseDrag(Anchor anchor) {
    	xLengthBefore = xLengthDrag;
    	yLengthBefore = yLengthDrag;
    }
	
}
