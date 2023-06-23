package com.mango_clark.speech_bubble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
	BorderPane rootPane;
	AnchorPane imgAnchorPane;
	MenuBar menuBar;
	Menu menu_File, menu_Help;
	MenuItem menuItem_Open, menuItem_Save;
	MenuItem menuItem_GitHub;
	ImageView imageView;
	ImageView bubbleView;
	Image bubbleImage;
	Image image;
	Slider xSlider;
	Slider ySlider;

	boolean bubbleEnabled;

	@Override
	public void start(Stage primaryStage) {
		Scene scene = initialScene(primaryStage);

		primaryStage.setTitle("Gs21079 - Paint Speech Bubble");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Scene initialScene(Stage s) {
		menuBar = new MenuBar();

		imageView = new ImageView();
		imageView.prefHeight(Region.USE_COMPUTED_SIZE);
		imageView.prefWidth(Region.USE_COMPUTED_SIZE);
		imageView.setPreserveRatio(true);

		initializeBubble();
		showImage();
		initializeMenuBar(s);

		imgAnchorPane = new AnchorPane();
		imgAnchorPane.getChildren().setAll(imageView);

		rootPane = new BorderPane();
		rootPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		rootPane.setTop(menuBar);
		rootPane.setCenter(
				new HBox(new Separator(Orientation.VERTICAL), imgAnchorPane, new Separator(Orientation.VERTICAL)));
		rootPane.setLeft(bubbleButtonVBox(s));
		rootPane.setRight(bubbleAttributeVBox(s));
		rootPane.setStyle("-fx-background-color: #dddddd");
		return new Scene(rootPane);
	}

	private void showImage() {
		showImage(getClass().getResourceAsStream("files/images/107852415_p4.png"));
	}

	private void showImage(InputStream is) {
		try {
			image = new Image(is, 1000.0, 700.0, true, true);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException");
		}
		imageView.setImage(image);
	}

	private void initializeBubble() {
		bubbleEnabled = false;
		bubbleImage = new Image(new File("./").getAbsolutePath().toString()
				+ "/src/main/java/com/mango_clark/speech_bubble/files/bubbles/0.png");
		bubbleView = new ImageView(bubbleImage);
	}

	private void toggleBubble() {
		if (bubbleEnabled) {
			hideBubble();
		} else {
			showBubble();
		}
	}

	private void toggleBubble(int bubbleNum) {
		if (bubbleEnabled) {
			hideBubble();
		} else {
			showBubble(bubbleNum);
		}
	}

	private void hideBubble() {
		bubbleEnabled = false;
		imgAnchorPane.getChildren().remove(bubbleView);
		return;
	}

	private void showBubble() {
		showBubble(0);
	}

	private void showBubble(int bubbleNum) {
		String url = new File("./").getAbsolutePath().toString()
				+ "/src/main/java/com/mango_clark/speech_bubble/files/bubbles/" + bubbleNum + ".png";
		try {
			bubbleImage = new Image(url);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException");
		}
		if (bubbleEnabled) {
		}
		bubbleEnabled = true;
		bubbleView.setImage(bubbleImage);
		updateBubbleLayout();
		imgAnchorPane.getChildren().add(bubbleView);
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

	public VBox bubbleButtonVBox(Stage s) {
		VBox vBox = new VBox();
		vBox.setPrefWidth(40);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vBox.setStyle("-fx-background-color: #ffffff;");
		vBox.getChildren().addAll(bubbleButton(0));
		return vBox;
	}

	private Button bubbleButton(int bubbleNum) {
		ImageView view = new ImageView(new Image(
				new File("./").getAbsolutePath().toString()
						+ "/src/main/java/com/mango_clark/speech_bubble/files/bubbles/" + bubbleNum + ".png"));
		view.setFitHeight(30);
		view.setPreserveRatio(true);
		Button btn = new Button();
		btn.setPrefWidth(35);
		btn.setGraphic(view);
		btn.setContentDisplay(ContentDisplay.TOP);

		btn.setOnAction(e -> toggleBubble(bubbleNum));
		return btn;
	}

	public VBox bubbleAttributeVBox(Stage s) {
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vBox.setStyle("-fx-background-color: #ffffff;");

		Label xSliderLabel = new Label("  X_  ");
		xSliderLabel.setFont(new Font("Consolas", 12));
		xSlider = new Slider(-0.05, 1.05, 0.2);
		xSlider.setBlockIncrement(0.01);
		xSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				updateBubbleLayout();
			}
		});
		HBox xSliderHBox = new HBox();
		xSliderHBox.setSpacing(10);
		xSliderHBox.setAlignment(Pos.CENTER);
		xSliderHBox.getChildren().addAll(xSliderLabel, xSlider);

		Label ySliderLabel = new Label("  Y_  ");
		ySliderLabel.setFont(new Font("Consolas", 12));
		ySlider = new Slider(-0.05, 1.05, 0.2);
		ySlider.setBlockIncrement(0.01);
		ySlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				updateBubbleLayout();
			}
		});
		HBox ySliderHBox = new HBox();
		ySliderHBox.setSpacing(10);
		ySliderHBox.setAlignment(Pos.CENTER);
		ySliderHBox.getChildren().addAll(ySliderLabel, ySlider);

		vBox.getChildren().setAll(xSliderHBox, ySliderHBox);

		// Label heightSliderLabel = new Label("Height");
		// heightSliderLabel.setFont(new Font("Consolas", 12));
		// Slider heightSlider = new Slider(0.0, 1.0, 0.2);
		// heightSlider.setBlockIncrement(0.01);
		// HBox heightSliderHBox = new HBox();
		// heightSliderHBox.setSpacing(10);
		// heightSliderHBox.setAlignment(Pos.CENTER);
		// heightSliderHBox.getChildren().addAll(heightSliderLabel, heightSlider);

		// Label widthSliderLabel = new Label("Width_");
		// widthSliderLabel.setFont(new Font("Consolas", 12));
		// Slider widthSlider = new Slider(0.0, 1.0, 0.2);
		// widthSlider.setBlockIncrement(0.01);
		// HBox widthSliderHBox = new HBox();
		// widthSliderHBox.setSpacing(10);
		// widthSliderHBox.setAlignment(Pos.CENTER);
		// widthSliderHBox.getChildren().addAll(widthSliderLabel, widthSlider);

		// vBox.getChildren().addAll(widthSliderHBox, heightSliderHBox);
		return vBox;
	}

	public void updateBubbleLayout() {
		bubbleView.setLayoutX(xSlider.getValue() * imageView.getFitWidth());
		bubbleView.setLayoutY(ySlider.getValue() * imageView.getFitHeight());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
