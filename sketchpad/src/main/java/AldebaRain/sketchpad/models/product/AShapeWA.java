package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.models.scene.AnchorSet;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/** 有锚点图形适配器(Adapter of ShapeWithAnchors).<br> 
 * 是适配器模式的适配器类,<br>
 * 也是工厂模式的抽象产品类 */
public abstract class AShapeWA implements IShapeWA {

	/** Adaptee - 图形 */
	protected Shape shape;
	
	/** Adaptee - 图形的锚点集 */
	protected AnchorSet anchors;

	/** Adaptee - 图层 */
	protected Pane pane;

	/** 原位置- 光标 */
	private double originMouseX, originMouseY;
	
	/** 原位置 - 图形 */
	private double originX, originY;

	/** 初始化：添加图形到Pane，并增加拖拽移动事件 */
	@Override
	public void init() {
		addtoPane(pane);
		shape.setOnMousePressed(e -> initBeforeDrag(e));
		shape.setOnMouseDragged(e -> followMouseDrag(e));
	}

	/** 设置图形位置 - X */
	@Override
	public void setTranslateX(double value) {
		shape.setTranslateX(value);
		anchors.setTranslateX(value);
	}

	/** 设置图形位置 - Y */
	@Override
	public void setTranslateY(double value) {
		shape.setTranslateY(value);
		anchors.setTranslateY(value);
	}

	/** 添加图形到Pane */
	@Override
	public void addtoPane(Pane pane) {
		pane.getChildren().add(shape);
		anchors.addtoPane(pane);
	}

	/** 图形拖拽事件- 拖拽前初始化 */
	@Override
	public void initBeforeDrag(MouseEvent e) {
    	originMouseX = e.getSceneX();
        originMouseY = e.getSceneY();
        originX = shape.getTranslateX();
        originY = shape.getTranslateY();
        anchors.setOriginPositions();
	}

	/** 图形拖拽事件 - 正在拖拽 */
	@Override
	public void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - originMouseX;
        double dy = e.getSceneY() - originMouseY;
        shape.setTranslateX(dx + originX);
        shape.setTranslateY(dy + originY);
        anchors.moveAllAnchors(dx, dy);
	}	

}
