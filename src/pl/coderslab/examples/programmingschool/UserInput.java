package pl.coderslab.examples.programmingschool;

import java.util.Scanner;

public class UserInput {
	private static Scanner s = new Scanner(System.in);
	
	public static void close() {
		UserInput.s.close();
	}
	
	public static Integer getInteger() {
		while(true) {
			if(s.hasNextInt()) {
				Integer input = s.nextInt();
				s.nextLine();
				return input;
			} else {
				System.out.println("Wprowadz poprawny int");
				s.nextLine();
			}
		}
	}
	
	public static String getString() {
		while(true) {
			if(s.hasNextLine()) {
				String input = s.nextLine();
				return input;
			} else {
				System.out.println("Wprowadz poprawny string");
				s.nextLine();
			}
		}
	}
	
	public static String getPassword() {
		while(true) {
			if(s.hasNextLine()) {
				String input = s.next();
				return input;
			} else {
				System.out.println("Wprowadz poprawny password");
				s.nextLine();
			}
		}
	}
	
	public static String getEmail() {
		while(true) {
			if(s.hasNext("\\w+@\\w+\\.\\w+")) {
				String input = s.next();
				return input;
			} else {
				System.out.println("Wprowadz poprawny email");
				s.nextLine();
			}
		}
	}
}
