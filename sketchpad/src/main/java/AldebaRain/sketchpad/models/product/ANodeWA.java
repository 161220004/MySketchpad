package AldebaRain.sketchpad.models.product;

import java.util.Iterator;
import java.util.List;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.manager.Selector;
import AldebaRain.sketchpad.models.anchor.AnchorSet;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/** 
 * 锚点图形抽象类(Abstract Node With Anchors).<br> 
 * 是适配器模式的目标抽象类，也是工厂模式的抽象产品类
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

	/** 鼠标事件 - 刷新属性面板 */
	private void refreshPropertiesView() {
		App.frameController.getPropertiesController().refreshPropertiesView();
	}

	/** 鼠标事件 - 根据图形的选择情况刷新画布 */
	private void refreshView() {
		// 获取画布上的所有图形
		List<ANodeWA> allNodes = PaneManager.getCurrentPane().getAllNodes();
		// 获取选中的所有图形
		Selector selector = PaneManager.getCurrentPane().getSelector();
		Iterator<ANodeWA> it = allNodes.iterator();
		while (it.hasNext()) {
			ANodeWA node = it.next();
			if (selector.contains(node)) // 被选中
				node.anchors.show();
			else // 未选中
				node.anchors.hide();
		}
	}
	
	/** 选中事件 - 根据情况操作图形选择器 */
	private void addToSelector() {
		Selector selector = PaneManager.getCurrentPane().getSelector();
		if (selector.isEmpty())
			selector.add(this);
		else {
			selector.change(this);
		}
		// 刷新属性面板和画布
		refreshView();
		refreshPropertiesView();
	}
	
	/** 图形拖拽事件- 拖拽前初始化以及图形选定 */
	private void initBeforeDrag(MouseEvent e) {
    	originMouseX = e.getSceneX();
        originMouseY = e.getSceneY();
        originX = node.getTranslateX();
        originY = node.getTranslateY();
	}

	/** 图形拖拽事件 - 正在拖拽 */
	private void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - originMouseX;
        double dy = e.getSceneY() - originMouseY;
        node.setTranslateX(dx + originX);
        node.setTranslateY(dy + originY);
        anchors.moveAllAnchors(dx, dy);
	}

	/** 图形拖拽事件 - 结束拖拽 */
	private void exitMouseDrag() {
        anchors.setOriginPositions();
		// 刷新属性面板和画布
        refreshView();
		refreshPropertiesView();
	}	

	/** 添加图形拖拽事件 */
	protected final void addMouseEvent() {
		node.setOnMousePressed(e -> {
			addToSelector();
			initBeforeDrag(e);
		});
		node.setOnMouseDragged(e -> {
			followMouseDrag(e);
		});
		node.setOnMouseReleased(e -> {
			exitMouseDrag();
		});
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
	public abstract double getTranslateX();

	/** 适配函数 - 获取图形中心Y坐标 */
	public abstract double getTranslateY();

	/** 适配函数 - 获取图形X方向长度 */
	public abstract double getLengthX();

	/** 适配函数 - 获取图形Y方向长度 */
	public abstract double getLengthY();

	/** 适配函数 - 获取图形边框宽度 */
	public abstract double getStrokeWidth();

	/** 适配函数 - 获取图形颜色 */
	public abstract Color getFill();

	/** 适配函数 - 获取图形边框颜色 */
	public abstract Color getStroke();

}
