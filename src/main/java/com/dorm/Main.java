package com.dorm;

import com.dorm.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewFactory viewFactory = new ViewFactory(primaryStage);
        viewFactory.showLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
