package client.factory;

import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CreateProfilePicture {

    public static Circle profilePicture(ImageView image,int radius){
        Circle circle = new Circle(radius);
        if(image.getImage()==null)
            System.out.println("eeeee");
        ImagePattern pattern = new ImagePattern(image.getImage());
        circle.setFill(pattern);
        return circle;
    }
}
