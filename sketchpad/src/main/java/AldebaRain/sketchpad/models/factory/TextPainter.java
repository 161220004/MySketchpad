package AldebaRain.sketchpad.models.factory;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.Default;
import AldebaRain.sketchpad.hierarchy.PaneManager;
import AldebaRain.sketchpad.models.product.TextWA;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** 
 * 描述文本框绘制器.<br>
 * 是工厂模式的具体工厂类
 * 
 * @see IPainter
 */
public class TextPainter implements IPainter {

	private Rectangle createRectangle(double x, double y, double xLen, double yLen) {
		Rectangle rect = new Rectangle();
		rect.setTranslateX(x);
		rect.setTranslateY(y);
		rect.setWidth(xLen);
		rect.setHeight(yLen);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(Default.textLabelStrokeW);
		return rect;
	}
	
	private Label createLabel(double x, double y, double xLen, double yLen) {
		Label label = new Label();
		label.setTranslateX(x);
		label.setTranslateY(y);
		label.setPrefWidth(xLen);
		label.setPrefHeight(yLen);
		label.setMaxWidth(xLen);
		label.setMaxHeight(yLen);
		label.setText("请输入描述...");
		label.setWrapText(true);
		label.setAlignment(Pos.CENTER);
		return label;
	}
	
	@Override
	public void paint(double x, double y, double xLen, double yLen) {
		Rectangle rect = createRectangle(x, y, xLen, yLen);
		Label label = createLabel(x, y, xLen - Default.textLabelMargin, yLen - Default.textLabelMargin);
		TextWA textWA = new TextWA(rect, label);
		// 在当前画布的当前图层绘制
		PaneManager.getInstance().getCurrentPane().add(textWA);
		// 添加到历史记录
		App.frameController.getHistoryController().saveAsHistory("绘制描述文本");
	}

	@Override
	public void paint() {
    	this.paint(Default.shapeOX, Default.shapeOY, Default.textLabelLenX, Default.textLabelLenY);
	}

}
