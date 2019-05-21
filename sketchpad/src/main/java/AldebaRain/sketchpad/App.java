package AldebaRain.sketchpad;

import java.io.IOException;

import AldebaRain.sketchpad.controllers.FrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	/**	“舞台”，顶层容器	*/
	public static Stage stage;
	
	/**	主界面	*/
    private BorderPane frame;

	/**	主界面	*/
    public static FrameController frameController;

    /** 初始化GUI */
    private void initGUI() {
    	// 获取FrameView
        FXMLLoader frameLoader = new FXMLLoader();
        frameLoader.setLocation(getClass().getClassLoader().getResource("views/FrameView.fxml"));
        try {
        	// 获取主界面
			frame = (BorderPane)frameLoader.load();
			// 获取控制器
			frameController = (FrameController)frameLoader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // 显示frame内的场景
        Scene scene = new Scene(frame);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());        
        stage.setScene(scene);
        stage.show();
	}

	@Override
	public void start(Stage primaryStage) {
		// 初始化GUI
		try { 
			primaryStage.setTitle("MySketchpad");
			primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("img/icon.png")));
			primaryStage.setWidth(1200);
			primaryStage.setHeight(900);
			primaryStage.setResizable(false); // 禁止窗口缩放
			stage = primaryStage;
			initGUI();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main( String[] args ) {
		launch(args);
    }
}
