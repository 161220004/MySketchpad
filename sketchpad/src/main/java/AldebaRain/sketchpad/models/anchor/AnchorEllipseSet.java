package AldebaRain.sketchpad.models.anchor;

import AldebaRain.sketchpad.App;
import javafx.scene.shape.Ellipse;

public class AnchorEllipseSet extends AnchorShapeSet {

	/** 构造函数A - 当Ellipse形状已确认时调用 */
	public AnchorEllipseSet(Ellipse ellipse) {
		super(ellipse, 2 * ellipse.getRadiusX(), 2 * ellipse.getRadiusY());
	}

	/** 根据已经设好的锚点重设图形大小 */
	@Override
	protected void resizeShape() {
		((Ellipse)parentNode).setRadiusX(this.getLengthX() / 2);
		((Ellipse)parentNode).setRadiusY(this.getLengthY() / 2);
	}
	
	@Override
	protected void exitMouseDrag(Anchor anchor) {
		super.exitMouseDrag(anchor);
		// 添加到历史记录
		App.frameController.getHistoryController().saveAsHistory("椭圆大小变换");
	}
	
}
