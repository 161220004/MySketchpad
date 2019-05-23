package AldebaRain.sketchpad.controllers;

import java.util.Iterator;
import java.util.List;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.State;
import AldebaRain.sketchpad.hierarchy.*;
import AldebaRain.sketchpad.models.product.ANodeWA;
import AldebaRain.sketchpad.selector.Clipboard;
import AldebaRain.sketchpad.selector.Selector;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
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
	
	/** 注入历史面板 */
	@FXML
    private AnchorPane history;

	/** 注入历史面板控制器 */
	@FXML
	private HistoryController historyController;
	
	/** 画布新建功能标签页（Tab） */
	@FXML
	private Tab moreTab;
	
	/** 默认画布 */
	@FXML
	private StackPane defaultPane;
	
	/** 菜单项 - 设置 - 显示拖拽位置提示 */
	@FXML
	private CheckMenuItem showDragPosCMI;

	/** 菜单项 - 设置 - 使用多分支历史记录 */
	@FXML
	private CheckMenuItem useMultiHistoryCMI;
	
	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	
    	// 为默认画布新建图层管理器并设为画布管理器的当前画布
    	LayerManager newPane = new LayerManager(defaultPane);
    	PaneManager.getInstance().add(newPane);
    	PaneManager.getInstance().setCurrentPane(newPane);

    	// 初始化属性面板
    	propertiesController.refreshPropertiesView();
    	
    	// 初始化菜单栏设置选项
    	showDragPosCMI.setSelected(State.showDragAnchorPosTips);
    	showDragPosCMI.setOnAction(e -> {
    		if (showDragPosCMI.isSelected())
    			State.showDragAnchorPosTips = true;
    		else State.showDragAnchorPosTips = false;
    	});
    	useMultiHistoryCMI.setSelected(State.showMultiHistory);
    	useMultiHistoryCMI.setOnAction(e -> {
    		if (useMultiHistoryCMI.isSelected())
    			State.showMultiHistory = true;
    		else State.showMultiHistory = false;
    	});
    	
    	// 添加复制粘贴的快捷键监听；添加图形复选Ctrl的快捷键监听
    	frameWindow.setOnKeyPressed(e -> {
    		if (e.isControlDown()) {
        		// 复制(Ctrl+C)
        		if (e.getCode() == KeyCode.C) {
        			// 获取选中的所有图形
        			Selector selector = PaneManager.getInstance().getCurrentPane().getSelector();
        			Clipboard.getInstance().copy(selector.getNodes());
        		}
        		// 粘贴(Ctrl+V)
        		else if (e.getCode() == KeyCode.V) {
        			Clipboard.getInstance().paste();
    				// 添加到历史记录
    				App.frameController.getHistoryController().saveAsHistory("复制粘贴");
        		}
        		// 撤销(Ctrl+Z)
        		else if (e.getCode() == KeyCode.Z) {
        			historyController.undoToLast();
        		}
        		// 重做(Ctrl+Y)
        		else if (e.getCode() == KeyCode.Y) {
        			historyController.redoToNext();
        		}
    		}
			// 删除(Delete)
    		else if (e.getCode() == KeyCode.DELETE) {
    			Selector selector = PaneManager.getInstance().getCurrentPane().getSelector();
    			PaneManager.getInstance().getCurrentPane().remove(selector.getNodes());
    			selector.removeAll();
    	    	// 刷新属性面板
    	    	propertiesController.refreshPropertiesView();
    		}
    	});
    	
    	// 为添加画布标签添加动作
    	
    }

    /** 获取属性面板控制器 */
	public PropertiesController getPropertiesController() {
		return this.propertiesController;
	}

    /** 获取历史面板控制器 */
	public HistoryController getHistoryController() {
		return this.historyController;
	}

	/** 根据图形的选择情况刷新画布 */
	public void refreshView() {
		// 获取画布上的所有图形
		List<ANodeWA> allNodes = PaneManager.getInstance().getCurrentPane().getAllNodes();
		// 获取选中的所有图形
		Selector selector = PaneManager.getInstance().getCurrentPane().getSelector();
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
