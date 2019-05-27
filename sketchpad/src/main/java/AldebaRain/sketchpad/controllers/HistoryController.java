package AldebaRain.sketchpad.controllers;

import java.util.Iterator;

import AldebaRain.sketchpad.hierarchy.PaneManager;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 历史记录面板
 * 
 * @see FrameController
 */
public class HistoryController {

	/** 历史面板 */
	@FXML
	private AnchorPane history;
	
	/** 树视图 */
	@FXML
	private TreeView<String> historyTree;

	/** 历史记录树的根节点 */
	private TreeItem<String> root;
	
	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	root = new TreeItem<>("历史记录");
    	root.setExpanded(true);
    	historyTree.setRoot(root);
    	// 第一条记录
    	TreeItem<String> item = new TreeItem<>("创建画布");
    	item.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/icon16.png"))));
    	root.getChildren().add(item);
    	
    	// 添加选中某个树节点事件的监听
    	historyTree.getSelectionModel().selectedItemProperty().addListener(listener -> {
        	int id = historyTree.getSelectionModel().getSelectedIndex() - 1; // 选中的id(使其从0开始)
        	// 若不是选中当前节点，回退到该历史节点
        	if (id != PaneManager.getInstance().getCurrentPane().getHistoryIndex())
        		PaneManager.getInstance().getCurrentPane().doHistory(id);
        	// 重设小图标
        	renderTreeGraphic();
    	});
    }
	
    /** 移除index(从0开始)大于等于参数的树节点 */
    public void removeMorethan(int index) {
		Iterator<TreeItem<String>> it = root.getChildren().iterator();
		for (int i = 0; it.hasNext() && i <= index; i++) {
			it.next();
		}
		// 删除后面的
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
    }
    
    /** 刷新显示，为当前历史记录的树节点添加小图标并选中该节点 */
    private void renderTreeGraphic() {
    	for (TreeItem<String> it: root.getChildren()) {
    		it.setGraphic(null);
    	}
		TreeItem<String> item = root.getChildren().get(PaneManager.getInstance().getCurrentPane().getHistoryIndex());
    	item.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/icon16.png"))));
		// 选中该节点（节点id是记录id+1）
		historyTree.getSelectionModel().select(PaneManager.getInstance().getCurrentPane().getHistoryIndex() + 1);
    }
    
    /** 添加到历史记录 */
    public void saveAsHistory(String description) {
    	// 先保存当前图层备份
		PaneManager.getInstance().getCurrentPane().save();
		// 再新建树节点
    	TreeItem<String> item = new TreeItem<>(description);
    	root.getChildren().add(item);
    	renderTreeGraphic();
    }
    
    /** 回退到上一步 */
    public void undoToLast() {
    	// 画布回退
		PaneManager.getInstance().getCurrentPane().undo();
		// 重设小图标
    	renderTreeGraphic();
    }
    
    /** 重做到下一步 */
    public void redoToNext() {
    	// 画布重做
		PaneManager.getInstance().getCurrentPane().redo();
		// 重设小图标
    	renderTreeGraphic();
    }
    
}
