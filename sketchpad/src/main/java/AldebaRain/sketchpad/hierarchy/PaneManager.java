package AldebaRain.sketchpad.hierarchy;

import java.util.ArrayList;
import java.util.List;

/**
 * 画布管理器，对应于软件的一次运行.<br>
 * 维护一个所有图层管理器（即画布）的列表；
 * 维护当前画布；
 * 采用单例模式（软件的一次运行只有一个画布管理器和一个当前画布）
 * 
 * @see LayerManager
 */
public class PaneManager {

	/** 画布列表 */
	private List<LayerManager> panes;
	
	/** 当前画布 */
	private LayerManager currentPane = null;
	
	/** 单例 */
	private static PaneManager instance = null;
	
	private PaneManager() { 
		panes = new ArrayList<>();
		// TODO: currentPane的初始化
	}

	/** 获取当前画板 */
	public LayerManager getCurrentPane() {
		return currentPane;
	}

	/** 重设当前画板 */
	public void setCurrentPane(LayerManager pane) {
		currentPane = pane;
	}
	
	/** 获取画布列表 */
	public List<LayerManager> getPanes() {
		return panes;
	}
	
	/** 添加画布 */
	public void add(LayerManager pane) {
		panes.add(pane);
	}
	
	/** 获取单例 */
	public static PaneManager getInstance() {
		if (instance == null)
			instance = new PaneManager();
		return instance;
	}

}
