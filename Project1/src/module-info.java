module Project1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	opens application to javafx.controls,javafx.graphics, javafx.fxml;
}
