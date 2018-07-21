package rex.jfx.result;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import rex.jfx.result.db.DataBase;
// import javafx.event.ActionEvent;
// import java.sql.Connection;
import java.sql.Statement;
// import java.sql.PreparedStatement;
// import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Result extends Application {
	
	Label idLbl, nameLbl, m1Lbl, m2Lbl, m3Lbl, resultLbl;
	public TextField NAME, M1, M2, M3, RESULT;
	public ChoiceBox<Integer> CHOICEBOX;
	Button detailsBtn, resultBtn;
	
	@Override public void start(Stage stage) {

		idLbl = new Label("ID");
		CHOICEBOX = new ChoiceBox<Integer>();
		HBox hb1 = new HBox(idLbl, CHOICEBOX);
		hb1.getStyleClass().add("hbox");
		
		detailsBtn = new Button("Details");
		detailsBtn.setOnAction(new rex.jfx.result.event.Detail(this));
		
		nameLbl = new Label("Name");
		NAME = new TextField();
		HBox hb2 = new HBox(nameLbl, NAME);
		hb2.getStyleClass().add("hbox");

		m1Lbl = new Label("Marks 1");
		M1 = new TextField();
		HBox hb3 = new HBox(m1Lbl, M1);
		hb3.getStyleClass().add("hbox");

		m2Lbl = new Label("Marks 2");
		M2 = new TextField();
		HBox hb4 = new HBox(m2Lbl, M2);
		hb4.getStyleClass().add("hbox");

		m3Lbl = new Label("Marks 3");
		M3 = new TextField();
		HBox hb5 = new HBox(m3Lbl, M3);
		hb5.getStyleClass().add("hbox");

		resultBtn = new Button("Result");
		resultBtn.setOnAction(new rex.jfx.result.event.ResultPrint(this));

		resultLbl = new Label("Result");
		RESULT = new TextField();
		HBox hb6 = new HBox(resultLbl, RESULT);
		hb6.getStyleClass().add("hbox");

		VBox vb = new VBox(hb1, new StackPane(detailsBtn), hb2, hb3, hb4, hb5, new StackPane(resultBtn), hb6);
		vb.getStyleClass().add("vbox");

		Scene scene = new Scene(vb, 500, 470);
		scene.getStylesheets().add("css/style.css");

		loadDetail();

		stage.setScene(scene);
		stage.getIcons().add(new javafx.scene.image.Image("img/Raza.png"));
		stage.setTitle("JavaFX Stage Window Application");		
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	public boolean loadDetail() {
		if (DataBase.connect()) {
			try {
				String query = "SELECT ID FROM STUDENT";
				Statement stmt = null;
				if (DataBase.CONN != null) {
					stmt = DataBase.CONN.createStatement();
				} else {
					return false;
				}
				ResultSet rs = stmt.executeQuery(query);
				int first = 0;
				boolean flag = false;
				while(rs.next()) {
					CHOICEBOX.getItems().add(rs.getInt(1));
					if (!flag) {
						first = rs.getInt(1);
						flag = true;
					}
				}
				CHOICEBOX.setValue(first);
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Query Problem");
				System.out.println(e);
				return false;
			}
			if (DataBase.close()) {
				return true;			
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
