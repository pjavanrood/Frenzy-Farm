import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class AlertBox {

    public static Label messagelabel;
    public static Button OKbutton;
    public static Stage window;

    public AlertBox() {
    }

    public static void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        label.setFont(new Font("Pixeboy", 32));
        label.setTextFill(Color.web("#f7611b"));
        Image okimage = null;
        try {
            okimage = new Image(new FileInputStream("src/assets/OK.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ImageView okImage = new ImageView(okimage);

        okImage.setFitWidth(115);
        okImage.setFitHeight(76);

        okImage.setOnMouseEntered(e -> glowButton(okImage));
        okImage.setOnMouseExited(e -> unGlowButton(okImage));
        okImage.setOnMouseClicked(e -> window.close());

        Image bckGroundImage = null;
        try {
            bckGroundImage = new Image(new FileInputStream("src/assets/AlertBoxBackGround.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundimage = new BackgroundImage(bckGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(478, 226, false, false, false, false));
        Background background = new Background(backgroundimage);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okImage);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout, 478, 226);
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