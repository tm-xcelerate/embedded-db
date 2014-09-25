package org.temadison.embeddb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JavaDBDemo {

	private static final String NAME = "Jules Verne";
	private static final String ADDRESS = "1313 Mockingbird Lane";

	private static Connection conn;

	@Test
	public void test() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String connectionURL = "jdbc:derby:src/main/resources/webdb";
		String createString = "CREATE TABLE Employee (NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(50) NOT NULL)";

		try {
			Class.forName(driver);
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(connectionURL);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createString);

			PreparedStatement psInsert = conn.prepareStatement("insert into Employee values (?,?)");

			psInsert.setString(1, NAME);
			psInsert.setString(2, ADDRESS);

			psInsert.executeUpdate();

			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery("select * from Employee");
			int num = 0;
			while (rs.next()) {
				System.out.println(++num + ": Name: " + rs.getString(1) + "\n Address" + rs.getString(2));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
