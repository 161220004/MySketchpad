package AldebaRain.sketchpad.models.anchor;

import javafx.scene.shape.Ellipse;

public class AnchorEllipseSet extends AnchorShapeSet {

	/** 构造函数A - 当Ellipse形状已确认时调用 */
	public AnchorEllipseSet(Ellipse ellipse) {
		super(ellipse, 2 * ellipse.getRadiusX(), 2 * ellipse.getRadiusY());
	}

	/** 构造函数B（危险） - 当Ellipse形状未知时调用 */
	@Deprecated
	public AnchorEllipseSet(Ellipse ellipse, double xLength, double yLength, double x, double y) {
		super(ellipse, xLength, yLength, x, y);
	}

	/** 根据已经设好的锚点重设图形大小 */
	@Override
	protected void resizeShape() {
		((Ellipse)parentNode).setRadiusX(this.getLengthX() / 2);
		((Ellipse)parentNode).setRadiusY(this.getLengthY() / 2);
	}
	
}
