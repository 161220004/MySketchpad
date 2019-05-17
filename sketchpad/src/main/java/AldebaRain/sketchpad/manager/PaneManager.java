package AldebaRain.sketchpad.manager;

import java.util.ArrayList;

/**
 * 画板管理器，对应于软件的一次运行.<br>
 * 维护一个所有图层管理器（即画板）的列表；
 * 维护当前画板；
 * 采用单例模式（软件的一次运行只有一个画板管理器和一个当前画板）
 * 
 * @see LayerManager
 */
public class PaneManager extends AManager<LayerManager> {

	/** 当前画板（软件的一次运行仅一个当前画板） */
	private static LayerManager currentPane = null;
	
	/** 单例 */
	private static PaneManager instance = null;
	
	private PaneManager() { 
		list = new ArrayList<LayerManager>();
		// TODO: currentPane的初始化
		
	}

	/** 获取当前画板 */
	public static LayerManager getCurrentPane() {
		return currentPane;
	}

	/** 重设当前画板 */
	public static void setCurrentPane(LayerManager pane) {
		currentPane = pane;
	}
	
	/** 获取单例 */
	public static PaneManager getInstance() {
		if (instance == null)
			instance = new PaneManager();
		return instance;
	}

}
