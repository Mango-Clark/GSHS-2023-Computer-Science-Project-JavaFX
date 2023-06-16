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
	Menu menu_File, menu_Help;
	MenuItem menuItem_Open, menuItem_Save;
	MenuItem menuItem_GitHub;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeImage();
		initializeMenuBar();
	}

	public void initializeImage() {
		imageView.setImage(new Image(getClass().getResourceAsStream("files/images/107852415_p4.png")));
	}

	private void initializeMenuBar() {
		menu_File = new Menu("File");
		menu_Help = new Menu("Help");

		menuItem_Open = new MenuItem("Open");
		menuItem_Save = new MenuItem("Save");
		menu_File.getItems().addAll(menuItem_Open, menuItem_Save);

		menuItem_GitHub = new MenuItem("GitHub");
		menu_Help.getItems().addAll(menuItem_GitHub);

		menuBar.getMenus().addAll(menu_File, menu_Help);
	}

	public void menuBarRequest(ActionEvent event) {

	}

}