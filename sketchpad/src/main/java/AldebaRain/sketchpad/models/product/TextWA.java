package AldebaRain.sketchpad.models.product;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.Default;
import AldebaRain.sketchpad.State;
import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.models.anchor.AnchorTextSet;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * 用于添加文字描述
 */
public class TextWA extends RectangleWA {

	/** 矩形中心的文字标签 */
	private Label label;
	
	/** 构造函数 */
	public TextWA(Rectangle rect, Label label) {
		this.type = NodeType.Text;
		this.node = rect;
		this.label = label;
		this.anchors = new AnchorTextSet(rect, label);
		this.anchors.hide();
		this.addMouseEvent();
	}

	/** 重写拖拽事件 */
	@Override
	protected void followMouseDrag(MouseEvent e) {
		super.followMouseDrag(e);
		label.setTranslateX(node.getTranslateX());
		label.setTranslateY(node.getTranslateY());
	}
	
	/** 重写鼠标事件 */
	@Override
	protected void addMouseEvent() {
		label.setOnMousePressed(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				State.hasDragged = false;
				initBeforeDrag(e);
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
		});
		label.setOnMouseDragged(e -> {
			// 仅鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				State.hasDragged = true;
				followMouseDrag(e);
			}
		});
		label.setOnMouseReleased(e -> {
			// 鼠标左键拖拽
			if (e.getButton() == MouseButton.PRIMARY) {
				exitMouseDrag();
				refreshSelected(e);
				State.hasDragged = false;
				// 刷新属性面板和画布
				App.frameController.refreshView();
				App.frameController.getPropertiesController().refreshPropertiesView();
			}
			// 鼠标右键更改内容
			if (e.getButton() == MouseButton.SECONDARY) {
				TextField field = new TextField();
				field.setTranslateX(this.getTranslateX());
				field.setTranslateY(this.getTranslateY());
				field.setPrefWidth(this.getLengthX());
				field.setPrefHeight(this.getLengthY());
				field.setMaxWidth(this.getLengthX());
				field.setMaxHeight(this.getLengthY());
				field.setText(label.getText());
				field.setEditable(true);
				field.requestFocus();
				PaneManager.getCurrentPane().getPane().getChildren().add(field);
				// 更改内容完毕的操作
				field.setOnKeyPressed(event -> { 
					if (event.getCode() == KeyCode.ENTER) {
						label.setText(field.getText());
						PaneManager.getCurrentPane().getPane().getChildren().remove(field);
					}
				});
			}
		});
	}
	
	/** 重写添加到当前画布 */
	@Override
	public void addtoPane(Pane pane) {
		super.addtoPane(pane);
		pane.getChildren().add(label);
	}
	
	/** 获取文本标签 */
	public Label getLabel() {
		return label;
	}
	
	@Override
	public String getDescription() {
		return new String("文本");
	}

	/** 克隆一个Label类型 */
	private Label cloneLabel() {
		Label newLabel = new Label();
		newLabel.setTranslateX(label.getTranslateX() + Default.pasteBiasX);
		newLabel.setTranslateY(label.getTranslateY() + Default.pasteBiasY);
		newLabel.setPrefWidth(label.getPrefWidth());
		newLabel.setPrefHeight(label.getPrefHeight());
		newLabel.setMaxWidth(label.getMaxWidth());
		newLabel.setMaxHeight(label.getMaxHeight());
		newLabel.setText("请输入描述...");
		newLabel.setWrapText(true);
		return newLabel;
	}
	
	@Override
	public ANodeWA clone() {
		Rectangle rect = cloneRectangle();
		Label label = cloneLabel();
		TextWA textWA = new TextWA(rect, label);
		return textWA;
	}
	
}
