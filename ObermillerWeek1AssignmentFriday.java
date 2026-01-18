/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.obermillerweek1assignmentfriday;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Dan Obermiller
 * 1/16/2026
 */
public class ObermillerWeek1AssignmentFriday {

    //Stores pet objects
    static ArrayList<Pet> pets = new ArrayList<Pet>();
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
