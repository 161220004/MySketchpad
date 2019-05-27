package AldebaRain.sketchpad.models.product;

/**
 * 可供绘制的图形类型.<br>
 * 包括：直线，圆形（椭圆），矩形，正 三角形，正多边形，文本框
 * */
public enum NodeType {
	Line("直线"), Ellipse("椭圆"), Rectangle("矩形"), Triangle("三角形"), Polygon("正多边形"), Text("文本");
	
	private String desc;
	
	private NodeType(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
}
