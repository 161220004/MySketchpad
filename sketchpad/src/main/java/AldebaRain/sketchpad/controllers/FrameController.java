package AldebaRain.sketchpad.controllers;

import AldebaRain.sketchpad.manager.*;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * 主界面控制器.<br>
 * 引用的子界面有：菜单栏，工具箱，属性面板，历史记录面板，层次面板
 * 
 * @see MenuController
 * @see ToolsController
 * @see PropertiesController
 * @see HistoryController
 * @see HierarchyController
 */
public class FrameController {

	/** 主窗口（TabPane） */
	@FXML
	private TabPane mainPane;

	/** 注入工具箱 */
	@FXML
    private AnchorPane tools;
	
	/** 注入工具箱控制器 */
	@FXML
    private ToolsController toolsController;
	
	/** 注入属性面板 */
	@FXML
    private AnchorPane properties;
	
	/** 注入属性面板控制器 */
	@FXML
    private PropertiesController propertiesController;
	
	/** 画布新建功能标签页（Tab） */
	@FXML
	private Tab moreTab;
	
	/** 默认画布 */
	@FXML
	private StackPane defaultPane;

	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	
    	// 为默认画布新建图层管理器并设为画布管理器的当前画布
    	PaneManager.setCurrentPane(new LayerManager(defaultPane));
    	
    	// 为添加画布标签添加动作
    	
    }

    /** 获取属性面板控制器 */
	public PropertiesController getPropertiesController() {
		return this.propertiesController;
	}

}
