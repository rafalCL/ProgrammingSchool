package pl.coderslab.examples.programmingschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.coderslab.examples.programmingschool.model.User;

public class UserProgram {
	private static Connection conn;

	public static void main(String[] args) {
		UserProgram app = new UserProgram();
		app.run();
		UserInput.close();
	}

	public UserProgram() {
		try {
			UserProgram.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programming_school", "root",
					"");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {
		while (true) {
			User[] users = {};
			try {
				users = User.loadAll(UserProgram.conn);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(0);
			}
			displayUsers(users);
			userMenu();
		}
	}

	public void displayUsers(User[] users) {
		System.out.println("Uzytkownicy w bazie:");
		System.out.println("id | username | email | password | personGroupId");
		for (User user : users) {
			System.out.println(user.getId() + " | " + user.getUsername() + " | " + user.getEmail() + " | "
					+ "<ukryte> | " + user.getPersonGroupId());
		}
	}

	public void userMenu() {
		System.out.println("=== USER MENU ===");
		System.out.println("1 - add");
		System.out.println("2 - edit");
		System.out.println("3 - delete");
		System.out.println("0 - quit");
		System.out.println("Wpisz numer opcji i zatwierdz enterem");
		processInput(UserInput.getInteger());
	}

	private void processInput(Integer input) {
		try {
			switch (input) {
			case 1:
				add();
				break;
			case 2:
				edit();
				break;
			case 3:
				delete();
				break;
			default:
				System.out.println("Koniec programu.");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void delete() throws SQLException {
		System.out.println("USUWANIE ELEMENTU");
		System.out.println("Wprowadz id:");
		Integer id = UserInput.getInteger();
		User user = User.loadById(UserProgram.conn, id);
		user.delete(UserProgram.conn);
		System.out.println("Usuniêto element");
	}

	private void edit() throws SQLException {
		System.out.println("EDYCJA ELEMENTU");
		System.out.println("Wprowadz id:");
		Integer id = UserInput.getInteger();
		System.out.println("Edytujesz: ");
		User[] users = { User.loadById(UserProgram.conn, id) };
		displayUsers(users);
		System.out.println("Wprowadz nowy username:");
		String username = UserInput.getString();
		System.out.println("Wprowadz nowy email:");
		String email = UserInput.getEmail();
		System.out.println("Wprowadz nowy password:");
		String password = UserInput.getPassword();
		User user = new User(username, email, password);
		user.saveToDb(UserProgram.conn);
		System.out.println("Zapisano do bazy");
	}

	private void add() throws SQLException {
		System.out.println("DODAWANIE ELEMENTU");
		System.out.println("Wprowadz username:");
		String username = UserInput.getString();
		System.out.println("Wprowadz email:");
		String email = UserInput.getEmail();
		System.out.println("Wprowadz password:");
		String password = UserInput.getPassword();
		User user = new User(username, email, password);
		user.saveToDb(UserProgram.conn);
		System.out.println("Zapisano do bazy. id: " + user.getId());
	}
}
