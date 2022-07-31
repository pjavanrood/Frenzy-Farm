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
import model.Map;
import model.WildAnimals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ItemsViewController {

    public static GridPane mainGridPane;
    public static Stage window;
    public static Scene scene;

    public ItemsViewController() {
        mainGridPane = new GridPane();
        mainGridPane.getChildren().clear();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Scene scene = startMainGrid();

        window.setScene(scene);
    }

    public void display() {
        setupProductsAnimals(GameMapController.clickedRow, GameMapController.clickedColumn);

        window.showAndWait();
    }

    public void setupProductsAnimals(int i, int j){
        mainGridPane.getChildren().clear();
        //Setup products
        HashMap<String, Integer> products = Map.productCounter(GameProcessor.gameMap.productions[i][j]);
        int rowCounter = 0;
        int colCounter = 0;
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();

        for (String productName : products.keySet()) {
            HBox hBox = new HBox();
            ImageView imageView = Main.imagesLoader.getImage(productName);
            imageView.setFitHeight(90);
            imageView.setFitWidth(90);
            Label label = new Label();
            label.setFont(new Font("Pixeboy", 26));
            label.setText("X" + products.get(productName));
            imageView.setOnMouseEntered(e -> glowButton(imageView));
            imageView.setOnMouseExited(e -> unGlowButton(imageView));
            imageView.setOnMouseClicked(e -> {
                productClicked(productName);
            });
            if(rowCounter < 7) {
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(label);
                vBox.getChildren().add(hBox);
            }

            else {
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(label);
                vBox1.getChildren().add(hBox);
            }

            if(rowCounter == 6 && products.keySet().size() > 7){
                vBox.setSpacing(10);
                mainGridPane.addColumn(0, vBox);
            }

            rowCounter++;
        }
        if(rowCounter < 8)
        {
            vBox.setSpacing(10);
            mainGridPane.addColumn(0, vBox);
            colCounter = 1;
        }
        if(rowCounter >= 8){
            vBox1.setSpacing(10);
            mainGridPane.addColumn(1, vBox1);
            colCounter = 2;
        }



        HashMap<String, Integer> creatures = Map.creatureCounter(GameProcessor.gameMap.creature[i][j]);
        rowCounter = 0;
        vBox = new VBox();
        vBox1 = new VBox();

        for (String animalName : creatures.keySet()) {
            HBox hBox = new HBox();
            ImageView imageView = Main.imagesLoader.getAnimalImage(animalName);
            imageView.setFitHeight(90);
            imageView.setFitWidth(90);
            Label label = new Label();
            label.setFont(new Font("Pixeboy", 26));
            label.setText("X" + creatures.get(animalName));

            if(WildAnimals.isWildAnimal(animalName) != -1 || animalName.charAt(animalName.length() - 1) == '*'){
                imageView.setOnMouseEntered(e -> glowButton(imageView));
                imageView.setOnMouseExited(e -> unGlowButton(imageView));
                imageView.setOnMouseClicked(e -> {
                    animalClicked(animalName);
                });
            }
            if(rowCounter < 7) {

                hBox.getChildren().add(imageView);
                hBox.getChildren().add(label);
                vBox.getChildren().add(hBox);
            }

            else {
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(label);
                vBox1.getChildren().add(hBox);
            }

            if(rowCounter == 6){
                vBox.setSpacing(10);
                mainGridPane.addColumn(colCounter, vBox);
            }

            rowCounter++;
        }

        if(rowCounter < 8)
        {
            vBox.setSpacing(10);
            mainGridPane.addColumn(colCounter, vBox);
        }
        else {
            vBox1.setSpacing(10);
            mainGridPane.addColumn(colCounter + 1, vBox1);
        }

        mainGridPane.setHgap(50);
        mainGridPane.setAlignment(Pos.CENTER);
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

    public void productClicked(String name){
        GameProcessor.pickupProduct(name, GameMapController.clickedRow, GameMapController.clickedColumn);
        setupProductsAnimals(GameMapController.clickedRow, GameMapController.clickedColumn);
    }

    public void animalClicked(String name){
        if(name.charAt(name.length() - 1) == '*')
            GameProcessor.pickupAnimal(name, GameMapController.clickedRow, GameMapController.clickedColumn);

        else
            GameProcessor.cage(name, GameMapController.clickedRow, GameMapController.clickedColumn);

        setupProductsAnimals(GameMapController.clickedRow, GameMapController.clickedColumn);
    }

    public static Scene startMainGrid(){
        Image bckGroundImage = null;
        try {
            bckGroundImage = new Image(new FileInputStream("assets/ItemsView.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        scene = new Scene(mainGridPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        BackgroundImage backgroundimage = new BackgroundImage(bckGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, false, false, false, false));

        // create Background
        Background background = new Background(backgroundimage);

        // set background
        mainGridPane.setBackground(background);

        return scene;
    }
}

