package rex.jfx.result.event;

import java.sql.SQLException;
import java.sql.CallableStatement;
import javafx.event.ActionEvent;
import rex.jfx.result.db.DataBase;
import rex.jfx.result.Result;
import java.sql.ResultSet;
import java.sql.Types;

public class ResultPrint implements javafx.event.EventHandler<ActionEvent> {
	public static Result RS;
	public ResultPrint(Result rs) {
		RS = rs;
	}
	@Override public void handle(ActionEvent event) {
		if (DataBase.connect()) {
			String query = "{ ?=call get_result("+RS.CHOICEBOX.getValue()+")}";
			try {
				CallableStatement stmt;
				if (DataBase.CONN != null) {
					stmt = DataBase.CONN.prepareCall(query);
				} else {
					return;
				}
				stmt.registerOutParameter(1, Types.VARCHAR);
				stmt.execute();
				String rs = stmt.getString(1);
				RS.RESULT.setText(rs);
				RS.RESULT.getStyleClass().remove(alter(rs));
				RS.RESULT.getStyleClass().add(rs);
			} catch (SQLException e) {
				System.out.println("Query Problem");
				System.out.println(e);
			}
			DataBase.close();
		}
	}
	public String alter(String rs) {
		if (rs.equals("PASSED")) {
			return "FAILED";
		} else {
			return "PASSED";
		}
	}
}