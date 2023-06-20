package com.mango_clark.speech_bubble;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AppController implements Initializable {

	static Stage githubPopup;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public static void showGithubPopup(Stage stage) {
		Label label = new Label("GitHub page?");
		label.setFont(new Font("Consolas", 15));
		label.setLayoutX(75.4);
		label.setLayoutY(24);
		label.setTextAlignment(TextAlignment.CENTER);

		Button open = new Button("Goto GitHub");
		open.setFont(new Font("Consolas", 12));
		open.setTextAlignment(TextAlignment.CENTER);
		open.setAlignment(Pos.CENTER);
		open.setLayoutX(80.6);
		open.setLayoutY(64);
		open.setOnAction(e -> openGithubPage());

		AnchorPane pane = new AnchorPane(label, open);

		githubPopup = new Stage();
		githubPopup.setAlwaysOnTop(true);
		githubPopup.setResizable(false);
		githubPopup.setHeight(150);
		githubPopup.setWidth(250);
		githubPopup.setTitle("GitHub Popup");
		githubPopup.setScene(new Scene(pane));
		githubPopup.show();
	}

	public static void openGithubPage() {
		try {
			java.awt.Desktop.getDesktop().browse(
					java.net.URI.create("https://github.com/Mango-Clark/GSHS-2023-Computer-Science-Project-JavaFX"));
			githubPopup.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File openImage(Stage stage) {
		FileChooser imageChooser = new FileChooser();
		imageChooser.getExtensionFilters()
				.addAll(new ExtensionFilter("그림파일 : Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
		return imageChooser.showOpenDialog(stage);
	}

	public static VBox bubbleButtonVBox() {
		VBox vBox = new VBox();
		vBox.setPrefWidth(40);
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vBox.setStyle("-fx-background-color: #ffffff;");

		ImageView view = new ImageView(new Image(
				new File("./").getAbsolutePath().toString()
						+ "/src/main/java/com/mango_clark/speech_bubble/files/images/106052857_p2_master1200.jpg"));
		view.setFitHeight(30);
		view.setPreserveRatio(true);
		Button btn1 = new Button();
		btn1.setPrefWidth(35);
		btn1.setGraphic(view);
		btn1.setContentDisplay(ContentDisplay.TOP);

		vBox.getChildren().addAll(btn1);
		return vBox;
	}

	public static VBox bubbleAttributeVBox() {
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		vBox.setStyle("-fx-background-color: #ffffff;");

		Label xSliderLabel = new Label("  X_  ");
		xSliderLabel.setFont(new Font("Consolas", 12));
		Slider xSlider = new Slider(0.0, 1.0, 0.2);
		xSlider.setBlockIncrement(0.01);
		HBox xSliderHBox = new HBox();
		xSliderHBox.setSpacing(10);
		xSliderHBox.setAlignment(Pos.CENTER);
		xSliderHBox.getChildren().addAll(xSliderLabel, xSlider);

		Label ySliderLabel = new Label("  Y_  ");
		ySliderLabel.setFont(new Font("Consolas", 12));
		Slider ySlider = new Slider(0.0, 1.0, 0.2);
		ySlider.setBlockIncrement(0.01);
		HBox ySliderHBox = new HBox();
		ySliderHBox.setSpacing(10);
		ySliderHBox.setAlignment(Pos.CENTER);
		ySliderHBox.getChildren().addAll(ySliderLabel, ySlider);

		Label heightSliderLabel = new Label("Height");
		heightSliderLabel.setFont(new Font("Consolas", 12));
		Slider heightSlider = new Slider(0.0, 1.0, 0.2);
		heightSlider.setBlockIncrement(0.01);
		HBox heightSliderHBox = new HBox();
		heightSliderHBox.setSpacing(10);
		heightSliderHBox.setAlignment(Pos.CENTER);
		heightSliderHBox.getChildren().addAll(heightSliderLabel, heightSlider);

		Label widthSliderLabel = new Label("Width_");
		widthSliderLabel.setFont(new Font("Consolas", 12));
		Slider widthSlider = new Slider(0.0, 1.0, 0.2);
		widthSlider.setBlockIncrement(0.01);
		HBox widthSliderHBox = new HBox();
		widthSliderHBox.setSpacing(10);
		widthSliderHBox.setAlignment(Pos.CENTER);
		widthSliderHBox.getChildren().addAll(widthSliderLabel, widthSlider);

		vBox.getChildren().addAll(xSliderHBox, ySliderHBox, widthSliderHBox, heightSliderHBox);
		return vBox;
	}
}