package AldebaRain.sketchpad.models.anchor;

import AldebaRain.sketchpad.Default;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class AnchorTextSet extends AnchorRectangleSet {

	/** 该文本标签 */
	protected Label label;
	
	public AnchorTextSet(Rectangle rect, Label label) {
		super(rect);
		this.label = label;
	}

	/** 根据已经设好的锚点重设图形大小 */
	@Override
	protected void resizeShape() {
		super.resizeShape();
		label.setPrefWidth(((Rectangle)parentNode).getWidth() - Default.textLabelMargin);
		label.setPrefHeight(((Rectangle)parentNode).getHeight() - Default.textLabelMargin);
		label.setMaxWidth(((Rectangle)parentNode).getWidth() - Default.textLabelMargin);
		label.setMaxHeight(((Rectangle)parentNode).getHeight() - Default.textLabelMargin);
		label.setTranslateX(parentNode.getTranslateX());
		label.setTranslateY(parentNode.getTranslateY());
	}

}
