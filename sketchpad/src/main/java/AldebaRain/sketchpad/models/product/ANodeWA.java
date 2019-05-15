package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.scene.AnchorSet;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/** 
 * 锚点图形抽象类(Abstract Node With Anchors).<br> 
 * 是适配器模式的目标抽象类，也是工厂模式的抽象产品类
 * 
 * @see ShapeWA
 * @see LineWA
 */
public abstract class ANodeWA {

	/** Adaptee - 图形/直线 */
	protected Node node;
	
	/** Adaptee - 图形的锚点集 */
	protected AnchorSet anchors;

	/** 所在图层 */
	protected Pane pane;

	/** 原位置- 光标 */
	private double originMouseX, originMouseY;
	
	/** 原位置 - 图形 */
	private double originX, originY;

	/** 添加图形到Pane */
	protected void addtoPane(Pane pane) {
		pane.getChildren().add(node);
		anchors.addtoPane(pane);
	}

	/** 添加图形拖拽事件 */
	protected void addMouseEvent() {
		node.setOnMousePressed(e -> {
			initBeforeDrag(e);
		});
		node.setOnMouseDragged(e -> {
			followMouseDrag(e);
		});
		node.setOnMouseReleased(e -> {
			exitMouseDrag();
		});
	}

	/** 图形拖拽事件- 拖拽前初始化 */
	protected void initBeforeDrag(MouseEvent e) {
    	originMouseX = e.getSceneX();
        originMouseY = e.getSceneY();
        originX = node.getTranslateX();
        originY = node.getTranslateY();
	}

	/** 图形拖拽事件 - 正在拖拽 */
	protected void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - originMouseX;
        double dy = e.getSceneY() - originMouseY;
        node.setTranslateX(dx + originX);
        node.setTranslateY(dy + originY);
        anchors.moveAllAnchors(dx, dy);
	}

	/** 图形拖拽事件 - 结束拖拽 */
	protected void exitMouseDrag() {
        anchors.setOriginPositions();
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
