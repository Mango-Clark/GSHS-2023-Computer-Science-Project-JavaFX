package com.mango_clark.speech_bubble;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	BorderPane rootPane;
	MenuBar menuBar;
	ImageView imageView;
	Image image;
	Menu menu_File, menu_Help;
	MenuItem menuItem_Open, menuItem_Save;
	MenuItem menuItem_GitHub;

	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = initializeScene();
			initializeImage();
			initializeMenuBar(primaryStage);

			primaryStage.setTitle("Gs21079 - Paint Speech Bubble");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Scene initializeScene() {
		rootPane = new BorderPane();
		rootPane.setPrefSize(700, 500);
		menuBar = new MenuBar();
		rootPane.setTop(menuBar);
		imageView = new ImageView();
		imageView.prefHeight(400);
		imageView.prefWidth(600);
		rootPane.setLeft(imageView);
		return new Scene(rootPane);
	}

	public void initializeImage() {
		image = new Image(getClass().getResourceAsStream("files/images/107852415_p4.png"), 400, 600, true, true);
		imageView.setImage(image);
	}

	private void initializeMenuBar(Stage stage) {
		menu_File = new Menu("File");
		menu_Help = new Menu("Help");

		menuItem_Open = new MenuItem("Open");
		menuItem_Open.setOnAction(e -> {
			System.out.println("Open");
		});
		menuItem_Save = new MenuItem("Save");
		menuItem_Save.setOnAction(e -> {
			System.out.println("Save");
		});
		menu_File.getItems().addAll(menuItem_Open, menuItem_Save);

		menuItem_GitHub = new MenuItem("GitHub");
		menuItem_GitHub.setOnAction(e -> AppController.showGithubPopup(stage));
		menu_Help.getItems().addAll(menuItem_GitHub);

		menuBar.getMenus().addAll(menu_File, menu_Help);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
