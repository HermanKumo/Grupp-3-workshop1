package se.lexicon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ContactApp {
    private final List<Contact> contacts;
    private final Scanner sc = new Scanner(System.in);
    private boolean isDone = false; // Fixat: Variabeln för att styra loopen

    public ContactApp() {
        this.contacts = new ArrayList<>();
        initializeDefaultContacts();
    }

    // Startpunkt för programmet
    public static void main(String[] args) {
        ContactApp app = new ContactApp();
        app.run();
    }

    private void initializeDefaultContacts() {
        // Fixat: Sparar direkt i klassens medlemsvariabel 'contacts' istället för en lokal lista
        contacts.add(new Contact("Anna Amdresson", "070-1234567", "anna@example.com"));
        contacts.add(new Contact("Sten Karlsson", "070-9876543", "Sten@example.com"));
        contacts.add(new Contact("Blackstar", "111111111", "Blackstar@number1.com"));
        contacts.add(new Contact("Jason White", "073-981253", "Jason@example.com"));
        contacts.add(new Contact("Soul Eater", "070-7740224", "Soul@example.com"));
    }

    private void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact searchName(String name) {
        for (Contact contact : contacts) {
            // Ändrat till equalsIgnoreCase för mer flexibel sökning
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getSortedContacts() {
        // Fixat: Sorterar listan och returnerar den utan att krascha applikationen med oändlig rekursion
        contacts.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        return contacts;
    }

    public void run() {
        while (!isDone) {
            try {
                printMenu();
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addContactFromInput();
                    case 2 -> lookup();
                    case 3 -> listContacts();
                    case 4 -> exit();
                    default -> throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.println("Only integers between 1 and 4 are valid.\n");
            }
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("Welcome to ContactApp");
        System.out.println("=============");
        System.out.println("1 Add Contact");
        System.out.println("2 Lookup Contact by Name");
        System.out.println("3 View All Contacts");
        System.out.println("4 Exit Program");
        System.out.print("Enter your choice: ");
    }

    // Fixat: Metod för att läsa in input och lägga till kontakt
    private void addContactFromInput() {
        System.out.println("\nAdd Contact:");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        if (name.isBlank()) {
            System.out.println("Invalid input.");
            return;
        }
        System.out.print("Enter Telephone: ");
        String phone = sc.nextLine();
        if  (phone.isBlank()|| !phone.matches("\\d+")) {
            System.out.println("Invalid input.");
            return;

        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        if (email.isBlank()) {
            System.out.println("Invalid input.");
            return;
        }

        Contact newContact = new Contact(name, phone, email);
        addContact(newContact);
        System.out.println(name + " has been added!\n");
    }

    // Fixat: Inkapslad logik för sökfunktionen
    private void lookup() {
        System.out.println("\nLookup Contact by Name:");
        System.out.print("Enter Name to search for: ");
        String searchName = sc.nextLine();

        Contact foundContact = searchName(searchName);
        if (foundContact != null) {
            System.out.println("\nContact Found!");
            System.out.println("Name: " + foundContact.getName());
            System.out.println("Telephone: " + foundContact.getPhone());
            System.out.println("Email: " + foundContact.getEmail() + "\n");
        } else {
            System.out.println("Contact Not Found!\n");
        }
    }

    // Fixat: Inkapslad logik för att lista alla kontakter
    private void listContacts() {
        System.out.println("\nView All Contacts:");
        for (Contact contact : getSortedContacts()) {
            System.out.println(contact);
        }
        System.out.println();
    }

    private void exit() {
        System.out.println("Goodbye!");
        isDone = true;
    }
}

// Dataklass (Städad och ligger som en separat eller sub-klass)
class Contact {
    private final String name;
    private final String phone;
    private final String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}