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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class fxmlDocumentControl implements Initializable {
	@FXML
	private TextField cb_answerForm;

	@FXML
	private Button cb_backBt;

	@FXML
	private Button cb_doneBt;

	@FXML
	private ComboBox<?> cb_questionForm;

	@FXML
    private TextField cb_username;

	
	@FXML
	private AnchorPane password_form;

	@FXML
	private AnchorPane question_form;

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
	private Button side_havebt1;

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
	private Button tx_backBt;

	@FXML
	private PasswordField tx_confirmpass;

	@FXML
	private Button tx_doneBt;

	@FXML
	private PasswordField tx_newpassword;


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
				//success => another form: main forms
				if (result.next()) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Login Successfull!");
					alert.showAndWait();
					//main form
					Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setTitle("Koi's Fish Shop");
					stage.setMinWidth(1100);
					stage.setMinHeight(600);
					
					stage.setScene(scene);
					stage.show();
					si_signInbt.getScene().getWindow().hide()
;					
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

	public void switchforgotAcc() {
		question_form.setVisible(true);
		si_loginForm.setVisible(false);

		forgotpassQuestion();
	}

	public void doneBt() {
		if (cb_username.getText().isEmpty() || cb_questionForm.getSelectionModel().getSelectedItem() == null
				|| cb_answerForm.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Messaage!");
			alert.setHeaderText(null);
			alert.setContentText("Please insert your infomation!");
			alert.showAndWait();
		} else {
			String selectData = "SELECT username ,question, answer FROM employee WHERE username = ? AND question = ? AND answer = ?";
			connect = Database.connectDB();
			try {
				prepare = connect.prepareStatement(selectData);
				prepare.setString(1, cb_username.getText());
				prepare.setString(2, (String) cb_questionForm.getSelectionModel().getSelectedItem());
				prepare.setString(3, cb_answerForm.getText());

				result = prepare.executeQuery();

				if (result.next()) {
					password_form.setVisible(true);
					question_form.setVisible(false);
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Wrong!");
					alert.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void changePassBt() {
		if (tx_newpassword.getText().isEmpty() || tx_confirmpass.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Messaage!");
			alert.setHeaderText(null);
			alert.setContentText("Please insert your new password/confirm password!");
			alert.showAndWait();
		} else {
			if (tx_newpassword.getText().equals(tx_confirmpass.getText())) {
				String getDate = "SELECT date FROM employee WHERE username = '" + cb_username.getText() + "'";
				connect = Database.connectDB();
				try {

					prepare = connect.prepareStatement(getDate);
					result = prepare.executeQuery();

					String date = "";
					if (result.next()) {
						date = result.getString("date");
					}

					String updatePass = "UPDATE employee set password = '" + tx_newpassword.getText() + "', question ='"
							+ cb_questionForm.getSelectionModel().getSelectedItem() + "', answer = '"
							+ cb_answerForm.getText() + "', date = '" + date + "'WHERE username = '"
							+ cb_username.getText() + "'";
					
					prepare = connect.prepareStatement(updatePass);
					prepare.executeUpdate();
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Messaage!");
					alert.setHeaderText(null);
					alert.setContentText("Done!");
					alert.showAndWait();
					
					si_loginForm.setVisible(true);
					password_form.setVisible(false);
					
					
					tx_confirmpass.setText("");
					tx_newpassword.setText("");
					cb_questionForm.getSelectionModel().clearSelection();
					cb_answerForm.setText("");
					cb_username.setText("");
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Messaage!");
				alert.setHeaderText(null);
				alert.setContentText("Not match!");
				alert.showAndWait();
			}

		}

	}
	
	public void backtoLogin() {
		si_loginForm.setVisible(true);
		question_form.setVisible(false);
	}
	public void backtoQuestion() {
		question_form.setVisible(true);
		password_form.setVisible(false);
	}

	public void forgotpassQuestion() {
		List<String> listQ = new ArrayList<>();

		for (String data : questionList) {
			listQ.add(data);
		}
		ObservableList listData = FXCollections.observableArrayList(listQ);
		cb_questionForm.setItems(listData);
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
				question_form.setVisible(false);
				si_loginForm.setVisible(true);
				password_form.setVisible(false);
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

				question_form.setVisible(false);
				si_loginForm.setVisible(true);
				password_form.setVisible(false);

			});
			slider.play();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}
}
