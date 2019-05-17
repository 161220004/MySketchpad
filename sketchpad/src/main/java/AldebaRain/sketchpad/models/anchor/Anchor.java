package AldebaRain.sketchpad.models.anchor;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** 
 * 锚点类型，用于图形缩放 
 * 
 * @see AnchorID
 */
public class Anchor extends Rectangle {

	/** 锚点形状 - 边长 */
	private static final double length = 5.0;
	/** 锚点形状 - 边框粗细 */
	private static final double strokeWidth = 2.0;
	
	/** 锚点位置标识 - 边锚点L/R/U/D（仅水平/仅竖直）、角锚点（LU/LD/RU/RD）、中心锚点C（不能拖拽） */
	private final AnchorID anchorId;
	
	/** 锚点拖拽前位置 */
	private double originX, originY;
	
	/** 锚点初始化 */
	public Anchor(AnchorID aid, Node node, double xLength, double yLength) {
		
		// 形状位置初始化
		super(length, length);
    	this.setFill(Color.TRANSPARENT);
    	this.setStroke(Color.LIGHTGRAY);
    	this.setStrokeWidth(strokeWidth);
    	double dx = xLength / 2 * aid.getXDir();
    	double dy = yLength / 2 * aid.getYDir();
		this.setTranslateX(node.getTranslateX() + dx);
		this.setTranslateY(node.getTranslateY() + dy);
		
    	// Id等初始化
		anchorId = aid;
		setOriginX(this.getTranslateX());
		setOriginY(this.getTranslateY());
	}

	/** 获取锚点位置标识 */
	public AnchorID getAnchorId() {
		return anchorId;
	}

	/** 获取锚点拖拽前位置 - X */
	public double getOriginX() {
		return originX;
	}

	/** 获取锚点拖拽前位置 - Y */
	public double getOriginY() {
		return originY;
	}

	/** 拖拽后设置锚点新位置 - X */
	public void setOriginX(double originX) {
		this.originX = originX;
	}

	/** 拖拽后设置锚点新位置 - Y */
	public void setOriginY(double originY) {
		this.originY = originY;
	}

	/** 显示锚点 */
	public void show() {
		this.setVisible(true);
	}
	
	/** 隐藏锚点 */
	public void hide() {
		this.setVisible(false);
	}

	/** 添加锚点到Pane */
	public void addtoPane(Pane pane) {
		pane.getChildren().add(this);
	}

	/** 从Pane移除锚点 */
	public void removeFromPane(Pane pane) {
		pane.getChildren().remove(this);
	}
	
	/** （Debug使用）获取锚点位置 */
	public String toString() {
		return new String("(" + this.getTranslateX() + "," + this.getTranslateY() + ")");
	}
	
}
