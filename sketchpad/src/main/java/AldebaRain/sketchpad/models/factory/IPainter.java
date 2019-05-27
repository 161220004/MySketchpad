package AldebaRain.sketchpad.models.factory;

/** 
 * 锚点图形绘制器.<br>
 * 是工厂模式的抽象工厂类
 * 包括一个默认绘制方法和一个指定参数的绘制方法
 */
public interface IPainter {

	/** 在当前画布的当前图层上绘制ShapeWA */
	public void paint(double x, double y, double xLen, double yLen);

	/** 在当前画布的当前图层上绘制默认大小的ShapeWA */
	public void paint();
	
}
