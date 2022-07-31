import controller.GameProcessor;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TasksController {
    public Label messagelabel;
    public static Button OKbutton;
    public Stage window;

    public static void display() throws IOException {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Tasks");
        window.setMinWidth(250);

        String message = "\n\n\n\n\n";

        for (int i = 0; i < GameProcessor.level.getTask().getProductsname().size(); i++) {
            message += GameProcessor.level.getTask().getProductsname().get(i);
            if (GameProcessor.taskproductsamount.size() == 0) {
                message += ": 0/";
            } else if (i >= GameProcessor.taskproductsamount.size()) {
                message += ": 0/";
            } else {
                message += ": " + GameProcessor.taskproductsamount.get(i) + "/";
            }
            message += GameProcessor.level.getTask().getAmounts().get(i) + "\n";
        }
        message += "Coins: " + GameProcessor.coins + "/" + GameProcessor.level.getTask().getCoin_amount_goal() + "\n";
        message += "Reward time: " + GameProcessor.level.getRewardtime() + "\n";
        message += "Reward amount: " + GameProcessor.level.getReward_coin() + "\n";
        message += "Game over time: " + GameProcessor.level.getMaxtime();

        Label label = new Label();
        label.setText(message);
        label.setFont(new Font("Pixeboy", 32));
        label.setTextFill(Color.web("#f7611b"));
        label.setAlignment(Pos.CENTER);

        final ImageView okImage = new ImageView(new Image(new FileInputStream("src/assets/OK.png")));
        okImage.setFitWidth(115);
        okImage.setFitHeight(76);

        okImage.setOnMouseEntered(e -> glowButton(okImage));
        okImage.setOnMouseExited(e -> unGlowButton(okImage));
        okImage.setOnMouseClicked(e -> window.close());

        Image backGroundImage = null;
        try {
            backGroundImage = new Image(new FileInputStream("src/assets/Scroll.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundimage = new BackgroundImage(backGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(500, 750, false, false, false, false));
        Background background = new Background(backgroundimage);


        VBox layout = new VBox(150);
        layout.getChildren().addAll(label, okImage);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setBackground(background);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout, 500, 750);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void glowButton(ImageView imageView) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        imageView.setEffect(glow);
    }

    public static void unGlowButton(ImageView imageView) {
        Glow glow = new Glow();
        glow.setLevel(0);
        imageView.setEffect(glow);
    }

    public void okButtonClicked(){
        window.close();
    }
}
