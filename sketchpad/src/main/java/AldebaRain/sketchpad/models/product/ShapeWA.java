package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.*;
import AldebaRain.sketchpad.hierarchy.PaneManager;
import AldebaRain.sketchpad.models.anchor.AnchorSet;
import AldebaRain.sketchpad.selector.Selector;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/** 
 * 锚点Shape，为适配器子类提供通用函数
 * 
 * @see ANodeWA
 * @see LineWA
 * @see EllipseWA
 * @see RectangleWA
 * @see PolygonWA
 */
public class ShapeWA extends ANodeWA {

	/** 图形类型 */
	protected NodeType type;
	
	/** Adaptee - 图形/直线 */
	protected Shape node;
	
	/** Adaptee - 图形的锚点集 */
	protected AnchorSet anchors;

	/** 原位置- 光标 */
	private double originMouseX, originMouseY;
	
	/** 原位置 - 图形 */
	private double originX, originY;

	@Override
	protected void refreshSelected(MouseEvent e) {
		Selector selector = PaneManager.getInstance().getCurrentPane().getSelector();
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
	
	@Override
	protected void initBeforeDrag(MouseEvent e) {
    	originMouseX = e.getSceneX();
        originMouseY = e.getSceneY();
        originX = anchors.getTranslateX();
        originY = anchors.getTranslateY();
	}

	@Override
	protected void followMouseDrag(MouseEvent e) {
    	double dx = e.getSceneX() - originMouseX;
        double dy = e.getSceneY() - originMouseY;
        this.setTranslateX(dx + originX);
        this.setTranslateY(dy + originY);
	}

	@Override
	protected void exitMouseDrag() {
        anchors.setOriginPositions();
	}	

	@Override
	protected void addMouseEvent() {
		Selector selector = PaneManager.getInstance().getCurrentPane().getSelector();
		node.setOnMousePressed(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY && selector.contains(this)) {
				State.hasDragged = false;
				for (ANodeWA one: selector.getNodes())
					one.initBeforeDrag(e);
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
		});
		node.setOnMouseDragged(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY && selector.contains(this)) {
				State.hasDragged = true;
				for (ANodeWA one: selector.getNodes()) 
					one.followMouseDrag(e);
			}
		});
		node.setOnMouseReleased(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				this.refreshSelected(e);
				if (selector.contains(this)) {
					for (ANodeWA one: selector.getNodes()) {
						one.exitMouseDrag();
					}
					// 若拖拽了图形，添加到历史记录
					if (State.hasDragged) {
						if (selector.count() == 1)
							App.frameController.getHistoryController().saveAsHistory(this.getType().getDesc() + "位置变换");
						else
							App.frameController.getHistoryController().saveAsHistory("选中图形位置变换");
					}
				}
				State.hasDragged = false;
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
		});
	}

	@Override
	public void showAnchors() {
		anchors.show();
	}

	@Override
	public void hideAnchors() {
		anchors.hide();
	}

	@Override
	public void addtoPane(Pane pane) {
		pane.getChildren().add(node);
		anchors.addtoPane(pane);
	}

	@Override
	public void removeFromPane(Pane pane) {
		pane.getChildren().remove(node);
		anchors.removeFromPane(pane);
	}

	@Override
	public NodeType getType() {
		return type;
	}

	@Override
	public double getTranslateX() {
		return anchors.getTranslateX();
	}

	@Override
	public void setTranslateX(double x) {
		anchors.setTranslateX(x);
	}

	@Override
	public double getTranslateY() {
		return anchors.getTranslateY();
	}

	@Override
	public void setTranslateY(double y) {
		anchors.setTranslateY(y);
	}

	@Override
	public double getLengthX() {
		return anchors.getLengthX();
	}

	@Override
	public void setLengthX(double xLen) {
		anchors.setLengthX(xLen);
	}

	@Override
	public double getLengthY() {
		return anchors.getLengthY();
	}

	@Override
	public void setLengthY(double yLen) {
		anchors.setLengthY(yLen);
	}

	@Override
	public double getStrokeWidth() {
		return node.getStrokeWidth();
	}

	@Override
	public Color getFill() {
		return (Color)node.getFill();
	}

	@Override
	public Color getStroke() {
		return (Color)node.getStroke();
	}

	@Override
	public void setStrokeWidth(double width) {
		node.setStrokeWidth(width);
	}
	
	@Override
	public void setFill(Color color) {
		node.setFill(color);
	}
	
	@Override
	public void setStroke(Color color) {
		node.setStroke(color);
	}

	@Override
	public String getDescription() {
		return new String("图形");
	}

	@Override
	public ANodeWA clone() {
		return null;
	}

}
