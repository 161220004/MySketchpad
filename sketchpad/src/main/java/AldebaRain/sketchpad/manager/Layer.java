package AldebaRain.sketchpad.manager;

import java.util.ArrayList;
import java.util.Iterator;

import AldebaRain.sketchpad.models.product.ANodeWA;
import javafx.scene.layout.Pane;

/**
 * 图层，即图形管理.<br>
 * 仅仅是一组图形，不对应UI的结构
 * 维护一个图层上的所有图形列表
 * 
 * @see AManager
 */
public class Layer extends AManager<ANodeWA> {

	/** 图层所在的画板 */
	private Pane pane; 
	
	public Layer(Pane pane) {
		list = new ArrayList<ANodeWA>();
		this.pane = pane;
	}
	
	@Override
	public void add(ANodeWA node) {
			super.add(node);
			// 将图形显示在图层上
			node.addtoPane(pane);
		}
	
	@Override
	public void remove(ANodeWA node) {
		super.remove(node);
		// 从图层移除图形
		node.removeFromPane(pane);
	}
	
	@Override
	public void removeAll() {
		super.removeAll();
		Iterator<ANodeWA> it = list.iterator();
			it.next().removeFromPane(pane);
	}
	
}
