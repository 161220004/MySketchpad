package AldebaRain.sketchpad.manager;

import java.util.ArrayList;
import java.util.List;

import AldebaRain.sketchpad.models.product.ANodeWA;
import javafx.scene.layout.Pane;

/**
 * 图层管理器，对应于UI的一个画板或者说标签页.<br>
 * 维护一个所有图层的列表；
 * 维护一个图形选择器；
 * 一个图层管理器对应着一个画板和一组选中的图形
 * 
 * @see Layer
 */
public class LayerManager extends AManager<Layer> {
	
	/** 当前图层 */
	private Layer currentLayer;
	
	/** 本画布 */
	private Pane pane;
	
	/** 本画布的图形选择器 */
	private Selector selector;
	
	/** 构造函数，为画布Pane添加图层管理器 */
	public LayerManager(Pane pane) {
		this.pane = pane;
		list = new ArrayList<Layer>();
		currentLayer = new Layer(pane);
		this.add(currentLayer);
		selector = new Selector();
	}

	/** 获取画布的图形选择器 */
	public Selector getSelector() {
		return selector;
	}

	/** 获取当前选中的图层 */
	public Layer getCurrentLayer() {
		return currentLayer;
	}

	/** 重设当前选中的图层 */
	public void setCurrentLayer(Layer currentLayer) {
		this.currentLayer = currentLayer;
	}

	/** 获取一个图形所在的图层 */
	public Layer getLayer(ANodeWA node) {
		for (Layer layer: list) {
			if (layer.contains(node))
				return layer;
		}
		return null;
	}

	/** 获取画布所有图层的所有图形列表 */
	public List<ANodeWA> getAllNodes() {
		List<ANodeWA> nodes = new ArrayList<>();
		for (Layer layer: list) {
			nodes.addAll(layer.getList());
		}
		return nodes;
	}
	
	/** 图层列表不能为空，currentLayer不能为null */
	@Override
	public void remove(Layer obj) {
		super.remove(obj);
		if (isEmpty()) {
			currentLayer = new Layer(pane);
			this.add(currentLayer);
		}
		else if (obj == currentLayer) {
			currentLayer = list.get(0); // 默认当前图层改为第一个图层
		}
	}
	
	/** 图层列表不能为空，currentLayer不能为null */
	@Override
	public void removeAll() {
		super.removeAll();
		currentLayer = new Layer(pane);
		this.add(currentLayer);
	}
	
}
