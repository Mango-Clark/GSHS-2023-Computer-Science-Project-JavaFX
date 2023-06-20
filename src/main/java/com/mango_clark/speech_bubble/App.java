package com.mango_clark.speech_bubble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	BorderPane rootPane;
	MenuBar menuBar;
	Menu menu_File, menu_Help;
	MenuItem menuItem_Open, menuItem_Save;
	MenuItem menuItem_GitHub;
	ImageView imageView;
	Image image;
	VBox vBox;

	@Override
	public void start(Stage primaryStage) {
		Scene scene = initialScene();
		showImage();
		initializeMenuBar(primaryStage);

		primaryStage.setTitle("Gs21079 - Paint Speech Bubble");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Scene initialScene() {
		menuBar = new MenuBar();

		imageView = new ImageView();
		imageView.prefHeight(Region.USE_COMPUTED_SIZE);
		imageView.prefWidth(Region.USE_COMPUTED_SIZE);

		rootPane = new BorderPane();
		rootPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		rootPane.setTop(menuBar);
		rootPane.setCenter(
				new HBox(new Separator(Orientation.VERTICAL), imageView, new Separator(Orientation.VERTICAL)));
		rootPane.setLeft(AppController.bubbleButtonVBox());
		rootPane.setRight(AppController.bubbleAttributeVBox());
		rootPane.setStyle("-fx-background-color: #dddddd");
		return new Scene(rootPane);
	}

	private void showImage() {
		showImage(getClass().getResourceAsStream("files/images/107852415_p4.png"));
	}

	private void showImage(InputStream is) {
		try {
			image = new Image(is, 600.0, 400.0, true, true);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException");
		}
		imageView.setImage(image);
	}

	public void initializeMenuBar(Stage s) {
		menuItem_Open = new MenuItem("Open");
		menuItem_Open.setOnAction(event -> {
			showImage();
			File sourceFile = AppController.openImage(s);
			try {
				showImage(new FileInputStream(sourceFile));
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
				System.out.println("File not found: " + sourceFile.getAbsolutePath().toString());
			}
		});

		menuItem_Save = new MenuItem("Save");
		menuItem_Save.setOnAction(e -> {
			System.out.println("Save");
		});

		menu_File = new Menu("File");
		menu_File.getItems().addAll(menuItem_Open, menuItem_Save);

		menuItem_GitHub = new MenuItem("GitHub");
		menuItem_GitHub.setOnAction(e -> AppController.showGithubPopup(s));

		menu_Help = new Menu("Help");
		menu_Help.getItems().addAll(menuItem_GitHub);

		menuBar.getMenus().addAll(menu_File, menu_Help);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
