package AldebaRain.sketchpad.memento;

import java.util.ArrayList;
import java.util.List;

import AldebaRain.sketchpad.hierarchy.Layer;
import AldebaRain.sketchpad.models.product.ANodeWA;

/**
 * 画布备忘录，备份整个画布.<br>
 * 备忘录模式，用于多步撤销
 * 
 * @see MementoManager
 * @see LayerManager
 */
public class PaneMemento {

	/** 备份内容：该画布的所有图层 */
	private List<Layer> layers;
	
	/** 构造函数，获取参数列表的备份 */
	public PaneMemento(List<Layer> list) {
		// 初始化
		layers = new ArrayList<>();
		for (Layer item: list) {
			List<ANodeWA> nodelist = item.getNodes(); // 原有图层
			List<ANodeWA> nodes = new ArrayList<>(); // 克隆的图层
			for (ANodeWA node: nodelist) // 开始克隆
				nodes.add(node.clone());
			layers.add(new Layer(nodes));
		}
	}
	
	/** 获取当前备份内容 */
	public List<Layer> getLayers() {
		return layers;
	}
	
}
