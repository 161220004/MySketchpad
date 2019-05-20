package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.State;
import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.manager.Selector;
import AldebaRain.sketchpad.models.anchor.AnchorSet;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/** 
 * 锚点图形抽象类(Abstract Node With Anchors).<br> 
 * 是适配器模式的目标抽象类，也是工厂模式的抽象产品类<br>
 * 同时也使用了原型模式，以实现复制粘贴
 * 
 * @see AShapeWA
 * @see LineWA
 */
public abstract class ANodeWA {

	/** 图形类型 */
	protected NodeType type;
	
	/** Adaptee - 图形/直线 */
	protected Node node;
	
	/** Adaptee - 图形的锚点集 */
	protected AnchorSet anchors;

	/** 原位置- 光标 */
	private double originMouseX, originMouseY;
	
	/** 原位置 - 图形 */
	private double originX, originY;

	/** 选中事件 - 释放鼠标后根据情况选择图形或取消选择 */
	protected void refreshSelected(MouseEvent e) {
		Selector selector = PaneManager.getCurrentPane().getSelector();
		// 若进行了拖拽，则选择器不发生改变
		if (State.hasDragged)
			return;
		// 若没有拖拽且已选中
		else if (selector.contains(this)) {
			// 若是复选状态则取消当前图形的选择
			if (e.isControlDown())
				selector.remove(this);
			// 若是单选状态，若当前只选中一个则取消选择；若当前选中多个则改为仅选择当前图形
			else if (selector.count() == 1)
				selector.remove(this);
			else selector.change(this);
		}
		// 若没有拖拽且未选中
		else {
			// 若是复选状态则添加选择当前图形
			if (e.isControlDown())
				selector.add(this);
			// 若是单选状态则仅选择当前图形
			else selector.change(this);
		}
	}
	
	/** 图形拖拽事件- 拖拽前初始化以及图形选定 */
	protected void initBeforeDrag(MouseEvent e) {
    	originMouseX = e.getSceneX();
        originMouseY = e.getSceneY();
        originX = anchors.getTranslateX();
        originY = anchors.getTranslateY();
	}

	/** 图形拖拽事件 - 正在拖拽 */
	protected void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - originMouseX;
        double dy = e.getSceneY() - originMouseY;
        this.setTranslateX(dx + originX);
        this.setTranslateY(dy + originY);
	}

	/** 图形拖拽事件 - 结束拖拽 */
	protected void exitMouseDrag() {
        anchors.setOriginPositions();
	}	

	/** 添加图形拖拽事件 */
	protected void addMouseEvent() {
		node.setOnMousePressed(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				State.hasDragged = false;
				initBeforeDrag(e);
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
		});
		node.setOnMouseDragged(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				State.hasDragged = true;
				followMouseDrag(e);
			}
		});
		node.setOnMouseReleased(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				exitMouseDrag();
				refreshSelected(e);
				State.hasDragged = false;
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
		});
	}

	/** 显示锚点集 */
	public void showAnchors() {
		anchors.show();
	}
	
	/** 隐藏锚点集 */
	public void hideAnchors() {
		anchors.hide();
	}
	
	/** 添加图形到Pane */
	public void addtoPane(Pane pane) {
		pane.getChildren().add(node);
		anchors.addtoPane(pane);
	}

	/** 从Pane移除图形 */
	public void removeFromPane(Pane pane) {
		pane.getChildren().remove(node);
		anchors.removeFromPane(pane);
	}

	/** 获取图形类型 */
	public NodeType getType() {
		return type;
	}

	/** 获取图形描述 */
	public abstract String getDescription();

	/** 适配函数 - 获取图形中心X坐标 */
	public double getTranslateX() {
		return anchors.getTranslateX();
	}

	/** 适配函数 - 重设图形中心X坐标 */
	public void setTranslateX(double x) {
		anchors.setTranslateX(x);
	}

	/** 适配函数 - 获取图形中心Y坐标 */
	public double getTranslateY() {
		return anchors.getTranslateY();
	}

	/** 适配函数 - 重设图形中心Y坐标 */
	public void setTranslateY(double y) {
		anchors.setTranslateY(y);
	}

	/** 适配函数 - 获取图形X方向长度 */
	public double getLengthX() {
		return anchors.getLengthX();
	}

	/** 适配函数 - 重设图形X方向长度 */
	public void setLengthX(double xLen) {
		anchors.setLengthX(xLen);
	}

	/** 适配函数 - 获取图形Y方向长度 */
	public double getLengthY() {
		return anchors.getLengthY();
	}

	/** 适配函数 - 重设图形Y方向长度 */
	public void setLengthY(double yLen) {
		anchors.setLengthY(yLen);
	}

	/** 适配函数 - 获取图形边框宽度 */
	public abstract double getStrokeWidth();

	/** 适配函数 - 重设图形边框宽度 */
	public abstract void setStrokeWidth(double width);

	/** 适配函数 - 获取图形颜色 */
	public abstract Color getFill();

	/** 适配函数 - 重设图形颜色 */
	public abstract void setFill(Color color);

	/** 适配函数 - 获取图形边框颜色 */
	public abstract Color getStroke();

	/** 适配函数 - 重设图形边框颜色 */
	public abstract void setStroke(Color color);

	/** 原型模式 - 深克隆 */
	public abstract ANodeWA clone();
	
}
