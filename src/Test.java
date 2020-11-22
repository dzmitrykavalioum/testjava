import model.Guy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        showMenu(scanner);


    }

    public static void showMenu(Scanner scanner) {
        int choice = 0;
        String choiceString = "";
        System.out.println("List users");
        while (!"0".equals(choiceString)) {
            System.out.println("1. View all users");
            System.out.println("2. Create new user");
            System.out.println("3. Delete user by id");
            System.out.println("4. Edit user by id");
            System.out.println("0. Exit");
            choiceString = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceString);
            }
            catch (NumberFormatException e){
                System.out.println("Enter correct number from menu");
                choice = 100;
            }
            switch (choice) {
                case 1: {
                    System.out.println("one");
                    break;
                }
                case 2: {
                    System.out.println("two");
                    break;
                }
                case 3: {
                    System.out.println("three");
                    break;
                }
                case 4: {
                    System.out.println("four");
                    break;
                }
                case 0: {
                    System.out.println("Thank you for using this application");
                    break;
                }
                default: {
                    System.out.println("Enter correct number from menu");
                }
            }
        }
        System.out.println("Bye");

    }
}
