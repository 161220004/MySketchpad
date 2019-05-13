package AldebaRain.sketchpad.controllers;

import AldebaRain.sketchpad.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class FrameController {

	@FXML 
	private StackPane stackPane;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab tabAdd;
	
    @FXML
    private void initialize() {
    	new CircleWithAnchors(stackPane, 50);
    	new CircleWithAnchors(stackPane, -150, -150, 50);
    }
    
    @FXML
    private void addTab() {
    	System.out.println("Add Tab !");
    }
	
}
