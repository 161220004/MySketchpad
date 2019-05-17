package AldebaRain.sketchpad.manager;

import java.util.ArrayList;

import AldebaRain.sketchpad.models.product.ANodeWA;

/**
 * 图形选择器.<br>
 * 维护被选中的所有图形列表
 * 
 * @see AManager
 */
public class Selector extends AManager<ANodeWA> {

	public Selector() {
		list = new ArrayList<ANodeWA>();
	}
	
	/** 取消此前选定的图形并选择新的图形 */
	public void change(ANodeWA node) {
		this.removeAll();
		this.add(node);
	}
	
}
