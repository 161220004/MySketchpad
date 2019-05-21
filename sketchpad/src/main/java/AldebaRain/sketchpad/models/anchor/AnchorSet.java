package AldebaRain.sketchpad.models.anchor;

import java.util.List;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.State;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
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

	/** 锚点拖拽事件- 拖拽前初始化 */
	protected void initBeforeDrag(MouseEvent e) {
    	oxMouse = e.getSceneX();
    	oyMouse = e.getSceneY();
    	oxParent = this.getTranslateX();
    	oyParent = this.getTranslateY();
	}

	/** 锚点拖拽事件 - 正在拖拽 */
	protected abstract void followMouseDrag(MouseEvent e, Anchor anchor);

	/** 锚点拖拽事件 - 结束拖拽 */
	protected void exitMouseDrag(Anchor anchor) {
		this.setOriginPositions();
	}

	/** 添加锚点拖拽事件；
	 * 添加功能使锚点拖拽时显示位置 */
	protected final void addMouseEvent() {
		for (Anchor anchor: anchors) {
			anchor.setOnMousePressed(e -> {
				// 仅鼠标左键拖拽
				if (e.getButton() == MouseButton.PRIMARY) {
					initBeforeDrag(e);
					if (State.showDragAnchorPosTips)
						anchor.initTipLabel();
					App.frameController.getPropertiesController().refreshPropertiesView();
				}
			});
			anchor.setOnMouseDragged(e -> {
				// 仅鼠标左键拖拽
				if (e.getButton() == MouseButton.PRIMARY) {
					followMouseDrag(e, anchor);
					if (State.showDragAnchorPosTips)
						anchor.refreshTipLabel();
				}
			});
			anchor.setOnMouseReleased(e -> {
				// 仅鼠标左键拖拽
				if (e.getButton() == MouseButton.PRIMARY) {
					exitMouseDrag(anchor);
					if (State.showDragAnchorPosTips)
						anchor.removeTipLabel();
					App.frameController.getPropertiesController().refreshPropertiesView();
				}
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

	/** 获取指定Index锚点 */
	public Anchor getAnchor(int id) {
		for (Anchor anchor: anchors) {
			if (anchor.getIndex() == id)
				return anchor;
		}
		return null;
	}

	/** 获取锚点集中心X坐标 */
	public double getTranslateX() {
		return getAnchor(AnchorID.C).getTranslateX();
	}
	
	/** 获取锚点集中心Y坐标 */
	public double getTranslateY() {
		return getAnchor(AnchorID.C).getTranslateY();
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

	/** 重设锚点集X坐标 */
	public void setTranslateX(double x) {
		// 处理锚点集
		double preX = this.getTranslateX();
		double halfXLen = this.getLengthX() / 2;
		for (Anchor anchor: anchors) {
			double sign = (anchor.getTranslateX() < preX) ? (-1) : (1);
			if (anchor.getAnchorId().getXDir() == 0)
				sign = 0;
			anchor.setTranslateX(x + sign * halfXLen);
		}
		this.setOriginPositions();
		// 处理图形
		parentNode.setTranslateX(x);
	}
	
	/** 重设锚点集Y坐标 */
	public void setTranslateY(double y) {
		// 处理锚点集
		double preY = this.getTranslateY();
		double halfYLen = this.getLengthY() / 2;
		for (Anchor anchor: anchors) {
			double sign = (anchor.getTranslateY() < preY) ? (-1) : (1);
			if (anchor.getAnchorId().getYDir() == 0)
				sign = 0;
			anchor.setTranslateY(y + sign * halfYLen);
		}
		this.setOriginPositions();
		// 处理图形
		parentNode.setTranslateY(y);
	}
	
	/** 重设锚点集X方向长度 */
	public void setLengthX(double xLen) {
		// 处理锚点集
		double x = this.getTranslateX();
		for (Anchor anchor: anchors) {
			double sign = (anchor.getTranslateX() < x) ? (-1) : (1);
			if (anchor.getAnchorId().getXDir() == 0)
				sign = 0;
			anchor.setTranslateX(x + sign * xLen / 2);
		}
		this.setOriginPositions();
		// 处理图形在子类
	}

	/** 重设锚点集Y方向长度 */
	public void setLengthY(double yLen) {
		// 处理锚点集
		double y = this.getTranslateY();
		for (Anchor anchor: anchors) {
			double sign = (anchor.getTranslateY() < y) ? (-1) : (1);
			if (anchor.getAnchorId().getYDir() == 0)
				sign = 0;
			anchor.setTranslateY(y + sign * yLen / 2);
		}
		this.setOriginPositions();
		// 处理图形在子类
	}

}
