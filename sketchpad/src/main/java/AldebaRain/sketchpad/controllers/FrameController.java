package AldebaRain.sketchpad.controllers;

import AldebaRain.sketchpad.models.factory.CircleWAPainter;
import AldebaRain.sketchpad.models.factory.IShapeWAPainter;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class FrameController {

	@FXML 
	private StackPane stackPane;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab tabAdd;
	
    @FXML
    private void initialize() {
    	Circle circle1 = new Circle(50);
    	Circle circle2 = new Circle(80);
    	IShapeWAPainter circlePainter = new CircleWAPainter();
    	circlePainter.paint(circle1, stackPane);
    	circlePainter.paint(circle2, stackPane, -150, -150);
    }
    
    @FXML
    private void addTab() {
    	System.out.println("Add Tab !");
    }
	
}
