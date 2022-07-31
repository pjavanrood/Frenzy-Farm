import controller.GameProcessor;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShopController1 {

    Stage window;
    Scene shopScene;

    public ShopController1() {
    }

    public void display(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(shopScene);
        window.showAndWait();
    }

    public ImageView henbutton;
    public ImageView turkeybutton;
    public ImageView buffalobutton;
    public ImageView millbutton;
    public ImageView bakerybutton;
    public ImageView clothstore;
    public ImageView tailorshop;
    public ImageView dairy;
    public ImageView icecreamshop;

    public void buyHen () {
        GameProcessor.buyAnimal("hen");
    }

    public void buyTurkey () {
        GameProcessor.buyAnimal("turkey");
    }

    public void buyBuffalo () {
        GameProcessor.buyAnimal("buffalo");
    }

    public void buyCat () {
        GameProcessor.buyAnimal("cat");
    }

    public void buyDog () {
        GameProcessor.buyAnimal("dog");
    }

    public void buildMill () {
        GameProcessor.buildFactory("mill");
    }

    public void buildBakery () {
        GameProcessor.buildFactory("bakery");
    }

    public void buildDairy () {
        GameProcessor.buildFactory("milk packing factory");
    }

    public void buildIceCreamShop () {
        GameProcessor.buildFactory("ice cream factory");
    }

    public void buildClothStore () {
        GameProcessor.buildFactory("textile factory");
    }

    public void buildTailorShop () {
        GameProcessor.buildFactory("tailor shop");
    }

    public void returnButton () {
        //window.close();
        window.setScene(null);
        window.close();
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

//    public void glowButton(ImageView imageView) {
//        Glow glow = new Glow();
//        glow.setLevel(0.5);
//        imageView.setEffect(glow);
//    }
//
//    public void unGlowButton(ImageView imageView) {
//        Glow glow = new Glow();
//        glow.setLevel(0);
//        imageView.setEffect(glow);
//    }

}
