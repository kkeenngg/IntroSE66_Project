import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return "Name: " + name + "\nPhone: " + phone + "\nEmail: " + email + "\n";
    }
}

public class ContactManager {

    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "ContactData.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadContactsFromFile(); // Load contacts when the program starts
        try {
            int choice;
            do {
                viewContacts();
                System.out.println("\nContact Management System");
                System.out.println("1. Add Contact");
                System.out.println("2. Update Contact");
                System.out.println("3. Delete Contact");
                System.out.println("4. Exit & Save Contact Data");
                System.out.print("Enter your choice(Number Only): ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addContact();
                        break;
                    case 2:
                        updateContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        saveContactsToFile(); // Save contacts when the program exits
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 4);
        } catch (Exception e) {
            System.out.println("Try again !");
            System.out.println(e.getMessage());
        }
    }

    private static void addContact() {
        System.out.println();
        System.out.println("Add New Contact System.........");
        System.out.println();

        String name, phone, email;

        while (true) {
            try {
                System.out.print("Enter name: ");
                name = scanner.nextLine();
                int indexName = findContactIndex(name);
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Empty Name detected! The Contact not add .Input it again");
                } 
                else if(indexName != -1){
                    System.out.println("The Contact is Already here ");
                }
                else {
                    break;
                }
                
                
                

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter phone: ");
                phone = scanner.nextLine();
                if (phone.isEmpty()) {
                    throw new IllegalArgumentException("Empty Phone detected! The Contact not add .Input it again");

                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter email: ");
                email = scanner.nextLine();
                if (email.isEmpty()) {
                    throw new IllegalArgumentException("Empty Phone detected! The Contact not add .Input it again");

                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        contacts.add(new Contact(name, phone, email));

        System.out.println("Contact added successfully!");
        System.out.println("--------------------------------------");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
        } else {
            System.out.printf("%40s ", "Contacts Manager Program\n");
            System.out.println("-----------------------------------------------------------------");
            System.out.printf("%-30s | %-20s | %-20s\n", "Name", "Phone", "Email");
            System.out.println("-----------------------------------------------------------------");
            int i = 1;
            for (Contact contact : contacts) {
                System.out.printf("%-30s | %-20s | %-20s\n", i + "." + contact.name, contact.phone, contact.email);
                i++;
            }
            System.out.println("-----------------------------------------------------------------");
        }
    }

    private static void updateContact() {
        System.out.println();
        System.out.println("Update Contact System.........");
        System.out.print("Contact Name -> ");
        for (Contact contact : contacts) {
            System.out.print("| " + contact.name + " ");
        }
        System.out.println();
        System.out.println();
        String oldName;
        while (true) {
            System.out.print("Enter name of contact to update: ");
            oldName = scanner.nextLine();

            if (oldName.isEmpty()) {
                System.out.println("Input The Name Contact . Musn't empty!!");
                continue;
            }
            if (oldName.equals("Exit")) {
                break;
            } else {
                break;
            }
        }
        int index = findContactIndex(oldName);

        while (true) {
            if (index != -1) {
                while (true) {
                    try {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        if (newName.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Empty Name detected! The Contact not add .Input it again");

                        } else {
                            contacts.get(index).name = newName;
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        System.out.print("Enter new phone: ");
                        String newPhone = scanner.nextLine();
                        if (newPhone.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Empty Phone detected! The Contact not add .Input it again");

                        } else {
                            contacts.get(index).phone = newPhone;
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                while (true) {
                    try {
                        System.out.print("Enter new email: ");
                        String newMail = scanner.nextLine();
                        if (newMail.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Empty Email detected! The Contact not add .Input it again");

                        } else {
                            contacts.get(index).email = newMail;
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Contact updated successfully!");
                System.out.println();
                break;
            } else if (index == -1) {
                System.out.println("Contact not found!!!");
                System.out.println("Input Name it again (Or if you don't want update Type 'Exit')");
                System.out.println();
                continue;
            }
        }
    }

    private static void deleteContact() {
        System.out.println();
        System.out.println("Delete Contact System.........");
        System.out.print("Contact Name -> ");
        for (Contact contact : contacts) {
            System.out.print("| " + contact.name + " ");
        }
        System.out.println();
        System.out.println();
        boolean delete = true;
        while (delete) {
            System.out.print("Enter name of contact to delete: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Input The Name Contact . Musn't empty!!");
                continue;
            }
            int index = findContactIndex(name);

            if (name.equals("Exit")) {
                break;
            }

            else if (index != -1) {
                boolean switchCase = true;
                while (switchCase) {
                System.out.print("Are You Sure To delete? [Enter Y or N]: ");
                char YesorNo = scanner.next().charAt(0);
                switch (YesorNo) {
                    case 'Y':
                    contacts.remove(index);
                    System.out.println("Contact deleted successfully!");
                    System.out.println();
                    switchCase = false;
                        break;
                        
                    case 'N':
                    System.out.println("Contact deleted unsuccessful!");
                    switchCase = false;
                    break;
                    default:
                    System.out.println("Invalid Input Please Try again");
                    switchCase = true;
                    System.out.println();
                        break;
                }
                }
                delete = false;
            
            } else if (index == -1) {
                System.out.println("Contact not found!");
                System.out.println("Input Name it again (Or if you don't want update Type 'Exit')");
                System.out.println();
            }
        }

    }

    private static int findContactIndex(String name) {

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ContactData.txt"))) {

            for (Contact contact : contacts) {

                writer.write(contact.name + "," + contact.phone + "," + contact.email);
                writer.newLine();

            }
            System.out.println("Contacts Saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    private static void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ContactData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Contacts Files Loaded successfully.\n");
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }
}
