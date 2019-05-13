package AldebaRain.sketchpad;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	/**	“舞台”，顶层容器	*/
	public static Stage stage;
	/**	菜单栏等框架	*/
    private BorderPane frame;

    /** 初始化GUI */
    private void initGUI() {
    	// 获取FrameView
        FXMLLoader frameLoader = new FXMLLoader();
        frameLoader.setLocation(getClass().getClassLoader().getResource("views/FrameView.fxml"));
        try {
			frame = (BorderPane)frameLoader.load();
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
			//primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("img/icon.png")));
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
