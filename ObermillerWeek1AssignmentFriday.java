/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.obermillerweek1assignmentfriday;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author charr
 */
public class ObermillerWeek1AssignmentFriday {

    
    static int CAPACITY = 100;
    //Stores pet objects
    static ArrayList<Pet> pets = new ArrayList<Pet>(CAPACITY);
    static int petCount = 0;
    Scanner s = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choice = 0;
        System.out.println("Pet Database Program");
        //Loops based on user input
        while (choice != 6)
        {
            choice = getUserChoice();
            //Shows all pets
            if (choice == 1)
            {
                showAllPets();
            }
            //Adds as many pets as user wants
            else if (choice == 2)
            {
                addPets();
            }
            //Removes a pet from index
            else if (choice == 3)
            {
                removePet();
            }
            //Searches and prints pets based on age
            else if (choice == 4)
            {
                searchPetsByAge();
            }
            //Searches and prints pets based on name
            else if (choice == 5)
            {
                searchPetsByName();
            }
        }
    }
    
    private static String[] parseArgument(String ln)
    {
        //Splits line into a String and integer
        String parsed[] = ln.split(" ");
        return parsed;
    }
    
    private static void addPet(String n, int a)
    {
        //Add new pet to ArrayList
        Pet newPet = new Pet(n, a);
        pets.add(petCount, newPet);
        petCount += 1;
        
    }
    
    private static void addPets()
    {
        Scanner scnr = new Scanner(System.in);
        String line;
        String parsed[];
        int numAdded = 0;
        //Get user input for name and age
        System.out.print("add pet (name, age): ");
        line = scnr.nextLine();
        //Keep adding pets until done is entered
        while (!line.equals("done"))
        {
            parsed = parseArgument(line);
            addPet(parsed[0], Integer.parseInt(parsed[1]));
            numAdded += 1;
            System.out.print("add pet (name, age): ");
            line = scnr.nextLine();
        }
        System.out.println("" + numAdded + " pets added.");
    }
    
    private static void showAllPets()
    {
        //Displays every pet stored in ArrayList
        int rowCount = 0;
        printTableHeader();
        for (int i = 0; i < petCount; i++)
        {
            printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
            rowCount += 1;
        }
        printTableFooter(rowCount);
    }
    
    private static void removePet()
    {
        Scanner scnr = new Scanner(System.in);
        int id;
        showAllPets();
        System.out.print("Enter the Pet ID to remove: ");
        id = scnr.nextInt();
        //Remove pet from ArrayList
            System.out.println("" + pets.get(id).getName() + " has been removed.");
            pets.remove(id);
            petCount -= 1;
    }
    
    private static void searchPetsByAge()
    {
        Scanner scnr = new Scanner(System.in);
        int age;
        int rowCount = 0;
        System.out.print("Enter an age to search: ");
        age = scnr.nextInt();
        printTableHeader();
        //Search for pets with specified age
        for (int i = 0; i < petCount; i++)
        {
            if (pets.get(i).getAge() == age)
            {
                printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                rowCount += 1;
            }
        }
        printTableFooter(rowCount);
    }
    
    private static void searchPetsByName()
    {
        Scanner scnr = new Scanner(System.in);
        String name;
        int rowCount = 0;
        System.out.print("Enter a name to search: ");
        name = scnr.next();
        printTableHeader();
        //Search for pets with specified name
        for (int i = 0; i < petCount; i++)
        {
            if (pets.get(i).getName().equals(name))
            {
                printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                rowCount += 1;
            }
        }
        printTableFooter(rowCount);
    }
    
    private static void printTableHeader()
    {
        //Static printing of text
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
    }
    
    private static void printTableRow(int id, String name, int age)
    {
        //Prints a single row
        System.out.printf("|%3d", id);
        System.out.printf(" | %-10s", name);
        System.out.printf("|%4d |\n", age);
    }
    
    private static void printTableFooter(int rowCount)
    {
        //Prints a footer with the number of rows printed
        System.out.println("+----------------------+");
        System.out.println("" + rowCount + " rows in set");
    }
    
    private static int getUserChoice()
    {
        int choice;
        Scanner scnr = new Scanner(System.in);
        //Display choices for user
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add new pets");
        System.out.println(" 3) Remove a pet");
        System.out.println(" 4) Search by age");
        System.out.println(" 5) Search by name");
        System.out.println(" 6) Exit program");
        System.out.print("Your choice: ");
        
        choice = scnr.nextInt();
        return choice;
    }
}


class Pet {
    private String name;
    private int age;
    //Create pet
    public Pet(String n, int a)
    {
        name = n;
        age = a;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int a)
    {
        age = a;
    }
}