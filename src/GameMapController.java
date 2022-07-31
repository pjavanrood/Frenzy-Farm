import controller.GameProcessor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameMapController implements Initializable {
    @FXML
    GridPane mainGridPane;
    public static Scene gameScene;
    Image grassImage;
    Image emptyImage;
    public static int clickedRow;
    public static int clickedColumn;
    public ImageView well;
    public ImageView truck;
    public ImageView shop;
    public ImageView storage;
    public BorderPane mainBorderPane;
    public GridPane factoriesGridPane;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //mainGridPane.getRowConstraints().clear();

        try {
            grassImage = new Image(new FileInputStream("assets/Grass.png"));
            emptyImage = new Image(new FileInputStream("assets/Empty.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setGrassMap();
        setFactories();
        setupWellTruck();
    }

    public void setGrassMap(){
        mainGridPane = new GridPane();
        mainGridPane.setPrefWidth(624);
        mainGridPane.setPrefHeight(661);
        mainBorderPane.setCenter(mainGridPane);

        for (int j = 0; j < 6; j++) {
            VBox vBox = new VBox();
            for(int i = 0; i < 6; i++){
                ImageView imageView;

                if(GameProcessor.gameMap.grass[i][j] == 0){
                    imageView = new ImageView(emptyImage);
                }

                else{
                    imageView = new ImageView(grassImage);
                }

                imageView.setFitWidth(Math.ceil(624 / 6));
                imageView.setFitHeight(Math.ceil(661 / 6));

                vBox.getChildren().add(imageView);
                vBox.setAlignment(Pos.TOP_LEFT);

                final int r = i;
                final int c = j;

                imageView.setOnMouseEntered(e -> glowButton(imageView));
                imageView.setOnMouseExited(e -> unGlowButton(imageView));
                imageView.setOnMouseClicked(e -> {

                    if(e.getButton() == MouseButton.SECONDARY) {
                        clickedColumn = c;
                        clickedRow = r;
                        Main.viewController.display();

                        //rightClicked(e);
                        System.out.println(clickedRow + " " + clickedColumn);
                        System.out.println("RightClicked");
                    } else if (e.getButton() == MouseButton.PRIMARY) {
                        clickedColumn = c;
                        clickedRow = r;
                        GameProcessor.plant(c, r);
                        setGrassMap();
                        //leftClicked(e);
                        System.out.println(clickedRow + " " + clickedColumn);
                        System.out.println("LeftClicked");
                    }
                });

            }
            mainGridPane.addColumn(j, vBox);
        }
        mainGridPane.setHgap(0);

    }



    public void fillWell () {//well button
        GameProcessor.fillWell();
        setupWellTruck();
    }

    public void displayTruck () {//truck button
        Main.truckController.display();
        setupWellTruck();
    }

    public void displayShop () {//shop button
        if (GameProcessor.Level == 1) {
            Main.shopController1.display();
        } else if (GameProcessor.Level == 2) {
            Main.shopController2.display();
        } else if (GameProcessor.Level == 3) {
            Main.shopController3.display();
            System.out.println(GameProcessor.level.getTask().getProductsname());
        } else if (GameProcessor.Level == 4) {
            Main.shopController4.display();
        }
        setFactories();
        setGrassMap();
    }

    public void displayStorage () {//storage button
        Main.storageController.display();
    }

    public void displayMenu () {//go back to login & sign up
        Main.window.setScene(Main.loginMenuScene);
    }

    public void displayLevels () {//go back to levels
        Main.window.setScene(Main.levelsPageScene);
    }

    public void displayTasks () {//tasks
        try {
            TasksController.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnCommand () {//turn button
        GameProcessor.turnCommand();
        OutputProcessor.turn();
        setGrassMap();
        setFactories();
        setupWellTruck();
    }


    public void setFactories(){
        int rowNum = 0;
        factoriesGridPane.getChildren().clear();
        for (Factory factory : GameProcessor.factories) {
            BorderPane borderPane = new BorderPane();
            ImageView imageView = Main.imagesLoader.getFactoryImage(factory.getName());
            imageView.setFitWidth(691/6);
            imageView.setFitHeight(691/6);

            imageView.setOnMouseEntered(e -> glowButton(e));
            imageView.setOnMouseExited(e -> unGlowButton(e));
            imageView.setOnMouseClicked(e -> {
                if(e.getButton() == MouseButton.PRIMARY){
                    GameProcessor.factoryWork(factory);
                }
                else if(e.getButton() == MouseButton.SECONDARY){
                    GameProcessor.upgradeFactory(factory);
                }
            });
            borderPane.setCenter(imageView);
            factoriesGridPane.addRow(rowNum, borderPane);
            rowNum++;
        }
        factoriesGridPane.setVgap(10);
        factoriesGridPane.setAlignment(Pos.CENTER);
    }


    public void glowButton(MouseEvent mouseEvent){
        Glow glow = new Glow();
        glow.setLevel(0.5);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
    }

    public void unGlowButton(MouseEvent mouseEvent){
        Glow glow = new Glow();
        glow.setLevel(0);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
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

    public void setupWellTruck(){
        double wellWidth = well.getFitWidth();
        double wellHeight = well.getFitHeight();

        double truckWidth = truck.getFitWidth();
        double truckHeight = truck.getFitHeight();

        if(GameProcessor.well.isBusy()){
            well.setImage(ImagesLoader.wellBWImage);
            well.setFitWidth(wellWidth);
            well.setFitHeight(wellHeight);
        }
        else {
            well.setImage(ImagesLoader.wellImage);
            well.setFitWidth(wellWidth);
            well.setFitHeight(wellHeight);
        }

        if(GameProcessor.truck.isWorking()){
            truck.setImage(ImagesLoader.truckBWImage);
            truck.setFitWidth(truckWidth);
            truck.setFitHeight(truckHeight);
        }
        else {
            truck.setImage(ImagesLoader.truckImage);
            truck.setFitWidth(truckWidth);
            truck.setFitHeight(truckHeight);
        }
    }
}
