package AldebaRain.sketchpad.models.anchor;

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

}
