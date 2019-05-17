package AldebaRain.sketchpad.models.anchor;

import java.util.List;

import AldebaRain.sketchpad.App;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/** 
 * 一个图形的抽象锚点集.<br>
 * ShapeWA类型有9个锚点，LineWA类型有3个锚点<br>
 * 
 * @see Anchor
 * @see AnchorShapeSet 
 * @see AnchorLineSet 
 */
public abstract class AnchorSet {

	/** 锚点列表集，顺序是：中央，左，右，上，下，左上，左下，右上，右下 */
	protected List<Anchor> anchors;

	/** 原位置- 光标 */
	protected double oxMouse, oyMouse;
	
	/** 原位置 - 图形 */
	protected double oxParent, oyParent;

	/** 锚点所在图形 */
	protected Node parentNode;	

	/** 鼠标事件 - 刷新属性面板 */
	private void refreshPropertiesView() {
		App.frameController.getPropertiesController().refreshPropertiesView();
	}
	
	/** 锚点拖拽事件- 拖拽前初始化 */
	protected abstract void initBeforeDrag(MouseEvent e);

	/** 锚点拖拽事件 - 正在拖拽 */
	protected abstract void followMouseDrag(MouseEvent e, Anchor anchor);

	/** 锚点拖拽事件 - 结束拖拽 */
	protected abstract void exitMouseDrag(Anchor anchor);

	/** 构造函数初始化 - 添加锚点 */
	protected abstract void addAnchors(Node node, double xLength, double yLength);
	
	/** 添加锚点拖拽事件 */
	protected final void addMouseEvent() {
		for (Anchor anchor: anchors) {
			anchor.setOnMousePressed(e -> {
				refreshPropertiesView();
				initBeforeDrag(e);
			});
			anchor.setOnMouseDragged(e -> {
				followMouseDrag(e, anchor);
			});
			anchor.setOnMouseReleased(e -> {
				exitMouseDrag(anchor);
				refreshPropertiesView();
			});
		}
	}

	/** 添加锚点集到Pane */
	public void addtoPane(Pane pane) {
		for (Anchor anchor: anchors) {
			anchor.addtoPane(pane);
		}
	}

	/** 从Pane移除锚点集 */
	public void removeFromPane(Pane pane) {
		for (Anchor anchor: anchors) {
			anchor.removeFromPane(pane);
		}
	}

	/** 所有锚点均位移(dx, dy) */
	public void moveAllAnchors(double dx, double dy) {
		for (Anchor anchor: anchors) {
			anchor.setTranslateX(anchor.getOriginX() + dx);
			anchor.setTranslateY(anchor.getOriginY() + dy);
		}
	}

	/** 刷新所有锚点的拖拽前位置 */
	public void setOriginPositions() {
		for (Anchor anchor: anchors) {
			anchor.setOriginX(anchor.getTranslateX());
			anchor.setOriginY(anchor.getTranslateY());
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

	/** 获取指定位置锚点 */
	public Anchor getAnchor(AnchorID aid) {
		for (Anchor anchor: anchors) {
			if (anchor.getAnchorId() == aid)
				return anchor;
		}
		return null;
	}
	
	/** 获取锚点集X方向长度 */
	public double getLengthX() {
		Anchor aLU = getAnchor(AnchorID.LU);
		Anchor aRD = getAnchor(AnchorID.RD);
		if (aLU != null && aRD != null)
			return Math.abs(aRD.getTranslateX() - aLU.getTranslateX());
		else 
			return 0;
	}

	/** 获取锚点集Y方向长度 */
	public double getLengthY() {
		Anchor aLU = getAnchor(AnchorID.LU);
		Anchor aRD = getAnchor(AnchorID.RD);
		if (aLU != null && aRD != null)
			return Math.abs(aRD.getTranslateY() - aLU.getTranslateY());
		else 
			return 0;
	}
	
}
