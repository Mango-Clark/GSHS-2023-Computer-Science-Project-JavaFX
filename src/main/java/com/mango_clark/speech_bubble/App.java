package com.mango_clark.speech_bubble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class App extends Application {
	Stage githubPopup;
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
	TextField bubbleTextField;
	int bubbleNum;
	Label bubbleTextLabel;

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
		imageView.setPreserveRatio(true);

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

		initializeBubble();

		return new Scene(rootPane);
	}

	private void showImage() {
		showImage(getClass().getResourceAsStream("files/images/107852415_p4.png"));
	}

	private void showImage(InputStream is) {
		try {
			image = new Image(is);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException");
		}
		imageView.setImage(image);
	}

	public void showGithubPopup() {
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

	public void openGithubPage() {
		try {
			java.awt.Desktop.getDesktop().browse(
					java.net.URI.create("https://github.com/Mango-Clark/GSHS-2023-Computer-Science-Project-JavaFX"));
			githubPopup.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File openImage(Stage stage) {
		FileChooser imageChooser = new FileChooser();
		imageChooser.setTitle("get Image");
		imageChooser.getExtensionFilters()
				.add(new ExtensionFilter("그림파일 : Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
		return imageChooser.showOpenDialog(stage);
	}

	private void initializeBubble() {
		bubbleView = new ImageView();
		bubbleView.prefHeight(Region.USE_COMPUTED_SIZE);
		bubbleView.prefWidth(Region.USE_COMPUTED_SIZE);
		bubbleView.setPreserveRatio(true);
		showBubble();
	}

	private void toggleBubble(int n) {
		if (bubbleNum != -1) {
			if (n == bubbleNum) {
				hideBubble();
				return;
			}
			hideBubble();
		}
		if (n != bubbleNum) {
			showBubble(n);
		}
	}

	private void hideBubble() {
		bubbleNum = -1;
		imgAnchorPane.getChildren().remove(bubbleView);
		imgAnchorPane.getChildren().remove(bubbleTextLabel);
		return;
	}

	private void showBubble() {
		showBubble(0);
	}

	private void showBubble(int n) {
		String url = new File("./").getAbsolutePath().toString()
				+ "/src/main/java/com/mango_clark/speech_bubble/files/bubbles/" + n + ".png";
		bubbleImage = new Image(url);
		bubbleNum = n;
		bubbleView.setImage(bubbleImage);
		bubbleTextLabel = new Label("text...");
		bubbleTextLabel.setFont(new Font("Consolas", 18));
		bubbleTextLabel.setPrefWidth(150);
		updateBubbleLayout();
		updateBubbleText();
		imgAnchorPane.getChildren().addAll(bubbleView, bubbleTextLabel);
	}

	public void initializeMenuBar(Stage s) {
		menuItem_Open = new MenuItem("Open");
		menuItem_Open.setOnAction(event -> {
			showImage();
			File sourceFile = openImage(s);
			try {
				showImage(new FileInputStream(sourceFile));
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
				System.out.println("File not found: " + sourceFile.getAbsolutePath().toString());
			}
			updateBubbleLayout();
			updateBubbleText();
		});

		menuItem_Save = new MenuItem("Save");
		menuItem_Save.setOnAction(e -> {
			System.out.println("Save");
		});

		menu_File = new Menu("File");
		menu_File.getItems().addAll(menuItem_Open, menuItem_Save);

		menuItem_GitHub = new MenuItem("GitHub");
		menuItem_GitHub.setOnAction(e -> showGithubPopup());

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
		for (int i = 0; i < 4; i++) {
			vBox.getChildren().add(bubbleButton(i));
		}
		return vBox;
	}

	private Button bubbleButton(int n) {
		ImageView view = new ImageView(new Image(
				new File("./").getAbsolutePath().toString()
						+ "/src/main/java/com/mango_clark/speech_bubble/files/bubbles/" + n + ".png"));
		view.setFitHeight(30);
		view.setPreserveRatio(true);
		Button btn = new Button();
		btn.setPrefWidth(35);
		btn.setGraphic(view);
		btn.setContentDisplay(ContentDisplay.TOP);

		btn.setOnAction(e -> toggleBubble(n));
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
		xSlider = new Slider(0.0, 1.0, 0.2);
		xSlider.setBlockIncrement(0.01);
		xSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateBubbleLayout());
		HBox xSliderHBox = new HBox();
		xSliderHBox.setSpacing(10);
		xSliderHBox.setAlignment(Pos.CENTER);
		xSliderHBox.getChildren().addAll(xSliderLabel, xSlider);

		Label ySliderLabel = new Label("  Y_  ");
		ySliderLabel.setFont(new Font("Consolas", 12));
		ySlider = new Slider(0.0, 1.0, 0.2);
		ySlider.setBlockIncrement(0.01);
		ySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateBubbleLayout());
		HBox ySliderHBox = new HBox();
		ySliderHBox.setSpacing(10);
		ySliderHBox.setAlignment(Pos.CENTER);
		ySliderHBox.getChildren().addAll(ySliderLabel, ySlider);

		Label bubbleTextFieldLabel = new Label(" Text ");
		bubbleTextFieldLabel.setFont(new Font("Consolas", 12));
		bubbleTextField = new TextField();
		bubbleTextField.setPromptText("text...");
		bubbleTextField.setFont(new Font("Consolas", 12));
		bubbleTextField.setPrefWidth(140);
		bubbleTextField.textProperty().addListener((observable, oldValue, newValue) -> updateBubbleText());

		HBox textHBox = new HBox();
		textHBox.setSpacing(10);
		textHBox.setAlignment(Pos.CENTER);
		textHBox.getChildren().addAll(bubbleTextFieldLabel, bubbleTextField);

		vBox.getChildren().setAll(xSliderHBox, ySliderHBox, textHBox);
		return vBox;
	}

	public void updateBubbleLayout() {
		bubbleView.setLayoutX(xSlider.getValue() * (image.getWidth() - bubbleImage.getWidth()));
		bubbleView.setLayoutY(ySlider.getValue() * (image.getHeight() - bubbleImage.getHeight()));
		bubbleTextLabel.setLayoutX(xSlider.getValue() * (image.getWidth() - bubbleImage.getWidth()) + 35);
		bubbleTextLabel.setLayoutY(ySlider.getValue() * (image.getHeight() - bubbleImage.getHeight()) + 40);
	}

	public void updateBubbleText() {
		bubbleTextLabel.setText(bubbleTextField.getText());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
