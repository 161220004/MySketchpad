package AldebaRain.sketchpad.hierarchy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import AldebaRain.sketchpad.models.product.ANodeWA;
import javafx.scene.layout.Pane;

/**
 * 图层，即图形管理.<br>
 * 仅仅是一组图形，不对应UI的结构
 * 维护一个图层上的所有图形列表
 * 
 * @see AManager
 */
public class Layer {

	/** 图形列表 */
	private List<ANodeWA> nodes;
	
	/** 构造函数 - 创建一个空图层 */
	public Layer() {
		nodes = new ArrayList<>();
	}

	/** 构造函数 - 创建一个包含nodes的图层 */
	public Layer(List<ANodeWA> nodelist) {
		nodes = new ArrayList<>();
		nodes.addAll(nodelist);
	}
	
	/** 获取图形列表 */
	public List<ANodeWA> getNodes() {
		return nodes;
	}
	
	/** 从图层移除图形 */
	public void removeFromPane(ANodeWA node, Pane pane) {
		node.removeFromPane(pane);
		nodes.remove(node);
	}

	/** 从图层移除全部图形 */
	public void removeAllFromPane(Pane pane) {
		Iterator<ANodeWA> it = nodes.iterator();
		while (it.hasNext()) {
			it.next().removeFromPane(pane);
		}
		nodes.removeAll(nodes);
	}
	
	/** 图层刷新显示 */
	public void render(Pane pane) {
		for (ANodeWA node: nodes) {
			node.removeFromPane(pane);
			node.addtoPane(pane);
		}
	}
}
