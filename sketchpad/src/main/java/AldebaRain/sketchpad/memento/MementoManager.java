package AldebaRain.sketchpad.memento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import AldebaRain.sketchpad.App;
import AldebaRain.sketchpad.State;

public class MementoManager {

	/** 全部历史记录 */
	private List<PaneMemento> mementos;
	
	/** 当前所在记录编号 */
	private int index;
	
	public MementoManager(PaneMemento memento) {
		mementos = new ArrayList<>();
		// 添加最初备份（空画布）
		mementos.add(memento);
		index = 0;
	}
	
	/** 获取当前记录编码 */
	public int getIndex() {
		return index;
	}
	
	/** 获取全部历史记录 */
	public List<PaneMemento> getAll() {
		return mementos;
	}
	
	/** 获取上一个编号的记录 */
	public PaneMemento getLast() {
		if (index <= 0) // 已经是最初的了
			return null;
		index -= 1;
		return mementos.get(index);
	}
	
	/** 获取下一个编号的记录 */
	public PaneMemento getNext() {
		if (index >= mementos.size() - 1) // 已经是最后的了
			return null;
		index += 1;
		return mementos.get(index);
	}
	
	/** 获取某一个编号的记录 */
	public PaneMemento getHistory(int id) {
		index = id;
		return mementos.get(index);
	}
	
	/** 保存一条记录 */
	public void saveMemento(PaneMemento memento) {
		// 若显示多分支历史记录，直接添加
		if (State.showMultiHistory) {
			mementos.add(memento);
			index = mementos.size() - 1;
		}
		// 若显示单分支历史记录，需删除后面的部分
		else {
			// 若当前是最后一条记录，则直接添加
			if (index == mementos.size() - 1)
				mementos.add(memento);
			// 若当前在之前的历史记录，则先删除后面的记录，再添加
			else {
				Iterator<PaneMemento> it = mementos.iterator();
				for (int i = 0; it.hasNext() && i <= index; i++) {
					it.next();
				}
				// 删除后面的
				while (it.hasNext()) {
					it.next();
					it.remove();
				}
				App.frameController.getHistoryController().removeMorethan(index);
				mementos.add(memento);
			}
			index += 1;
		}
	}
	
}
