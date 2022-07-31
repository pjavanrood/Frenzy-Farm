import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImagesLoader {
    //Products
    public static Image eggImage;
    public static Image featherImage;
    public static Image milkBucketImage;
    public static Image breadImage;
    public static Image fabricImage;
    public static Image iceCreamImage;
    public static Image shirtImage;
    public static Image flourImage;
    public static Image milkImage;


    //Animals
    public static Image buffaloImage;
    public static Image henImage;
    public static Image turkeyImage;
    public static Image catImage;
    public static Image houndImage;
    public static Image bearImage;
    public static Image lionImage;
    public static Image tigerImage;
    public static Image bearCagedImage;
    public static Image lionCagedImage;
    public static Image tigerCagedImage;

    //Factories
    public static Image bakeryImage;
    public static Image iceCreamFactoryImage;
    public static Image milkPackingFactory;
    public static Image millImage;
    public static Image tailorShopImage;
    public static Image textileFactory;

    public static Image wellImage;
    public static Image wellBWImage;
    public static Image truckImage;
    public static Image truckBWImage;

    public ImagesLoader() throws FileNotFoundException {
        eggImage = new Image(new FileInputStream("assets/Products/Egg.png"));
        featherImage = new Image(new FileInputStream("assets/Products/Feather.png"));
        milkBucketImage = new Image(new FileInputStream("assets/Products/Milk bucket.png"));
        breadImage = new Image(new FileInputStream("assets/Products/Bread.png"));
        fabricImage = new Image(new FileInputStream("assets/Products/Fabric roll.png"));
        iceCreamImage = new Image(new FileInputStream("assets/Products/Ice cream.png"));
        shirtImage = new Image(new FileInputStream("assets/Products/Shirt.png"));
        flourImage = new Image(new FileInputStream("assets/Products/Flour.png"));
        milkImage = new Image(new FileInputStream("assets/Products/Milk.png"));

        buffaloImage = new Image(new FileInputStream("assets/Animals/Buffalo.png"));
        bearImage = new Image(new FileInputStream("assets/Animals/Bear.png"));
        catImage = new Image(new FileInputStream("assets/Animals/Cat.png"));
        henImage = new Image(new FileInputStream("assets/Animals/Hen.png"));
        houndImage = new Image(new FileInputStream("assets/Animals/Hound.png"));
        lionImage = new Image(new FileInputStream("assets/Animals/Lion.png"));
        tigerImage = new Image(new FileInputStream("assets/Animals/Tiger.png"));
        turkeyImage = new Image(new FileInputStream("assets/Animals/Turkey.png"));

        bearCagedImage = new Image(new FileInputStream("assets/Animals/BearCaged.png"));
        lionCagedImage = new Image(new FileInputStream("assets/Animals/LionCaged.png"));
        tigerCagedImage = new Image(new FileInputStream("assets/Animals/TigerCaged.png"));

        bakeryImage = new Image(new FileInputStream("assets/Factories/Bakery.png"));
        iceCreamFactoryImage = new Image(new FileInputStream("assets/Factories/IceCreamFactory.png"));
        milkPackingFactory = new Image(new FileInputStream("assets/Factories/MilkPackingFactory.png"));
        millImage = new Image(new FileInputStream("assets/Factories/Mill.png"));
        tailorShopImage = new Image(new FileInputStream("assets/Factories/TailorShop.png"));
        textileFactory = new Image(new FileInputStream("assets/Factories/TextileFactory.png"));


        wellImage = new Image(new FileInputStream("assets/Buildings & Truck/Well.png"));
        wellBWImage = new Image(new FileInputStream("assets/WellBW.png"));

        truckImage = new Image(new FileInputStream("assets/Buildings & Truck/Truck.png"));
        truckBWImage = new Image(new FileInputStream("assets/TruckBW.png"));
    }

    public ImageView getImage(String name){
        switch (name) {
            case "egg" : return new ImageView(eggImage);
            case "feather" : return new ImageView(featherImage);
            case "milk" : return new ImageView(milkBucketImage);
            case "packed milk" : return new ImageView(milkImage);
            case "shirt" : return new ImageView(shirtImage);
            case "flour" : return new ImageView(flourImage);
            case "ice cream" : return new ImageView(iceCreamImage);
            case "cloth" : return new ImageView(fabricImage);
            case "bread" : return new ImageView(breadImage);
        }
        return null;
    }

    public ImageView getAnimalImage(String name){
        switch (name) {
            case "bear" : return new ImageView(bearImage);
            case "buffalo" : return new ImageView(buffaloImage);
            case "cat" : return new ImageView(catImage);
            case "hen" : return new ImageView(henImage);
            case "hound" : return new ImageView(houndImage);
            case "lion" : return new ImageView(lionImage);
            case "tiger" : return new ImageView(tigerImage);
            case "turkey" : return new ImageView(turkeyImage);
            case "bear*" : return new ImageView(bearCagedImage);
            case "lion*" : return new ImageView(lionCagedImage);
            case "tiger*" : return new ImageView(tigerCagedImage);
        }
        return null;
    }

    public ImageView getFactoryImage(String name){
        switch (name) {
            case "bakery" : return new ImageView(bakeryImage);
            case "ice cream factory" : return new ImageView(iceCreamFactoryImage);
            case "milk packing factory" : return new ImageView(milkPackingFactory);
            case "mill" : return new ImageView(millImage);
            case "tailor shop" : return new ImageView(tailorShopImage);
            case "textile factory" : return new ImageView(textileFactory);
        }
        return null;
    }
}
