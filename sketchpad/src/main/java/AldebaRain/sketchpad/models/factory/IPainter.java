package AldebaRain.sketchpad.models.factory;

/** 
 * 锚点图形绘制器.<br>
 * 是工厂模式的抽象工厂类
 */
public interface IPainter {

	/** 在当前画布的当前图层上绘制ShapeWA */
	public void paint(double x, double y, double xLen, double yLen);
	
}
