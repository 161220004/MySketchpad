package AldebaRain.sketchpad.controllers;

import java.util.Comparator;

import AldebaRain.sketchpad.manager.PaneManager;
import AldebaRain.sketchpad.manager.Selector;
import AldebaRain.sketchpad.models.product.ANodeWA;
import AldebaRain.sketchpad.models.product.LineWA;
import AldebaRain.sketchpad.models.product.NodeType;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class PropertiesController {

	/** 属性面板 */
	@FXML
	private AnchorPane properties;
	
	/** 属性面板 - 根元件 */
	@FXML
	private Accordion accordion;

	/** 属性面板 - 类型描述Ttp - 标签 */
	@FXML
	private Label discLab;

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

	/** 属性面板 - 大小Ttp - 图形/直线在X方向的长 */
	@FXML
	private TextField sizeXField;

	/** 属性面板 - 大小Ttp - 图形/直线在Y方向的长 */
	@FXML
	private TextField sizeYField;

	/** 属性面板 - 大小Ttp - 图形边框宽度/直线宽度 */
	@FXML
	private TextField sizeStrokeField;

	/** 属性面板 - 颜色Ttp - 图形边框颜色/直线颜色 */
	@FXML
	private ColorPicker colorStrokePicker;

	/** 属性面板 - 颜色Ttp - 图形填充颜色/直线不可见 */
	@FXML
	private ColorPicker colorFillPicker;

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
    }
    
    /** 设置类型描述属性 */
    private void setDescription(ANodeWA node) {
    	discLab.setText(node.getDescription());
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
    	sizeXField.setText(String.valueOf(node.getLengthX()));
    	sizeYField.setText(String.valueOf(node.getLengthY()));
    	sizeStrokeField.setText(String.valueOf(node.getStrokeWidth()));
    }

    /** 设置颜色属性 */
    private void setColor(ANodeWA node) {
    	colorFillPicker.setValue(node.getFill());
    	colorStrokePicker.setValue(node.getStroke());
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
    
}