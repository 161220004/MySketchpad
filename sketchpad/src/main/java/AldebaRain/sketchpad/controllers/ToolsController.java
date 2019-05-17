package AldebaRain.sketchpad.controllers;

import AldebaRain.sketchpad.models.factory.*;
import AldebaRain.sketchpad.prefabs.Default;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ToolsController {

	/** 工具箱 */
	@FXML
	private AnchorPane tools;
	
	/** 工具箱 - 直线绘制*/
	@FXML
	private Button lineToolBtn;

	/** 工具箱 - 椭圆绘制 */
	@FXML
	private Button ovalToolBtn;

	/** 工具箱 - 长方形绘制 */
	@FXML
	private Button rectToolBtn;

	/** 工具箱 - 三角形绘制 */
	@FXML
	private Button triToolBtn;
	
	/** 工具箱 - 多边形绘制 */
	@FXML
	private Button polyToolBtn;

	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	
    	// 为工具栏按钮添加图标
    	Image lineImg = new Image(getClass().getClassLoader().getResourceAsStream("img/line_icon.png"));
    	lineToolBtn.setGraphic(new ImageView(lineImg));
    	Image ovalImg = new Image(getClass().getClassLoader().getResourceAsStream("img/oval_icon.png"));
    	ovalToolBtn.setGraphic(new ImageView(ovalImg));
    	Image rectImg = new Image(getClass().getClassLoader().getResourceAsStream("img/rect_icon.png"));
    	rectToolBtn.setGraphic(new ImageView(rectImg));
    	Image triImg = new Image(getClass().getClassLoader().getResourceAsStream("img/tri_icon.png"));
    	triToolBtn.setGraphic(new ImageView(triImg));
    	Image polyImg = new Image(getClass().getClassLoader().getResourceAsStream("img/poly_icon.png"));
    	polyToolBtn.setGraphic(new ImageView(polyImg));
    	
    }

    /** 点击直线工具触发 */
    @FXML
    private void onClickLineTool() {
    	IPainter linePainter = new LinePainter();
    	linePainter.paint(Default.lineStartX, Default.lineStartY, Default.lineEndX, Default.lineEndY);
    }

    /** 点击椭圆工具触发 */
    @FXML
    private void onClickOvalTool() {
    	IPainter circlePainter = new CirclePainter();
    	circlePainter.paint(Default.circleOX, Default.circleOY, 2 * Default.circleRadius, 2 * Default.circleRadius);
    }

    /** 点击长方形工具触发 */
    @FXML
    private void onClickRectTool() {
    	
    }

    /** 点击三角形工具触发 */
    @FXML
    private void onClickTriTool() {
    	
    }

    /** 点击多边形工具触发 */
    @FXML
    private void onClickPolyTool() {
    	
    }

}
