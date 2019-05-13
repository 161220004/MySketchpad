package AldebaRain.sketchpad.models;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/** 带锚点的圆形 */
public class CircleWithAnchors {

	/** 圆形 */
	private final Circle circle;
	
	/** 圆形的锚点集 */
	private AnchorSets anchors;

	/** 原位置- 光标 */
	private double oxMouse, oyMouse;
	/** 原位置 - 图形 */
	private double originX, originY;

	/** 初始化：添加图形到Pane，并增加拖拽移动事件 */
	public void init(Pane pane) {
		addtoPane(pane);
		circle.setOnMousePressed(e -> initBeforeDrag(e));
		circle.setOnMouseDragged(e -> followMouseDrag(e));
	}
	
	public CircleWithAnchors(Pane pane, double r) {
		circle = new Circle(r);
		anchors = new AnchorSets(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
		init(pane);
	}
	
	public CircleWithAnchors(Pane pane, double x, double y, double r) {
		circle = new Circle(r);
		anchors = new AnchorSets(circle, 2 * circle.getRadius(), 2 * circle.getRadius());
		this.setTranslateX(x);
		this.setTranslateY(y);
		init(pane);
	}

	/** 设置图形位置 - X */
	public void setTranslateX(double value) {
		circle.setTranslateX(value);
		anchors.setTranslateX(value);
	}
	
	/** 设置图形位置 - Y */
	public void setTranslateY(double value) {
		circle.setTranslateY(value);
		anchors.setTranslateY(value);
	}
	
	/** 添加图形到Pane */
	private void addtoPane(Pane pane) {
		pane.getChildren().add(circle);
		anchors.addtoPane(pane);
	}

	/** 图形拖拽事件- 拖拽前初始化 */
    private void initBeforeDrag(MouseEvent e) {
    	oxMouse = e.getSceneX();
        oyMouse = e.getSceneY();
        originX = circle.getTranslateX();
        originY = circle.getTranslateY();
        anchors.setOriginPositions();
    }

	/** 图形拖拽事件 - 正在拖拽 */
    private void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - oxMouse;
        double dy = e.getSceneY() - oyMouse;
        circle.setTranslateX(dx + originX);
        circle.setTranslateY(dy + originY);
        anchors.moveAllAnchors(dx, dy);
    }
}
