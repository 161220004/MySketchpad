package AldebaRain.sketchpad.models;

/** 标识锚点位置，以区分拖拽时的行为 */
public enum AnchorID {
	C(0, 0), L(-1, 0), R(1, 0), U(0, -1), D(0, 1), 
	LU(-1, -1), LD(-1, 1), RU(1, -1), RD(1, 1);
	
	private final int xDir;
	private final int yDir;
	
	private AnchorID(int xDir, int yDir) {
		this.xDir = xDir;
		this.yDir = yDir;
	}
	
	public int getXDir() {
		return xDir;
	}

	public int getYDir() {
		return yDir;
	}
	
	/** 判定边锚点 */
	public boolean isEdge() {
		return this == L || this == R || this == U || this == D;
	}

	/** 判定水平边锚点 */
	public boolean isHorizontal() {
		return this == L || this == R;
	}

	/** 判定竖直边锚点 */
	public boolean isVertical() {
		return this == U || this == D;
	}
	
	/** 判定角锚点 */
	public boolean isCorner() {
		return this == LU || this == LD || this == RU || this == RD;
	}
	
	/** 对于本锚点，判定aid是否位置相对.<br>
	 * 边锚点的相对点是对边上的3个锚点；
	 * 角锚点的相对点是对角1个锚点 */
	public boolean isOpposite(AnchorID aid) {
		if (this == C || aid == C || this == aid)
			return false;
		if (this.isCorner())
			return (this.xDir + aid.xDir == 0) && (this.yDir + aid.yDir == 0);
		else if (this.isHorizontal())
			return (this.xDir + aid.xDir == 0);
		else
			return (this.yDir + aid.yDir == 0);
	}
	
	/** 对于本边锚点，判定aid是否在相同的边上 */
	public boolean isSide(AnchorID aid) {
		if (this.xDir != 0)
			return isSideVertical(aid);
		else if (this.yDir != 0)
			return isSideHorizontal(aid);
		else 
			return false;
	}
	
	/** 对于本角锚点，判定aid是否在水平的边上 */
	public boolean isSideHorizontal(AnchorID aid) {
		return (this.yDir == aid.yDir);
	}
	
	/** 对于本角锚点，判定aid是否在竖直的边上 */
	public boolean isSideVertical(AnchorID aid) {
		return (this.xDir == aid.xDir);
	}
	
	/** 对于本角锚点，判定aid是否在对角点的水平的边上 */
	public boolean isOppositeHorizontal(AnchorID aid) {
		return (this.yDir + aid.yDir == 0);
	}
	
	/** 对于本角锚点，判定aid是否在对角点的竖直的边上 */
	public boolean isOppositeVertical(AnchorID aid) {
		return (this.xDir + aid.xDir == 0);
	}
}
