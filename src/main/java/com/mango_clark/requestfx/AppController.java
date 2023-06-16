package com.mango_clark.requestfx;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class AppController implements Initializable {
	@FXML
	DatePicker datePicker;

	@FXML
	TextField round;

	@FXML
	Button todayBtn;

	@FXML
	Label todayLabel;

	LocalDate nowDate = LocalDate.now();
	final LocalDate initalDate = LocalDate.of(2022, 4, 1);
	LocalDate setDate;
	long dateDiff;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datePicker.setValue(nowDate);
		setDate = datePicker.getValue();
		update();
	}

	public void todayBtnClick(ActionEvent event) {
		datePicker.setValue(nowDate);
		setDate = datePicker.getValue();
		update();
	}

	public void update() {
		dateDiff = ChronoUnit.DAYS.between(initalDate, setDate);
		round.setText("" + dateDiff);
		nowDate = LocalDate.now();
		todayLabel.setText("회차");
	}

	public void getDate(ActionEvent event) {
		setDate = datePicker.getValue();
		update();
	}

	public void getRound(ActionEvent event) {
		if (!isInteger(round.getText())) {

		}

		update();
	}

	public static boolean isInteger(String s, int radix) {
		Scanner sc = new Scanner(s.trim());
		if (!sc.hasNextInt(radix))
			return false;
		sc.nextInt(radix);
		return !sc.hasNext();
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	void errorMsg(String msg) {

	}
}