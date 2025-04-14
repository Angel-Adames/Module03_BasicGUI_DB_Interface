package com.example.module03_basicgui_db_interface;

import com.example.module03_basicgui_db_interface.db.DBConnDbOps;
import java.sql.SQLException;

/**
 * TestCases class to perform various database operations tests.
 */
public class TestCases {

    /**
     * Main method to execute the database operations tests.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws SQLException If a database access error occurs.
     */
    public static void main(String[] args) throws SQLException {

        System.out.println("--- Starting Database Operations Tests ---");
        DBConnDbOps dbOps = new DBConnDbOps();

        /// Test Case 1: Ensure Database and Table Structure
        System.out.println("\n--- Test Case 1: Connect and Ensure Schema ---");
        try {
            boolean usersExist = dbOps.connectToDatabase();
            System.out.println("Test Case 1: connectToDatabase() executed successfully.");
            System.out.println("Test Case 1: Initial check - users exist in table? " + usersExist);
        } catch (Exception e) {
            System.err.println("Test Case 1: FAILED - Error during connectToDatabase(): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        /// Test Case 2: Insert User
        System.out.println("\n--- Test Case 2: Insert User ---");
        String testName = "Test User";
        String testEmail = "test@example.com";
        String testPhone = "123-456-7890";
        String testAddress = "123 Test St";
        String testPassword = "password123";

        try {
            dbOps.insertUser(testName, testEmail, testPhone, testAddress, testPassword);
            System.out.println("Test Case 2: insertUser() executed. Check console output from method for confirmation.");
        } catch (Exception e) {
            System.err.println("Test Case 2: FAILED or User Exists - Error during insertUser(): " + e.getMessage());
        }

        /// Test Case 3: Verify Successful Login
        System.out.println("\n--- Test Case 3: Verify Successful Login ---");
        try {
            boolean loginSuccess = dbOps.verifyUserLogin(testEmail, testPassword);
            if (loginSuccess) {
                System.out.println("Test Case 3: PASSED - verifyUserLogin() returned true for correct credentials.");
            } else {
                System.err.println("Test Case 3: FAILED - verifyUserLogin() returned false for correct credentials.");
            }
        } catch (Exception e) {
            System.err.println("Test Case 3: FAILED - Error during verifyUserLogin(): " + e.getMessage());
            e.printStackTrace();
        }

        /// Test Case 4: Verify Failed Login (Incorrect Password)
        System.out.println("\n--- Test Case 4: Verify Failed Login ---");
        try {
            boolean loginFailure = dbOps.verifyUserLogin(testEmail, "wrongPassword");
            if (!loginFailure) {
                System.out.println("Test Case 4: PASSED - verifyUserLogin() returned false for incorrect password.");
            } else {
                System.err.println("Test Case 4: FAILED - verifyUserLogin() returned true for incorrect password.");
            }
        } catch (Exception e) {
            System.err.println("Test Case 4: FAILED - Error during verifyUserLogin(): " + e.getMessage());
            e.printStackTrace();
        }

        /// Test Case 5: List All Users (Check for Execution without Errors)
        System.out.println("\n--- Test Case 5: List All Users ---");
        try {
            System.out.println("Test Case 5: Attempting to list all users...");
            dbOps.listAllUsers();
            System.out.println("Test Case 5: listAllUsers() executed without throwing an exception.");
        } catch (Exception e) {
            System.err.println("Test Case 5: FAILED - Error during listAllUsers(): " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Database Operations Tests Finished ---");
    }
}
