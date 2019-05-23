package AldebaRain.sketchpad.models.anchor;

import AldebaRain.sketchpad.App;
import javafx.scene.shape.Rectangle;

public class AnchorRectangleSet extends AnchorShapeSet {

	/** 构造函数A - 当Ellipse形状已确认时调用 */
	public AnchorRectangleSet(Rectangle rect) {
		super(rect, rect.getWidth(), rect.getHeight());
	}

	/** 根据已经设好的锚点重设图形大小 */
	@Override
	protected void resizeShape() {
		((Rectangle)parentNode).setWidth(this.getLengthX());
		((Rectangle)parentNode).setHeight(this.getLengthY());
	}

	@Override
	protected void exitMouseDrag(Anchor anchor) {
		super.exitMouseDrag(anchor);
		// 添加到历史记录
		App.frameController.getHistoryController().saveAsHistory("矩形大小变换");
	}
	
}
