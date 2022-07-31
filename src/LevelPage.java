import controller.GameProcessor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelPage implements Initializable {

    public static int levelUnlocked;

    @FXML
    public ImageView returnIcon;

    @FXML
    public ImageView levelOneImage;

    @FXML
    public ImageView levelTwoImage;

    @FXML
    public ImageView levelThreeImage;

    @FXML
    public ImageView levelFourImage;

    @FXML
    public ImageView levelsLogo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        levelUnlocked = GameProcessor.user.getLevelpassed();
        try {
            setLevelIcons();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setLevelIcons() throws FileNotFoundException {
        String fileName = "level Assets/";
        levelsLogo.setImage(new Image(new FileInputStream(fileName + "Levels.png")));
        levelsLogo.setFitHeight(168);
        levelsLogo.setFitWidth(380);

        returnIcon.setImage(new Image(new FileInputStream(fileName + "return.png")));
        returnIcon.setFitHeight(113);
        returnIcon.setFitWidth(70);
        switch (levelUnlocked) {
            case 1: {
                levelOneImage.setImage(new Image(new FileInputStream(fileName + "1.png")));
                levelOneImage.setFitHeight(150);
                levelOneImage.setFitWidth(100);

                levelTwoImage.setImage(new Image(new FileInputStream(fileName + "2L.png")));
                levelTwoImage.setFitHeight(150);
                levelTwoImage.setFitWidth(200);

                levelThreeImage.setImage(new Image(new FileInputStream(fileName + "3L.png")));
                levelThreeImage.setFitHeight(150);
                levelThreeImage.setFitWidth(200);

                levelFourImage.setImage(new Image(new FileInputStream(fileName + "4L.png")));
                levelFourImage.setFitHeight(150);
                levelFourImage.setFitWidth(200);
                break;
            }

            case 2: {
                levelOneImage.setImage(new Image(new FileInputStream(fileName + "1.png")));
                levelOneImage.setFitHeight(150);
                levelOneImage.setFitWidth(100);

                levelTwoImage.setImage(new Image(new FileInputStream(fileName + "2.png")));
                levelTwoImage.setFitHeight(150);
                levelTwoImage.setFitWidth(100);

                levelThreeImage.setImage(new Image(new FileInputStream(fileName + "3L.png")));
                levelThreeImage.setFitHeight(150);
                levelThreeImage.setFitWidth(200);

                levelFourImage.setImage(new Image(new FileInputStream(fileName + "4L.png")));
                levelFourImage.setFitHeight(150);
                levelFourImage.setFitWidth(200);
                break;
            }

            case 3: {
                levelOneImage.setImage(new Image(new FileInputStream(fileName + "1.png")));
                levelOneImage.setFitHeight(150);
                levelOneImage.setFitWidth(100);

                levelTwoImage.setImage(new Image(new FileInputStream(fileName + "2.png")));
                levelTwoImage.setFitHeight(150);
                levelTwoImage.setFitWidth(100);

                levelThreeImage.setImage(new Image(new FileInputStream(fileName + "3.png")));
                levelThreeImage.setFitHeight(150);
                levelThreeImage.setFitWidth(100);

                levelFourImage.setImage(new Image(new FileInputStream(fileName + "4L.png")));
                levelFourImage.setFitHeight(150);
                levelFourImage.setFitWidth(200);
                break;
            }

            case 4: {
                levelOneImage.setImage(new Image(new FileInputStream(fileName + "1.png")));
                levelOneImage.setFitHeight(150);
                levelOneImage.setFitWidth(100);

                levelTwoImage.setImage(new Image(new FileInputStream(fileName + "2.png")));
                levelTwoImage.setFitHeight(150);
                levelTwoImage.setFitWidth(100);

                levelThreeImage.setImage(new Image(new FileInputStream(fileName + "3.png")));
                levelThreeImage.setFitHeight(150);
                levelThreeImage.setFitWidth(100);

                levelFourImage.setImage(new Image(new FileInputStream(fileName + "4.png")));
                levelFourImage.setFitHeight(150);
                levelFourImage.setFitWidth(100);
                break;
            }
        }

    }

    public void levelOneClicked(){
        levelSelected(1);
    }

    public void levelTwoClicked(){
        levelSelected(2);
    }

    public void levelThreeClicked(){
        levelSelected(3);
    }

    public void levelFourClicked(){
        levelSelected(4);
    }

    public void levelSelected(int level){
        if(level > levelUnlocked){
            OutputProcessor.levelIsLocked();
            return;
        }
        GameProcessor.setLevel(level);
        GameProcessor.startLevel();
        GameProcessor.Level = level;
        switch (level) {
            case 1 : {
                Parent shopPage = null;
                try {
                    shopPage = FXMLLoader.load(getClass().getResource("Level1Shop.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.shopController1.shopScene = new Scene(shopPage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            }
            case 2 : {
                Parent shopPage = null;
                try {
                    shopPage = FXMLLoader.load(getClass().getResource("Level2Shop.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.shopController2.shopScene = new Scene(shopPage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            }
            case 3 : {
                Parent shopPage = null;
                try {
                    shopPage = FXMLLoader.load(getClass().getResource("Level3Shop.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.shopController3.shopScene = new Scene(shopPage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            }
            case 4 : {
                Parent shopPage = null;
                try {
                    shopPage = FXMLLoader.load(getClass().getResource("Level4Shop.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.shopController4.shopScene = new Scene(shopPage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            }
        }
        try {
            setupGameMap();
        } catch (Exception e){

        }
    }

    public void returnClicked(){
        Main.window.setScene(Main.loginMenuScene);
    }

    public void glowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);

    }

    public void unGlowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
    }

    public void setupGameMap() throws IOException {
        Parent gamePage = FXMLLoader.load(getClass().getResource("GameMap.fxml"));
        Main.gameMapScene = new Scene(gamePage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        Main.window.setScene(Main.gameMapScene);
    }
}
