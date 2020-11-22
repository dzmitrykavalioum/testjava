import model.Guy;
import model.Person;

import javax.lang.model.type.MirroredTypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static List<Person> persons;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        persons = new ArrayList<Person>();
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
            System.out.println("5. Generate some users");
            System.out.println("0. Exit");
            choiceString = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceString);
            } catch (NumberFormatException e) {
                System.out.println("Enter correct number from menu");
                choice = 100;
            }
            switch (choice) {
                case 1: {
                    printAllUsers(persons);
                    break;
                }
                case 2: {
                    createUser();

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
                case 5: {
                    generateSomeUsers();
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

    private static void generateSomeUsers() {
        List<String> phones = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        phones.add("375291234567");
        phones.add("375255511122");
        roles.add("actor");
        roles.add("editor");
        persons.add(new Person("Dzmitry", "Kava", roles, phones, "wwwleningrad@gmail.com"));
        persons.add(new Person("Michail", "Kaval", roles, phones, "mikakika@gmail.com"));
        System.out.println(persons.size() + " Persons are created \n");
    }

    private static void printAllUsers(List<Person> persons) {
        for (Person person : persons
        ) {
            printAllDataPerson(person);
        }
    }


    private static void createUser() {
        Pattern emailPattern, phonePattern;
        Matcher emailMatcher, phoneMatcher;
        Scanner sc = new Scanner(System.in);
        List<String> phones = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        System.out.print("Enter first name: ");
        String firstname = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastname = sc.nextLine();

        String email = "";

        emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        phonePattern = Pattern.compile("^375[0-9]{9}$");
        do {
            System.out.print("Enter correct email (for example user@us.com): ");
            email = sc.nextLine();
            emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches()) {
                System.out.println("Error. Email not entered correctly");
            }

        } while (!emailMatcher.matches());


        int qtyPhones = 0;
        String qtyPhonesString = "";
        do {
            System.out.println("Enter the correct number of phone numbers (1-3)");
            qtyPhonesString = sc.nextLine();
            try {
                qtyPhones = Integer.parseInt(qtyPhonesString);

            } catch (NumberFormatException e) {
                System.out.println("Error. Phone number not entered correctly");
            }
        } while (!(qtyPhones >= 1 && qtyPhones <= 3));

        String phone = "";
        for (int i = 0; i < qtyPhones; i++) {
            System.out.print("Enter phone number: ");

            do {
                System.out.print("Enter correct email (for example user@us.com): ");
                phone = sc.nextLine();
                phoneMatcher = phonePattern.matcher(phone);
                if (!emailMatcher.matches()) {
                    System.out.println("Error. Email not entered correctly");
                }
            }
            while (!phoneMatcher.matches());


            phones.add(phone);
        }
        int qtyRoles = 0;
        String qtyRolesString = "";
        do {
            System.out.println("Enter the correct number of roles (1-3)");
            qtyRolesString = sc.nextLine();
            try {
                qtyRoles = Integer.parseInt(qtyRolesString);

            } catch (NumberFormatException e) {

            }
        } while (!(qtyRoles >= 1 && qtyRoles <= 3));
        for (int i = 0; i < qtyRoles; i++) {
            System.out.print("Enter role: ");
            String role = sc.nextLine();
            roles.add(role);
        }

        Person person = new Person(firstname, lastname, roles, phones, email);
        persons.add(person);
        System.out.println("New user is created\n");

    }

    private static void printAllDataPerson(Person person) {
        List<String> phones = person.getPhones();
        List<String> roles = person.getRoles();
        System.out.println();
        System.out.println(person.getFirstName() + "\t" + person.getLastName());
        System.out.println(person.getEmail());
        for (String phone : phones
        ) {
            System.out.println("Phone:\t" + phone);
        }
        for (String role : roles
        ) {
            System.out.println("Role:\t" + role);
        }
        System.out.println();
    }
}
