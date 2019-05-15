package AldebaRain.sketchpad.controllers;

import AldebaRain.sketchpad.models.factory.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FrameController {

	/** 画布 */
	@FXML 
	private StackPane stackPane;
	
	/** 工具箱 - 直线绘制*/
	@FXML
	private Button btnLineTool;

	/** 工具箱 - 椭圆绘制 */
	@FXML
	private Button btnOvalTool;

	/** 工具箱 - 长方形绘制 */
	@FXML
	private Button btnRectTool;

	/** 工具箱 - 三角形绘制 */
	@FXML
	private Button btnTriTool;
	
	/** 工具箱 - 多边形绘制 */
	@FXML
	private Button btnPolyTool;

	/** 自动初始化调用 */
    @FXML
    private void initialize() {
    	
    	// 工具栏按钮加上图标
    	Image lineImg = new Image(getClass().getClassLoader().getResourceAsStream("img/line_icon.png"));
    	btnLineTool.setGraphic(new ImageView(lineImg));
    	Image ovalImg = new Image(getClass().getClassLoader().getResourceAsStream("img/oval_icon.png"));
    	btnOvalTool.setGraphic(new ImageView(ovalImg));
    	Image rectImg = new Image(getClass().getClassLoader().getResourceAsStream("img/rect_icon.png"));
    	btnRectTool.setGraphic(new ImageView(rectImg));
    	Image triImg = new Image(getClass().getClassLoader().getResourceAsStream("img/tri_icon.png"));
    	btnTriTool.setGraphic(new ImageView(triImg));
    	Image polyImg = new Image(getClass().getClassLoader().getResourceAsStream("img/poly_icon.png"));
    	btnPolyTool.setGraphic(new ImageView(polyImg));
    	
    }
    
    /** 点击直线工具触发 */
    @FXML
    private void onClickLineTool() {
    	Line line = new Line();
    	IPainter linePainter = new LinePainter();
    	linePainter.paint(line, stackPane);
    }

    /** 点击椭圆工具触发 */
    @FXML
    private void onClickOvalTool() {
    	Circle circle = new Circle();
    	IPainter circlePainter = new CirclePainter();
    	circlePainter.paint(circle, 120, 120, stackPane);
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
