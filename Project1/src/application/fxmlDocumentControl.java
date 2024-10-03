package application;

import javafx.animation.TranslateTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class fxmlDocumentControl implements Initializable {
	@FXML
	private Hyperlink si_forgotPass;

	@FXML
	private AnchorPane si_loginForm;

	@FXML
	private PasswordField si_password;

	@FXML
	private Button si_signInbt;

	@FXML
	private TextField si_username;

	@FXML
	private AnchorPane side_form;

	@FXML
	private Button side_signUpbt;

	@FXML
	private TextField su_answer;

	@FXML
	private AnchorPane su_createForm;

	@FXML
	private PasswordField su_password;

	@FXML
	private ComboBox<?> su_question;

	@FXML
	private Button su_signUpbt;

	@FXML
	private TextField su_username;

	@FXML
	private Button side_havebt1;

	public void switchForm(ActionEvent event) {
		TranslateTransition slider = new TranslateTransition();

		if (event.getSource() == side_signUpbt) {
			slider.setNode(side_form);
			slider.setToX(300);
			slider.setDuration(Duration.seconds(.5));
			slider.setOnFinished((ActionEvent e) -> {
				side_havebt1.setVisible(true);
				side_signUpbt.setVisible(false);
			});
			slider.play();
		} else if (event.getSource() == side_signUpbt) {
			slider.setNode(side_form);
			slider.setToX(0);
			slider.setDuration(Duration.seconds(.5));
			slider.setOnFinished((ActionEvent e) -> {
				side_havebt1.setVisible(false);
				side_signUpbt.setVisible(true);
			});
			slider.play();
		}
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub		
	}
}
