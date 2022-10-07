package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Users {
    static String username;
    static String password;
    static String rePassword;
    static String age;
    static String command;

    private Users() {
    }

    public static void addUser(Scanner scanner) {
        Connection connection = SqlConexion.usersConection();
        System.out.println("=-> Add account menu: <-=");
        System.out.println("Please, enter your username below: ");
        username = scanner.nextLine();
        System.out.println("Please, enter your password below: ");
        password = scanner.nextLine();
        System.out.println("Please, repeat your password below: ");
        rePassword = scanner.nextLine();
        System.out.println("Please, enter your age: ");
        age = scanner.nextLine();
        if (rePassword.equals(password)) {
            String query = "INSERT INTO user (username,age,password) VALUES('" + username + "','" + age + "','"
                    + password + "');";
            try {
                java.sql.Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                connection.close();

            } catch (SQLException e) {
                System.out.println("Username is alredy taken.");
            }

        } else {
            System.out.println("Passwords does not match!");
        }

    }

    public static void deleteUser(Scanner scanner) {
        Connection connection = SqlConexion.usersConection();
        System.out.println("=-> Delete account menu: <-=");
        System.out.println("Please, enter your username below: ");
        username = scanner.nextLine();
        System.out.println("Please enter your password below: ");
        password = scanner.nextLine();
        String query = "DELETE FROM user WHERE username='" + username + "' AND password='" + password + "';";
        try {
            java.sql.Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void showUsers() {
        Connection connection = SqlConexion.usersConection();
        String query = "SELECT * FROM user;";
        try {
            java.sql.Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            if (!data.next()) {
                System.out.println("There's nothing yet...");
            } else {
                data = statement.executeQuery(query);
                System.out.println("These are the registered users: ");
                while (data.next()) {
                    System.out.println(data.getString("username") + " || Age: " + data.getString("age") + ".");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyUser(Scanner scanner) {
        Connection connection = SqlConexion.usersConection();
        System.out.println("=-> Update account information menu: <-=");
        System.out.println("Please, enter your username below: ");
        username = scanner.nextLine();
        System.out.println("Please, enter your password below: ");
        password = scanner.nextLine();
        try {
            java.sql.Statement statement = connection.createStatement();
            String query = "SELECT EXISTS(SELECT * FROM user WHERE username='" + username + "' and password='"
                    + password + "');";
            ResultSet data = statement.executeQuery(query);
            System.out.println(data);
            System.out.println("what do you want to change? ");
            System.out.println("1: Username.");
            System.out.println("2: Age.");
            System.out.println("3: Password.");
            command = scanner.nextLine();
            switch (command) {
                case "1":
                    try {
                        System.out.println("Please, insert the new username: ");
                        String newUsername = scanner.nextLine();
                        statement = connection.createStatement();
                        query = "UPDATE user SET username = '" + newUsername + "' WHERE username='" + username
                                + "';";
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        System.out.println("Please, insert the new age: ");
                        String newAge = scanner.nextLine();
                        statement = connection.createStatement();
                        query = "UPDATE user SET age = '" + newAge + "' WHERE username='" + username + "';";
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        System.out.println("Please, insert the new password: ");
                        String newPassword = scanner.nextLine();
                        statement = connection.createStatement();
                        query = "UPDATE user SET username = '" + newPassword + "' WHERE username='" + username
                                + "';";
                        statement.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Incorrect command.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
