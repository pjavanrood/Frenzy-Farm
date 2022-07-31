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


public class StorageController {
    public BorderPane borderPane;
    public GridPane mainGridPane;
    public Stage window;
    public Scene scene;

    public StorageController() {
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
        setupStorage();

        window.show();
    }

    public void setupStorage(){
        mainGridPane.getChildren().clear();
        HashMap<String, Integer> products = Storage.productCounter(GameProcessor.storage.getProducts());
        HashMap<String, Integer> cagedAnimals = Storage.cagedAnimalCounter(GameProcessor.storage.getWildAnimals());
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
                    GameProcessor.loadTruckProduct(s);
                    setupStorage();
                });

                if (colCounter == 9){
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
                    GameProcessor.loadTruckWildAnimal(s);
                    setupStorage();
                });

                if (colCounter == 9){
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



//        for (String s : products.keySet()) {
//            HBox hBox = new HBox();
//            hBox.setAlignment(Pos.CENTER);
//            hBox.setSpacing(10);
//            hBox.setOnMouseEntered(e -> glowButton(e));
//            hBox.setOnMouseExited(e -> unGlowButton(e));
//
//            System.out.println(s + " " + products.get(s));
//
//            for (int i = 0; i < products.get(s); i++) {
//                ImageView imageView = Main.imagesLoader.getImage(s);
//                imageView.setFitWidth(70);
//                imageView.setFitHeight(70);
//
//
//                if(colCounter == 9){
//                    hBox.getChildren().add(imageView);
//                    hBoxes.add(hBox);
//                    hBox = new HBox();
//                    hBox.setSpacing(10);
//                    hBox.setAlignment(Pos.CENTER);
//                    hBox.setOnMouseEntered(e -> glowButton(e));
//                    hBox.setOnMouseExited(e -> unGlowButton(e));
//                    colCounter = 0;
//                    rowCounter++;
//                }
//
//                else{
//                    hBox.getChildren().add(imageView);
//                    colCounter++;
//                }
//            }
//            mainGridPane.addRow(rowCounter, hBox);
//        }
//        HashMap<String, Integer> cagedAnimals = Storage.cagedAnimalCounter(GameProcessor.storage.getWildAnimals());
//
//        for (String s : cagedAnimals.keySet()) {
//            HBox hBox = new HBox();
//            hBox.setAlignment(Pos.CENTER);
//            hBox.setSpacing(10);
//            hBox.setOnMouseEntered(e -> glowButton(e));
//            hBox.setOnMouseExited(e -> unGlowButton(e));
//            System.out.println(s + " " + cagedAnimals.get(s));
//            for (int i = 0; i < cagedAnimals.get(s); i++) {
//                ImageView imageView = Main.imagesLoader.getAnimalImage(s);
//                imageView.setFitWidth(70);
//                imageView.setFitHeight(70);
//
//                if(colCounter == 9){
//                    hBox.getChildren().add(imageView);
//                    hBoxes.add(hBox);
//                    hBox = new HBox();
//                    hBox.setSpacing(10);
//                    hBox.setAlignment(Pos.CENTER);
//                    hBox.setOnMouseEntered(e -> glowButton(e));
//                    hBox.setOnMouseExited(e -> unGlowButton(e));
//                    colCounter = 0;
//                    rowCounter++;
//                }
//
//                else{
//                    hBox.getChildren().add(imageView);
//                    colCounter++;
//                }
//            }
//            mainGridPane.addRow(rowCounter, hBox);
//        }




        mainGridPane.setVgap(15);
        mainGridPane.setAlignment(Pos.CENTER);

    }

    public Scene startMainGrid(){
        BorderPane topBorder = new BorderPane();
        Label label = new Label("Storage");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Pixeboy", 45));
        topBorder.setCenter(label);
        borderPane.setTop(topBorder);

        Image bckGroundImage = null;
        try {
            bckGroundImage = new Image(new FileInputStream("assets/WareHouseBackGround.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        BackgroundImage backgroundimage = new BackgroundImage(bckGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, false, false, false, false));

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
