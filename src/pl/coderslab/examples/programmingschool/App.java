package pl.coderslab.examples.programmingschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.coderslab.examples.programmingschool.model.User;

public class App {
	public static Connection conn;
	
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
	
	public App() {
			try {
				App.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programming_school",
						                    "root", "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void run() {
		User user = new User("marian",
				"marian@gmail.com", "haslo");
		try {
			user.saveToDb(App.conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.getPassword());	
	}
}
