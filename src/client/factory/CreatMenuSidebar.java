package client.factory;


import client.controller.ContactsPage;
import client.controller.LoginPage;
import client.controller.Page;
import client.main.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class CreatMenuSidebar {

    public VBox creat(Pane root){
        VBox menu = new VBox();
        menu.getStylesheets().add("client/UI/cssFiles/menuPage.css");
        menu.setId("menu");
        menu.prefHeightProperty().bind(root.heightProperty());
        menu.setPrefWidth(350);
        menu.setId("menu");

        ImageView backIcon=new ImageView("client/UI/materialFiles/icons/back2.png");
        Button backbtn=new Button();
        backbtn.setGraphic(backIcon);
        backbtn.setId("backBtn");
        backbtn.setOnAction(event -> {
            ContactsPage.removeMenu();
        });

        HBox topBar=new HBox();
        topBar.getChildren().add(backbtn);
        menu.getChildren().add(topBar);
        topBar.setId("topBar");
        ImageView profilePic=new ImageView(LoginPage.user.getProfilePicturePath());

        HBox profileInfo=new HBox();
        profileInfo.getChildren().add(CreateProfilePicture.profilePicture(profilePic,40));
        profileInfo.getChildren().add(new Label(LoginPage.user.getName()));
        menu.getChildren().add(profileInfo);
        profileInfo.setId("profileInfo");

        HBox item1=new HBox();
        item1.setId("items");

        HBox item2=new HBox();
        item2.setId("items");

        HBox item3=new HBox();
        item3.setId("items");

        HBox item4=new HBox();
        item4.setId("items");


        Button epBTN=new Button();
        epBTN.setText("Edit Profile");
        epBTN.setId("item");
        epBTN.setOnAction(event -> {
            Page.goTo(getClass().getResource("../UI/fxmlFiles/editProfile.fxml"));
        });
        item1.getChildren().add(epBTN);

        Button ngBTN=new Button();
        ngBTN.setText("New Group");
        ngBTN.setId("item");
        ngBTN.setOnAction(event -> {
            Page.goTo(getClass().getResource("../UI/fxmlFiles/newGroup.fxml"));
        });
        item2.getChildren().add(ngBTN);

        Button ncBTN=new Button();
        ncBTN.setText("New Channel");
        ncBTN.setId("item");
        item3.getChildren().add(ncBTN);

        Button eBTN=new Button();
        eBTN.setText("Log Out");
        eBTN.setId("item");
        eBTN.setOnAction(event -> {
            try {
                Main.outputStream.writeUTF("logout");

                Main.outputStream.flush();
                LoginPage.user=null;
                Page.goTo(getClass().getResource("../UI/fxmlFiles/loginPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        item4.getChildren().add(eBTN);

        menu.getChildren().addAll(item1,item2,item3,item4);
        menu.setTranslateX(-190);
        return menu;
    }
}
