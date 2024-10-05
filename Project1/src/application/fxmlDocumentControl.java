package application;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;		
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	private String[] questionList = new String[] { "What is your favourite character?", "What is your first letter?",
			"What is your number?" };

	public void regLquestionList() {
		List<String> listQ = new ArrayList<>();

		for (String data : questionList) {
			listQ.add(data);
		}
		ObservableList listData = FXCollections.observableArrayList(listQ);

		su_question.setItems(listData);

	}

	private Alert alert;

	public void loginBt() {
		if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Messaage!");
			alert.setHeaderText(null);
			alert.setContentText("Incorret Username/Password!");
			alert.showAndWait();
		} else {
			String sql1 = "SELECT username, password FROM employee WHERE username = ? and password = ?";
			connect = Database.connectDB();
			try {
				prepare = connect.prepareStatement(sql1);
				prepare.setString(1, si_username.getText());
				prepare.setString(2, si_password.getText());

				result = prepare.executeQuery();
				if (result.next()) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Login Successfull!");
					alert.showAndWait();
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Login Unsuccessfull!");
					alert.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void regBt() {
		if (su_username.getText().isEmpty() || su_password.getText().isEmpty()
				|| su_question.getSelectionModel().getSelectedItem() == null || su_answer.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Messaage!");
			alert.setHeaderText(null);
			alert.setContentText("Please fill you information!");
			alert.showAndWait();
		} else {
			String sql = "INSERT INTO employee (username , password , question , answer ,  date ) VALUES(?,?,?,?,?)";
			connect = Database.connectDB();
			try {
				String checkUser = "SELECT username FROM employee WHERE username = '" + su_username.getText() + "' ";
				prepare = connect.prepareStatement(checkUser);
				result = prepare.executeQuery();
				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Messaage!");
					alert.setHeaderText(null);
					alert.setContentText(su_username.getText() + "is already taken!");
					alert.showAndWait();
				} else if (su_password.getText().length() < 8) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("More word!");
					alert.showAndWait();
				} else {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, su_username.getText());
					prepare.setString(2, su_password.getText());
					prepare.setString(3, (String) su_question.getSelectionModel().getSelectedItem());
					prepare.setString(4, su_answer.getText());

					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					prepare.setString(5, String.valueOf(sqlDate));

					prepare.executeUpdate();

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Successfull!");
					alert.showAndWait();

					su_username.setText("");
					su_password.setText("");
					su_question.getSelectionModel().clearSelection();
					su_answer.setText("");

					TranslateTransition slider = new TranslateTransition();

					slider.setNode(side_form);
					slider.setToX(0);
					slider.setDuration(Duration.seconds(.5));
					slider.setOnFinished((ActionEvent e) -> {
						side_havebt1.setVisible(false);
						side_signUpbt.setVisible(true);
					});
					slider.play();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void switchForm(ActionEvent event) {
		TranslateTransition slider = new TranslateTransition();

		if (event.getSource() == side_signUpbt) {
			slider.setNode(side_form);
			slider.setToX(300);
			slider.setDuration(Duration.seconds(.5));
			slider.setOnFinished((ActionEvent e) -> {
				side_havebt1.setVisible(true);
				side_signUpbt.setVisible(false);
				regLquestionList();
			});
			slider.play();
		} else if (event.getSource() == side_havebt1) {
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
