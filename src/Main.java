import controller.GameProcessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application {

    public static MediaPlayer mediaPlayer;
    public static Stage window;
    public static Scene loginMenuScene;
    public static Scene levelsPageScene;
    public static Scene gameMapScene;
    public static Scene exitBoxScene;
    public static Scene alertBoxScene;
    public static Scene shopScene;
    public static ItemsViewController viewController;
    public static StorageController storageController;
    public static TruckController truckController;
    public static ShopController1 shopController1;
    public static ShopController2 shopController2;
    public static ShopController3 shopController3;
    public static ShopController4 shopController4;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 723;
    public static GameProcessor gameProcessor;
    public static ImagesLoader imagesLoader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameProcessor = new GameProcessor();
        imagesLoader = new ImagesLoader();
        viewController = new ItemsViewController();
        storageController = new StorageController();
        truckController = new TruckController();
        shopController1 = new ShopController1();
        shopController2 = new ShopController2();
        shopController3 = new ShopController3();
        shopController4 = new ShopController4();

        //music("assets/Music.mp3");

        OutputProcessor outputProcessor = new OutputProcessor();
        window = primaryStage;
        setUpLoginScene();

        window.setTitle("Frenzy Farm");
        window.setScene(loginMenuScene);
        window.show();
    }

    public void setUpLoginScene() throws IOException {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        Main.loginMenuScene = new Scene(loginMenu, WINDOW_WIDTH, WINDOW_HEIGHT);
        Main.window.setScene(Main.loginMenuScene);
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void music(String url) throws MalformedURLException {
        File mediaFile = new File(url);

        Media media = new Media(mediaFile.toURI().toURL().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.play();
    }
}
