package se.lexicon;
import jdk.dynalink.beans.StaticClass;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        //Scanner
        Scanner sc = new Scanner(System.in);

        //List for the all member
        List<Contact> ContactList = new ArrayList<>();
        ContactList.add(new Contact("Anna Amdresson", "070-1234567", "anna@example.com"));
        ContactList.add(new Contact("Sten Karlsson", "070-9876543", "Sten@example.com"));
        ContactList.add(new Contact("Blackstar", "111111111", "Blackstar@number1.com"));
        ContactList.add(new Contact("Jason White", "073-981253", "Jason@example.com"));
        ContactList.add(new Contact("Soul Eater", "070-7740224", "Soul@example.com"));

        boolean run = true;




        // main menu and Loop
        while (run) {
            System.out.println("Welcome!");
            System.out.println("=============");
            System.out.println("1 Add Contact");
            System.out.println("2 Lookup Contact by Name");
            System.out.println("3 View All Contacts");
            System.out.println("4 Exit Program");
            System.out.print("Enter your choice");

            String choice = sc.nextLine();



            switch (choice) {
                //add Comtacts name, email, and phone
                case "1":
                    System.out.println("Add Contact:");
                    System.out.println("Enter Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Email:");
                    String email = sc.nextLine();
                    System.out.println("Enter Telephone:");
                    String phone = sc.nextLine();
                    ContactList.add(new Contact(name, email, phone));
                    System.out.println(name + " has been added!");

                    break;
                case "2":
                    System.out.println("Lookup Contact by Name:");
                    System.out.println("Enter Name to search for:");
                    String searchName = sc.nextLine();

                    // See if Name be found in list (need First name and last name)
                    boolean found = false;
                    for (Contact contact : ContactList) {
                        if (contact.getName().equalsIgnoreCase(searchName)) {
                            System.out.println("\nContact Found!");
                            System.out.println("Name: " + contact.getName());
                            System.out.println("Email: " + contact.getEmail());
                            System.out.println("Telephone: " + contact.getPhone());
                            found = true;
                            break;
                        }
                        // if you spell it wrong, fogot last name or the person dose it exist
                        if (!found) {
                            System.out.print("Contact Not Found!\n");
                        }
                    }
                    break;

                    // it will print all Contacts in alphabetically by name.
                    case "3":
                    System.out.println("View All Contacts");
                    ContactList.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
                    System.out.println(ContactList);
                    break;

                    //close the program
                    case "4":
                    System.out.println("Goodbye!");
                    run = false;
            }
                    sc.close();
        }
    }
     // Storge for data
     static class Contact {
        String name;
        String email;
        String phone;

        public Contact(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Email: " + email + ", Phone: " + phone;
        }
    }
}