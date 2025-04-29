package Patient;  // Declares the package name 'Patient'. Helps in organizing related classes together.

import java.sql.Connection;       // Importing Connection class to establish a connection with the database.
import java.sql.PreparedStatement; // Importing PreparedStatement to execute parameterized SQL queries.
import java.sql.SQLException;      // Importing SQLException to handle any SQL-related exceptions.
import java.util.Scanner;          // Importing Scanner to take user input.

public class Patients {  // Defines the class 'Patients', which contains methods for handling patient-related operations.

	private Connection connection; // A variable to store the database connection object.
	private Scanner scanner;       // A Scanner object to read user input from the console.

	// Constructor to initialize database connection and scanner.
	public Patients(Connection connection, Scanner scanner) {
		this.connection = connection; // Assigns the passed connection object to the class variable.
		this.scanner = scanner;       // Assigns the passed scanner object to the class variable.
	}

	// Method to add a new patient to the database.
	public void addPatient() {
		// Prompting user to enter patient details.
		System.out.println("Enter Patient Name : ");
		String name = scanner.next(); // Reads the patient's name.

		System.out.println("Enter Patient Age : ");
		int age = scanner.nextInt(); // Reads the patient's age.

		System.out.println("Enter Patient Gender : ");
		String gender = scanner.next(); // Reads the patient's gender.

		// Using try-catch to handle potential SQL exceptions.
		try {
			// SQL query to insert patient data into the 'Patient' table.
			String query = "INSERT INTO Patient(name, age, gender) VALUES(?, ?, ?)";
			
			// Creating a PreparedStatement to execute the SQL query with user-provided values.
			PreparedStatement prepareStatement = connection.prepareStatement(query);

			// Setting parameters for the PreparedStatement (to prevent SQL injection).
			prepareStatement.setString(1, name);  // Sets the first '?' with the name.
			prepareStatement.setInt(2, age);      // Sets the second '?' with the age.
			prepareStatement.setString(3, gender);// Sets the third '?' with the gender.

			// Executing the query and getting the number of affected rows.
			int affectedRows = prepareStatement.executeUpdate(); // Returns the number of rows affected.
			
			// Checking if the data was inserted successfully.
			if (affectedRows > 0) {
				System.out.println("Data Registered Successfully");
			} else {
				System.out.println("Failed to Register Data");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Prints the stack trace if an exception occurs (helps in debugging).
		}
	}
}
