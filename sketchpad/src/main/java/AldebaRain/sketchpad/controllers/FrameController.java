package AldebaRain.sketchpad.controllers;

import java.util.Iterator;
import java.util.List;

import AldebaRain.sketchpad.manager.*;
import AldebaRain.sketchpad.models.product.ANodeWA;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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

	/** 主界面 */
	@FXML
	private BorderPane frameWindow;
	
	/** 画布窗口 */
	@FXML
	private TabPane paneTabManager;

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

    	// 初始化属性面板
    	propertiesController.refreshPropertiesView();
    	
    	// 添加复制粘贴的快捷键监听；添加图形复选Ctrl的快捷键监听
    	frameWindow.setOnKeyPressed(e -> {
    		if (e.isControlDown()) {
        		// 复制(Ctrl+C)
        		if (e.getCode() == KeyCode.C) {
        			// 获取选中的所有图形
        			Selector selector = PaneManager.getCurrentPane().getSelector();
        			Clipboard.getInstance().copy(selector.getList());
        		}
        		// 粘贴(Ctrl+V)
        		else if (e.getCode() == KeyCode.V) {
        			Clipboard.getInstance().paste();
        		}
    		}
    	});
    	
    	// 为添加画布标签添加动作
    	
    }

    /** 获取属性面板控制器 */
	public PropertiesController getPropertiesController() {
		return this.propertiesController;
	}

	/** 根据图形的选择情况刷新画布 */
	public void refreshView() {
		// 获取画布上的所有图形
		List<ANodeWA> allNodes = PaneManager.getCurrentPane().getAllNodes();
		// 获取选中的所有图形
		Selector selector = PaneManager.getCurrentPane().getSelector();
		Iterator<ANodeWA> it = allNodes.iterator();
		while (it.hasNext()) {
			ANodeWA node = it.next();
			if (selector.contains(node)) // 被选中
				node.showAnchors();
			else // 未选中
				node.hideAnchors();
		}
	}

}
