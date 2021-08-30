package client.factory;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.User;

public class CreatContactInfo {
    public HBox creat(User contact){
        ImageView imageView = new ImageView(contact.getProfilePicturePath());
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(CreateProfilePicture.profilePicture(imageView, 27));
        VBox vBox = new VBox(5);
        Text text = new Text(contact.getName());
        text.setStyle("-fx-font-size: 18px");
        vBox.getChildren().add(text);
        Label text2 = new Label(contact.getLastSeen());
        if (contact.getLastSeen().equalsIgnoreCase("online"))
            text2.setStyle("-fx-font-size: 10px ; -fx-text-fill: #0088cc");
        else
            text2.setStyle("-fx-font-size: 10px ; -fx-text-fill: gray");
        vBox.getChildren().add(text2);
        hBox.getChildren().add(vBox);
        return hBox;
    }
}
