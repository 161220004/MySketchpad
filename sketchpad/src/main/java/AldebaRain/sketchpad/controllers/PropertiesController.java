package AldebaRain.sketchpad.controllers;

import java.util.Comparator;

import AldebaRain.sketchpad.manager.*;
import AldebaRain.sketchpad.models.product.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PropertiesController {

	/** 属性面板 */
	@FXML
	private AnchorPane properties;
	
	/** 属性面板 - 根元件 */
	@FXML
	private Accordion accordion;

	/** 属性面板 - 类型描述Ttp - 标签 */
	@FXML
	private Label descLab;

	/** 属性面板 - 位置Ttp - 图形X坐标/直线StartX坐标标签 */
	@FXML
	private Label posXLab;

	/** 属性面板 - 位置Ttp - 图形X坐标/直线StartX坐标 */
	@FXML
	private TextField posXField;

	/** 属性面板 - 位置Ttp - 图形Y坐标/直线StartY坐标标签 */
	@FXML
	private Label posYLab;

	/** 属性面板 - 位置Ttp - 图形Y坐标/直线StartY坐标 */
	@FXML
	private TextField posYField;

	/** 属性面板 - 位置Ttp - 图形不可见/直线EndX坐标标签 */
	@FXML
	private Label posXEndLab;

	/** 属性面板 - 位置Ttp - 图形不可见/直线EndX坐标 */
	@FXML
	private TextField posXEndField;

	/** 属性面板 - 位置Ttp - 图形不可见/直线EndY坐标标签 */
	@FXML
	private Label posYEndLab;

	/** 属性面板 - 位置Ttp - 图形不可见/直线EndY坐标 */
	@FXML
	private TextField posYEndField;

	/** 属性面板 - 大小Ttp - 图形/直线在X方向的长的标签 */
	@FXML
	private Label sizeXLab;

	/** 属性面板 - 大小Ttp - 图形/直线在X方向的长 */
	@FXML
	private TextField sizeXField;

	/** 属性面板 - 大小Ttp - 图形/直线在Y方向的长的标签 */
	@FXML
	private Label sizeYLab;

	/** 属性面板 - 大小Ttp - 图形/直线在Y方向的长 */
	@FXML
	private TextField sizeYField;

	/** 属性面板 - 大小Ttp - 图形边框宽度/直线宽度 */
	@FXML
	private TextField sizeStrokeField;

	/** 属性面板 - 颜色Ttp - 图形边框颜色/直线颜色 */
	@FXML
	private ColorPicker colorStrokePicker;

	/** 属性面板 - 颜色Ttp - 图形填充颜色标签 */
	@FXML
	private Label colorFillLab;

	/** 属性面板 - 颜色Ttp - 图形填充颜色/直线不可见 */
	@FXML
	private ColorPicker colorFillPicker;

	/** 属性面板 - 颜色Ttp - 文本颜色标签 */
	@FXML
	private Label colorTextLab;

	/** 属性面板 - 颜色Ttp - 文本颜色 */
	@FXML
	private ColorPicker colorTextPicker;

	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	
    	// 定制Accordion，使得选中的TitledPane永远在最下面
    	accordion.expandedPaneProperty().addListener((ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) -> {
			if (newValue != null) {
				// 交换打开的TitledPane为最后一个
				ObservableList<TitledPane> ttpList = accordion.getPanes(); // 全部TitledPane
				for (TitledPane ttp: ttpList) {
					// 全部初始化
					char originIndex = ttp.getId().charAt(4); // ttpXX
					ttp.setId("ttp" + originIndex + originIndex);
				}
				char curOriginIndex = newValue.getId().charAt(4); // ttpXX
				newValue.setId("ttp" + '4' + curOriginIndex);
				// 重新排序
				ttpList.sort(new Comparator<TitledPane>() {
					@Override
					public int compare(TitledPane o1, TitledPane o2) {
						return o1.getId().compareTo(o2.getId());
					}
		        });
			}
    	});
    	
    	// 所有文本框添加焦点监听事件
    	posXField.focusedProperty().addListener(listener -> { onPosXInputChanged(); });
    	posYField.focusedProperty().addListener(listener -> { onPosYInputChanged(); });
    	posXEndField.focusedProperty().addListener(listener -> { onPosXEndInputChanged(); });
    	posYEndField.focusedProperty().addListener(listener -> { onPosYEndInputChanged(); });
    	sizeXField.focusedProperty().addListener(listener -> { onSizeXLenInputChanged(); });
    	sizeYField.focusedProperty().addListener(listener -> { onSizeYLenInputChanged(); });
    	sizeStrokeField.focusedProperty().addListener(listener -> { onSizeStrokeInputChanged(); });
    	colorFillPicker.setOnAction(e -> { onColorFillInputChanged(); });
    	colorStrokePicker.setOnAction(e -> { onColorStrokeInputChanged(); });
    	colorTextPicker.setOnAction(e -> { onColorTextInputChanged(); });
    }

	/** 根据选中的图形，刷新属性界面的方法 */
    public void refreshPropertiesView() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则显示属性面板，否则不显示
    	if (selector.count() == 1) {
    		accordion.setVisible(true);
    		ANodeWA node = selector.getList().get(0);
    		setDescription(node);
    		setPosition(node);
    		setSize(node);
    		setColor(node);
    	} else {
    		accordion.setVisible(false);
    	}
    }
    
    /** 设置类型描述属性 */
    private void setDescription(ANodeWA node) {
    	descLab.setText(node.getDescription());
    }

    /** 设置位置属性 */
    private void setPosition(ANodeWA node) {
    	if (node.getType() == NodeType.Line) {
    		posXLab.setText("X1:");
    		posYLab.setText("Y1:");
    		posXEndLab.setVisible(true);
    		posXEndLab.setText("X2:");
    		posYEndLab.setVisible(true);
    		posYEndLab.setText("Y2");
    		posXField.setText(String.valueOf(((LineWA)node).getStartX()));
    		posYField.setText(String.valueOf(((LineWA)node).getStartY()));
    		posXEndField.setVisible(true);
    		posXEndField.setText(String.valueOf(((LineWA)node).getEndX()));
    		posYEndField.setVisible(true);
    		posYEndField.setText(String.valueOf(((LineWA)node).getEndY()));
    	}
    	else {
    		posXLab.setText("X:");
    		posYLab.setText("Y:");
    		posXEndLab.setVisible(false);
    		posYEndLab.setVisible(false);
        	posXField.setText(String.valueOf(node.getTranslateX()));
        	posYField.setText(String.valueOf(node.getTranslateY()));
    		posXEndField.setVisible(false);
    		posYEndField.setVisible(false);
    	}
    }

    /** 设置大小属性 */
    private void setSize(ANodeWA node) {
    	if (node.getType() == NodeType.Polygon) {
    		// 只有外接圆半径以及边框宽度
    		sizeXLab.setText("R:");
        	sizeXField.setText(String.valueOf(((PolygonWA)node).getRadius()));
        	sizeYLab.setText("(R为正多边形外接圆半径)");
        	sizeYField.setVisible(false);
        	sizeStrokeField.setText(String.valueOf(node.getStrokeWidth()));
    	}
    	else {
    		sizeXLab.setText("X:");
        	sizeXField.setText(String.valueOf(node.getLengthX()));
        	sizeYLab.setText("Y:");
        	sizeYField.setVisible(true);
        	sizeYField.setText(String.valueOf(node.getLengthY()));
        	sizeStrokeField.setText(String.valueOf(node.getStrokeWidth()));
    	}
    }

    /** 设置颜色属性 */
    private void setColor(ANodeWA node) {
    	colorStrokePicker.setValue(node.getStroke());
    	if (node.getType() == NodeType.Line) {
    		colorFillLab.setVisible(false);
    		colorFillPicker.setVisible(false);
    		colorTextLab.setVisible(false);
    		colorTextPicker.setVisible(false);
    	} 
    	else if (node.getType() == NodeType.Text) {
    		colorFillLab.setVisible(true);
    		colorFillPicker.setVisible(true);
        	colorFillPicker.setValue(node.getFill());
    		colorTextLab.setVisible(true);
    		colorTextPicker.setVisible(true);
        	colorTextPicker.setValue((Color)((TextWA)node).getLabel().getTextFill());
    	}
    	else {
    		colorFillLab.setVisible(true);
    		colorFillPicker.setVisible(true);
        	colorFillPicker.setValue(node.getFill());
    		colorTextLab.setVisible(false);
    		colorTextPicker.setVisible(false);
    	}
    }

    /** 属性输入 - 输入坐标x */
    private void onPosXInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double x = Double.valueOf(posXField.getText());
    		if (x != null) {
    			if (node.getType() == NodeType.Line)
    				((LineWA)node).setStartX(x);
    			else
    				node.setTranslateX(x);
    		}
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    
    
    /** 属性输入 - 输入y坐标 */
    private void onPosYInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double y = Double.valueOf(posYField.getText());
    		if (y != null) {
    			if (node.getType() == NodeType.Line)
    				((LineWA)node).setStartY(y);
    			else 
    				node.setTranslateY(y);
    		}
    	}    	
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    

    /** 属性输入 - 输入坐标xEnd */
    private void onPosXEndInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double xEnd = Double.valueOf(posXEndField.getText());
    		if (node.getType() == NodeType.Line && xEnd != null) 
				((LineWA)node).setEndX(xEnd);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    

    /** 属性输入 - 输入坐标yEnd */
    private void onPosYEndInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double yEnd = Double.valueOf(posYEndField.getText());
    		if (node.getType() == NodeType.Line && yEnd != null) 
				((LineWA)node).setEndY(yEnd);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    

    /** 属性输入 - 输入x方向长度或多边形外接圆半径 */
    private void onSizeXLenInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double xLen = Double.valueOf(sizeXField.getText());
    		if (xLen != null) {
        		if (node.getType() == NodeType.Polygon)
        			((PolygonWA)node).setRadius(xLen);
        		else
        			node.setLengthX(xLen);
    		}
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    

    /** 属性输入 - 输入y方向长度 */
    private void onSizeYLenInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double yLen = Double.valueOf(sizeYField.getText());
    		if (yLen != null) 
				node.setLengthY(yLen);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    

    /** 属性输入 - 输入边框宽度 */
    private void onSizeStrokeInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Double width = Double.valueOf(sizeStrokeField.getText());
    		if (width != null) 
				node.setStrokeWidth(width);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }    
    
    /** 属性输入 - 输入填充颜色 */
    private void onColorFillInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Color fill = colorFillPicker.getValue();
			node.setFill(fill);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }

    /** 属性输入 - 输入边框颜色 */
    private void onColorStrokeInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Color color = colorStrokePicker.getValue();
			node.setStroke(color);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }
    
    /** 属性输入 - 输入文本颜色 */
    private void onColorTextInputChanged() {
    	// 获取当前画布的图形选择器
    	Selector selector = PaneManager.getCurrentPane().getSelector();
    	// 若选中一个对象则对其进行更改
    	if (selector.count() == 1) {
    		ANodeWA node = selector.getList().get(0);
    		Color color = colorTextPicker.getValue();
			((TextWA)node).getLabel().setTextFill(color);
    	}
		// 刷新属性面板
    	this.refreshPropertiesView();
    }
    
}
