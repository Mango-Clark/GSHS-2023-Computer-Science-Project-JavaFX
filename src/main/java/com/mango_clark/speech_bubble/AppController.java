package com.mango_clark.speech_bubble;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class AppController implements Initializable {
	@FXML
	MenuBar menuBar;
	@FXML
	ImageView imageView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeImage();
	}

	public void initializeImage() {
		imageView.setImage(new Image(getClass().getResourceAsStream("/files/images/107852415_p4.png")));
	}

	public void menuBarRequest(ActionEvent event) {
	}

}