package AldebaRain.sketchpad.hierarchy;

import java.util.ArrayList;
import java.util.List;

import AldebaRain.sketchpad.memento.MementoManager;
import AldebaRain.sketchpad.memento.PaneMemento;
import AldebaRain.sketchpad.models.product.ANodeWA;
import AldebaRain.sketchpad.selector.Selector;
import javafx.scene.layout.Pane;

/**
 * 图层管理器，对应于UI的一个画板或者说标签页.<br>
 * 维护一个所有图层的列表；
 * 维护一个图形选择器；
 * 一个图层管理器对应着一个画板和一组选中的图形；
 * 采用备忘录模式（一个画布保存一份历史记录）
 * 
 * @see Layer
 * @see Selector
 * @see MementoManager
 * @see PaneMemento
 */
public class LayerManager {
	
	/** 图层列表 */
	private List<Layer> layers;
	
	/** 当前图层 */
	private Layer currentLayer;
	
	/** 本画布 */
	private Pane pane;
	
	/** 本画布的图形选择器 */
	private Selector selector;
	
	/** 本画布的历史记录 */
	private MementoManager mementoManager;
	
	/** 构造函数，为画布Pane添加图层管理器，图形选择器和历史记录 */
	public LayerManager(Pane pane) {
		this.pane = pane;
		layers = new ArrayList<>();
		currentLayer = new Layer();
		layers.add(currentLayer);
		selector = new Selector();
		mementoManager = new MementoManager(new PaneMemento(layers));
	}

	/** 获取全部图层 */
	public List<Layer> getLayers() {
		return layers;
	}
	
	/** 获取画布的图形选择器 */
	public Selector getSelector() {
		return selector;
	}
	
	/** 获取当前画布 */
	public Pane getPane() {
		return pane;
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
		for (Layer layer: layers) {
			if (layer.getNodes().contains(node))
				return layer;
		}
		return null;
	}

	/** 获取画布所有图层的所有图形列表 */
	public List<ANodeWA> getAllNodes() {
		List<ANodeWA> nodes = new ArrayList<>();
		for (Layer layer: layers) {
			nodes.addAll(layer.getNodes());
		}
		return nodes;
	}

	/** 在画布的当前图层绘制某个图形 */
	public void add(ANodeWA node) {
		currentLayer.getNodes().add(node);
		node.addtoPane(pane);
	}

	/** 从画布移除某个图形 */
	public void remove(ANodeWA node) {
		for (Layer layer: layers)
			layer.removeFromPane(node, pane);
	}

	/** 从画布移除一组图形 */
	public void remove(List<ANodeWA> nodes) {
		for (Layer layer: layers) {
			for (ANodeWA node: nodes)
				layer.removeFromPane(node, pane);
		}
	}

	/** 图层列表不能为空，currentLayer不能为null */
	public void remove(Layer layer) {
		// 先移除所有该图层的图形
		layer.removeAllFromPane(pane);
		layers.remove(layer);
		if (layers.isEmpty()) {
			currentLayer = new Layer();
			layers.add(currentLayer);
		}
		else if (layer == currentLayer) {
			currentLayer = layers.get(0); // 默认当前图层改为第一个图层
		}
	}
	
	/** 图层列表不能为空，currentLayer不能为null */
	public void removeAll() {
		// 先移除所有图层的所有图形
		for (Layer layer: layers)
			layer.removeAllFromPane(pane);
		layers.removeAll(layers);
		currentLayer = new Layer();
		layers.add(currentLayer);
	}
	
	/** 从画布的当前图层移除所有图形 */
	public void removeAllFromPane() {
		currentLayer.removeAllFromPane(pane);
	}
	
	/** 克隆当前画布的所有图形为Memento */
	private PaneMemento saveAsMemento() {
		PaneMemento newMemento = new PaneMemento(layers);
		return newMemento;
	}
	
	/** 恢复当前画布为Memento */
	private void reloadToPane(PaneMemento memento) {
		// 先清空所有画布列表并移除对应图形
		for (Layer layer: layers)
			layer.removeAllFromPane(pane);
		layers.removeAll(layers);
		// 再清空选择器
		selector.removeAll();
		/* 再把Memento加载到当前画布，
		 * 此处注意，应再给Memento做一个备份加载上去而不是直接把原Memento地址加载上去，
		* 否则在历史记录的基础上修改的话，会导致原记录改变 */
		layers.addAll((new PaneMemento(memento.getLayers())).getLayers());
		// 刷新当前画布显示
		for (Layer layer: layers)
			layer.render(pane);
		// 重定义当前图层
		currentLayer = layers.get(0);
	}
	
	/** 获取当前历史记录Index */
	public int getHistoryIndex() {
		return mementoManager.getIndex();
	}
	
	/** 保存到历史记录 */
	public void save() {
		mementoManager.saveMemento(this.saveAsMemento());
	}
	
	/** 恢复上一次操作 */
	public void undo() {
		PaneMemento lastPane = mementoManager.getLast();
		if (lastPane != null)
			this.reloadToPane(lastPane);
	}
	
	/** 重做下一个操作 */
	public void redo() {
		PaneMemento nextPane = mementoManager.getNext();
		if (nextPane != null)
			this.reloadToPane(nextPane);
	}
	
	/** 恢复到某一条历史记录 */
	public void doHistory(int id) {
		this.reloadToPane(mementoManager.getHistory(id));
	}
	
}
