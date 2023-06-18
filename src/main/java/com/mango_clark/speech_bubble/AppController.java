package com.mango_clark.speech_bubble;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
}