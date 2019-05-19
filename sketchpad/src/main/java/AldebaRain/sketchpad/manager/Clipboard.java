package AldebaRain.sketchpad.manager;

import java.util.ArrayList;
import java.util.List;

import AldebaRain.sketchpad.models.product.ANodeWA;

/**
 * 剪贴板.<br>
 * 维护被复制的所有图形列表；
 * 采用单例模式（软件的一次运行只有一个剪贴板）
 * 
 * @see AManager
 */
public class Clipboard extends AManager<ANodeWA> {

	/** 单例 */
	private static Clipboard instance = null;
	
	private Clipboard() {
		list = new ArrayList<ANodeWA>();
	}
	
	/** 复制操作，将当前画布的所有被选中图形深克隆到剪贴板的图形列表 */
	public void copy(List<ANodeWA> nodes) {
		this.removeAll();
		for (ANodeWA node: nodes) {
			this.add(node.clone());
		}
	}
	
	/** 粘贴操作，将剪贴板中的所有图形绘制到当前画布的当前图层 */
	public void paste() {
		for (ANodeWA node: list) {
			PaneManager.getCurrentPane().getCurrentLayer().add(node);
		}
	}
	
	/** 获取单例 */
	public static Clipboard getInstance() {
		if (instance == null)
			instance = new Clipboard();
		return instance;
	}
	
}
