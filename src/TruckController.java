import controller.GameProcessor;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class TruckController {
    public BorderPane borderPane;
    public GridPane mainGridPane;
    public Stage window;
    public Scene scene;

    public TruckController() {
        borderPane = new BorderPane();
        mainGridPane = new GridPane();
        borderPane.setCenter(mainGridPane);
        mainGridPane.getChildren().clear();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Scene scene = startMainGrid();

        window.setScene(scene);
    }

    public void display(){
        setupTruck();

        window.show();
    }

    public void setupTruck(){
        mainGridPane.getChildren().clear();
        HashMap<String, Integer> products = Storage.productCounter(GameProcessor.truck.getStoredProducts());
        HashMap<String, Integer> cagedAnimals = Storage.cagedAnimalCounter(GameProcessor.truck.getWildAnimals());
        HashMap<String, Integer> nameStartIndex = new HashMap<>();


        int rowCounter = 0;
        int colCounter = 0;

        for (String s : products.keySet()) {
            nameStartIndex.put(s, mainGridPane.getChildren().size());
            for (int i = 0; i < products.get(s); i++) {
                ImageView imageView = Main.imagesLoader.getImage(s);
                imageView.setFitWidth(70);
                imageView.setFitHeight(70);
                imageView.setOnMouseEntered(e -> {
                    int start = nameStartIndex.get(s);
                    for(int t = 0; t < products.get(s); t++){
                        glowButton((ImageView) mainGridPane.getChildren().get(t + start));
                    }
                });

                imageView.setOnMouseExited(e -> {
                    int start = nameStartIndex.get(s);
                    for(int t = 0; t < products.get(s); t++){
                        unGlowButton((ImageView) mainGridPane.getChildren().get(t + start));
                    }
                });

                imageView.setOnMouseClicked(e -> {
                    GameProcessor.unloadTruckProduct(s);
                    setupTruck();
                });

                if (colCounter == 4){
                    mainGridPane.addRow(rowCounter, imageView);
                    colCounter = 0;
                    rowCounter++;
                }
                else {
                    mainGridPane.addRow(rowCounter, imageView);
                    colCounter++;
                }

            }
        }



        for (String s : cagedAnimals.keySet()) {
            nameStartIndex.put(s, mainGridPane.getChildren().size());
            for (int i = 0; i < cagedAnimals.get(s); i++) {
                ImageView imageView = Main.imagesLoader.getAnimalImage(s);
                imageView.setFitWidth(70);
                imageView.setFitHeight(70);
                imageView.setOnMouseEntered(e -> {
                    int start = nameStartIndex.get(s);
                    for(int t = 0; t < cagedAnimals.get(s); t++){
                        glowButton((ImageView) mainGridPane.getChildren().get(t + start));
                    }
                });

                imageView.setOnMouseExited(e -> {
                    int start = nameStartIndex.get(s);
                    for(int t = 0; t < cagedAnimals.get(s); t++){
                        unGlowButton((ImageView) mainGridPane.getChildren().get(t + start));
                    }
                });

                imageView.setOnMouseClicked(e -> {
                    GameProcessor.unloadTruckWildAnimal(s);
                    setupTruck();
                });

                if (colCounter == 4){
                    mainGridPane.addRow(rowCounter, imageView);
                    colCounter = 0;
                    rowCounter++;
                }
                else {
                    mainGridPane.addRow(rowCounter, imageView);
                    colCounter++;
                }

            }
        }

        mainGridPane.setVgap(15);
        mainGridPane.setAlignment(Pos.CENTER);

    }

    public Scene startMainGrid(){

        Image bckGroundImage = null;
        Image truckGo = null;
        try {
            bckGroundImage = new Image(new FileInputStream("assets/TruckBackGround.png"));
            truckGo = new Image(new FileInputStream("assets/TruckGo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView truckGoView = new ImageView(truckGo);
        truckGoView.setFitWidth(130);
        truckGoView.setFitHeight(63);
        BorderPane rightPane = new BorderPane();
        rightPane.setBottom(truckGoView);
        borderPane.setRight(rightPane);
        truckGoView.setOnMouseEntered(e -> glowButton(truckGoView));
        truckGoView.setOnMouseExited(e -> unGlowButton(truckGoView));
        truckGoView.setOnMouseClicked(e -> GameProcessor.goTruck());


        scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        BackgroundImage backgroundimage = new BackgroundImage(bckGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(840, 201, false, false, false, false));

        // create Background
        Background background = new Background(backgroundimage);

        // set background
        borderPane.setBackground(background);

        return scene;
    }

    public void glowButton(ImageView imageView) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        imageView.setEffect(glow);

    }

    public void unGlowButton(ImageView imageView) {
        Glow glow = new Glow();
        glow.setLevel(0);
        imageView.setEffect(glow);
    }
}
