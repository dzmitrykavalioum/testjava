import model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static List<Person> persons;
    private static final String fileName = "test.txt";
    private static final int outOfRange = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        persons = new ArrayList<Person>();
        loadData();
        showMenu(scanner);
        saveChanges();
    }

    public static void showMenu(Scanner scanner) {
        int choice = 0;
        boolean isNewUser = true;
        String choiceString = "";
        System.out.println("List users");
        while (!"0".equals(choiceString)) {
            System.out.println("1. View all users");
            System.out.println("2. Create new user");
            System.out.println("3. Delete user by index");
            System.out.println("4. Edit user by index");
            System.out.println("5. Generate some users");
            System.out.println("6. Save changes");
            System.out.println("7. Load data");

            System.out.println("0. Exit");
            choiceString = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceString);
            } catch (NumberFormatException e) {
                choice = outOfRange;
            }
            switch (choice) {
                case 1: {
                    printAllUsers(persons);
                    break;
                }
                case 2: {
                    isNewUser = true;
                    createUser(isNewUser);
                    break;
                }
                case 3: {
                    deleteUserByIndex();
                    break;
                }
                case 4: {
                    isNewUser = false;
                    createUser(isNewUser);
                    break;
                }
                case 5: {
                    generateSomeUsers();
                    break;
                }

                case 6: {
                    saveChanges();
                    break;
                }
                case 7:
                    loadData();
                    break;
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

    private static void loadData() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            persons = ((ArrayList<Person>) ois.readObject());
            System.out.println("File has been read");
        } catch (Exception ex) {
            System.out.println("The file does not exist. Create users and save them to a file (menu item 6)");
        }
    }

    private static void saveChanges() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(persons);
            System.out.println("Changes are saved to a file.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void generateSomeUsers() {
        List<String> phones = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        phones.add("375291234567");
        phones.add("375257654321");
        roles.add("actor");
        roles.add("editor");
        persons.add(new Person("Castiel", "Collins", roles, phones, "castiel@gmail.com"));
        persons.add(new Person("Deen", "Weenchester", roles, phones, "deen@gmail.com"));
        persons.add(new Person("Jack", "Luciferssun", roles, phones, "jack@Jack.com"));
        persons.add(new Person("Ruby", "Freate", roles, phones, "ruby@gmail.com"));
        persons.add(new Person("Sam", "Weenchester", roles, phones, "sam@gmail.com"));
        System.out.println(persons.size() + " Persons are created \n");
    }

    private static void printAllUsers(List<Person> persons) {
        for (Person person : persons
        ) {
            System.out.println(person.toString());
        }
    }

    private static void deleteUserByIndex() {
        printShortUsers();
        Scanner scanner = new Scanner(System.in);
        String idString = "";
        int indexInt = -1;
        int maxIndex = persons.size() - 1;

        do {
            System.out.print("Enter the correct index to delete: (0-" + maxIndex + ")");
            idString = scanner.nextLine();
            try {
                indexInt = Integer.parseInt(idString);

            } catch (NumberFormatException e) {
                System.out.println("Error. Index must be between 0 and " + maxIndex);
            }
        } while (!(indexInt >= 0 && indexInt <= maxIndex));

        Person person = persons.get(indexInt);
        if (person != null) {
            persons.remove(person);
            System.out.println("Success! User "+person.getFirstName()+" "+person.getLastName()+" has been deleted!\n");
        } else {
            System.out.println("Error. Wrong index");
        }
    }

    private static Person findFirstPersonByLastName(String lastName, List<Person> persons) {
        //unused for the future
        Person person = null;
        for (Person item : persons
        ) {
            if (item.getLastName().equals(lastName)) {
                person = item;
                break;
            }
        }
        // you must to check : person!=null
        return person;
    }

    private static void createUser(boolean isNewUser) {
        Pattern emailPattern, phonePattern;
        Matcher emailMatcher, phoneMatcher;
        Scanner sc = new Scanner(System.in);
        List<String> phones = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        Person editPerson = null;
        int maxIndex = persons.size() - 1;
        int indexInt = -1;
        if (!isNewUser) {
            String indexString = "";
            do {
                printShortUsers();
                System.out.print("Enter correct user index: (0-" + maxIndex + ")");

                indexString = sc.nextLine();
                try {
                    indexInt = Integer.parseInt(indexString);

                } catch (NumberFormatException e) {
                    System.out.println("Error. Index must be between 0 and " + maxIndex);
                }

            } while (!(indexInt >= 0 && indexInt <= maxIndex));
            editPerson = persons.get(indexInt);
            System.out.println(editPerson.toString());
        }
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
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
            do {
                System.out.print("Enter correct phone number (for example 375291234567): ");
                phone = sc.nextLine();
                phoneMatcher = phonePattern.matcher(phone);
                if (!phoneMatcher.matches()) {
                    System.out.println("Error. Phone number not entered correctly");
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
            System.out.print("Enter the role: ");
            String role = sc.nextLine();
            roles.add(role);
        }
        if (isNewUser) {
            Person person = new Person(firstName, lastName, roles, phones, email);
            persons.add(person);
            System.out.println("The new user has been created\n");
        } else {
            editPerson.setFirstName(firstName);
            editPerson.setLastName(lastName);
            editPerson.setRoles(roles);
            editPerson.setPhones(phones);
            editPerson.setEmail(email);
            persons.set(indexInt, editPerson);
            System.out.println("The user has been changed\n");
        }
    }

    private static void printShortUsers() {
        for (int i = 0; i < persons.size(); i++) {
            System.out.println("Index: " + i + "\t" + persons.get(i).getFirstName() + "\t" + persons.get(i).getLastName());
        }
    }


}
