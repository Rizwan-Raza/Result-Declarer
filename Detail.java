package rex.jfx.result.event;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import rex.jfx.result.db.DataBase;
import rex.jfx.result.Result;
import java.sql.ResultSet;

public class Detail implements javafx.event.EventHandler<ActionEvent> {
	public static Result RS;
	public Detail(Result rs) {
		RS = rs;
	}
	@Override public void handle(ActionEvent event) {
		if (DataBase.connect()) {
			String query = "SELECT NAME, M1, M2, M3 FROM STUDENT WHERE ID=?";
			try {
				PreparedStatement stmt;
				if (DataBase.CONN != null) {
					stmt = DataBase.CONN.prepareStatement(query);
				} else {
					return;
				}
				stmt.setInt(1, RS.CHOICEBOX.getValue());
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					RS.NAME.setText(rs.getString(1));
					RS.M1.setText(String.valueOf(rs.getInt(2)));
					RS.M2.setText(String.valueOf(rs.getInt(3)));
					RS.M3.setText(String.valueOf(rs.getInt(4)));
				} else {
					System.out.println("No Data found with that ID");
					return;
				}
			} catch (SQLException e) {
				System.out.println("Query Problem");
				System.out.println(e);
			}
			DataBase.close();
		}
	}
}