package AldebaRain.sketchpad.models.product;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/** 
 * 锚点图形抽象类(Abstract Node With Anchors).<br> 
 * 是适配器模式的目标抽象类，也是工厂模式的抽象产品类<br>
 * 同时也使用了原型模式，以实现复制粘贴
 * 
 * @see ShapeWA
 */
public abstract class ANodeWA {

	/** 选中事件 - 释放鼠标后根据情况选择图形或取消选择 */
	protected abstract void refreshSelected(MouseEvent e);
	
	/** 图形拖拽事件- 拖拽前初始化以及图形选定 */
	protected abstract void initBeforeDrag(MouseEvent e);

	/** 图形拖拽事件 - 正在拖拽 */
	protected abstract void followMouseDrag(MouseEvent e);

	/** 图形拖拽事件 - 结束拖拽 */
	protected abstract void exitMouseDrag();
	
	/** 添加图形拖拽事件 */
	protected abstract void addMouseEvent();

	/** 显示锚点集 */
	public abstract void showAnchors();
	
	/** 隐藏锚点集 */
	public abstract void hideAnchors();
	
	/** 添加图形到Pane */
	public abstract void addtoPane(Pane pane);

	/** 从Pane移除图形 */
	public abstract void removeFromPane(Pane pane);

	/** 获取图形类型 */
	public abstract NodeType getType();

	/** 获取图形描述 */
	public abstract String getDescription();

	/** 适配函数 - 获取图形中心X坐标 */
	public abstract double getTranslateX();

	/** 适配函数 - 重设图形中心X坐标 */
	public abstract void setTranslateX(double x);

	/** 适配函数 - 获取图形中心Y坐标 */
	public abstract double getTranslateY();

	/** 适配函数 - 重设图形中心Y坐标 */
	public abstract void setTranslateY(double y);

	/** 适配函数 - 获取图形X方向长度 */
	public abstract double getLengthX();

	/** 适配函数 - 重设图形X方向长度 */
	public abstract void setLengthX(double xLen);

	/** 适配函数 - 获取图形Y方向长度 */
	public abstract double getLengthY();

	/** 适配函数 - 重设图形Y方向长度 */
	public abstract void setLengthY(double yLen);

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
