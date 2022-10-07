package com.example;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select a command:");
            System.out.println("1: Create user");
            System.out.println("2: Read users");
            System.out.println("3: Update user");
            System.out.println("4: Delete user");
            System.out.println("5: Exit.");
            int input = Integer.parseInt(scanner.nextLine());
            if (input == 1) {
                Users.addUser(scanner);
            }
            if (input == 2) {
                Users.showUsers();
            }
            if (input == 3){
                Users.modifyUser(scanner);
            }
            if (input == 4) {
                Users.deleteUser(scanner);
            }
            if (input == 5) {
                System.out.println("Thank you for using our services!");
                scanner.close();
                break;
            }

        }
        

    }
}
