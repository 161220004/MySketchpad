package AldebaRain.sketchpad.selector;

import java.util.ArrayList;
import java.util.List;

import AldebaRain.sketchpad.models.product.ANodeWA;

/**
 * 图形选择器.<br>
 * 维护被选中的所有图形列表
 * 
 */
public class Selector {

	/** 选中的图形的列表 */
	private List<ANodeWA> nodes;
	
	public Selector() {
		nodes = new ArrayList<>();
	}
	
	/** 获取选中图形列表 */
	public List<ANodeWA> getNodes() {
		return nodes;
	}
	
	/** 判断一个图形是否被选中 */
	public boolean contains(ANodeWA node) {
		return nodes.contains(node);
	}
	
	/** 计算当前选中图形数量 */
	public int count() {
		return nodes.size();
	}
	
	/** 新增选中图形 */
	public void add(ANodeWA node) {
		nodes.add(node);
	}

	/** 取消某个选中图形 */
	public void remove(ANodeWA node) {
		nodes.remove(node);
	}
	
	/** 取消所有选中图形 */
	public void removeAll() {
		nodes.removeAll(nodes);
	}
	
	/** 取消此前选定的图形并选择新的图形 */
	public void change(ANodeWA node) {
		this.removeAll();
		this.add(node);
	}
	
}
