package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Ecommerce extends Application {

    UserInterface userInterface = new UserInterface();

    public Ecommerce() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Load the favicon image directly from the file path
        //Image favImage = new Image("C:\\Users\\jangi\\IdeaProjects\\Ecommerce\\src\\appstore.jpg");

        // Set the favicon for the primary stage
        //stage.getIcons().add(favImage);

        Scene scene = new Scene(userInterface.createContent());
        stage.setTitle("E-commerce: Buy amazing products at best prices!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}