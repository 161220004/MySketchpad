package AldebaRain.sketchpad.manager;

import java.util.List;

/**
 * 列表维护者.<br>
 * 维护一个类型为T的列表
 */
public abstract class AManager<T> {

	/** 所维护的列表 */
	protected List<T> list;
	
	/** 获取列表 */
	public List<T> getList() {
		return list;
	}
	
	/** 判断一个T对象是否在维护的列表内 */
	public boolean contains(T obj) {
		return list.contains(obj);
	}

	/** 判断列表是否为空 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/** 返回列表内T对象的数量 */
	public int count() {
		return list.size();
	}
	
	/** 在列表中新增T对象 */
	public void add(T obj) {
		list.add(obj);
	}

	/** 删除指定T对象 */
	public void remove(T obj) {
		list.remove(obj);
	}
	
	/** 清空列表 */
	public void removeAll() {
		list.removeAll(list);
	}
	
}
