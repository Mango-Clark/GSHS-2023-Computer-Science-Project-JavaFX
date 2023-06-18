package com.mango_clark.speech_bubble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.FileSystems;

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
			Scene scene = initialScene();
			showImage();
			initializeMenuBar(primaryStage);

			primaryStage.setTitle("Gs21079 - Paint Speech Bubble");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Scene initialScene() {
		menuBar = new MenuBar();

		imageView = new ImageView();
		imageView.prefHeight(400);
		imageView.prefWidth(600);

		rootPane = new BorderPane();
		rootPane.setPrefSize(700, 500);
		rootPane.setTop(menuBar);
		rootPane.setLeft(imageView);
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
			try {
				File sourceFile = AppController.openImage(s);
				showImage(new FileInputStream(sourceFile));
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				// String name = imageFile.toString();
				// System.out.println(name);
				// showImage(name);
			} catch (NullPointerException exception) {
				exception.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception exception");
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
