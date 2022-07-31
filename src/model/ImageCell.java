package model;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
public class ImageCell {
    ImageView imageView;
    int number;

    public ImageCell(Image image, int number) {
        this.imageView = new ImageView(image);
        this.number = number;
    }
}
