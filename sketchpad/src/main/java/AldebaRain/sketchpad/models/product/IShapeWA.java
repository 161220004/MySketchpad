package AldebaRain.sketchpad.models.product;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/** 有锚点图形(ShapeWithAnchors).<br> 
 * 是适配器模式的目标抽象类 */
public interface IShapeWA {

	/** 设置图形位置 - X */
	public void setTranslateX(double value);
	
	/** 设置图形位置 - Y */
	public void setTranslateY(double value);

	/** 初始化：添加图形到对应图层，并增加拖拽移动事件 */
	public void init();

	/** 添加图形到Pane */
	public void addtoPane(Pane pane);

	/** 图形拖拽事件- 拖拽前初始化 */
	public void initBeforeDrag(MouseEvent e);

	/** 图形拖拽事件 - 正在拖拽 */
	public void followMouseDrag(MouseEvent e);
	
}
