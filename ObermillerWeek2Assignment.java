/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.obermillerweek2assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Pet Database Program
 *I certify, that this computer program submitted by me is all of my own work. Signed: Dan Obermiller
 * 1/25/2026
 */
public class ObermillerWeek2Assignment {
    
    //ArrayList for Pets
    static ArrayList<Pet> pets = new ArrayList<Pet>();
    static int petCount = 0;
    Scanner s = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        //Check if petdata file exists
        //If there is, load pet data from file
        File data = new File("petdata.txt");
        if (data.exists())
        {
            pets = loadPets();
            petCount = pets.size();
        }
        
        int choice = 0;
        System.out.println("Pet Database Program.");
        //Get user input
        while (choice != 6)
        {
            choice = getUserChoice();
            //Display all pets
            if (choice == 1)
            {
                showAllPets();
            }
            //Add pets
            else if (choice == 2)
            {
                addPets();
            }
            //Removes a pet
            else if (choice == 3)
            {
                removePet();
            }
            //Search pet by age
            else if (choice == 4)
            {
                searchPetsByAge();
            }
            //Search pet by name
            else if (choice == 5)
            {
                searchPetsByName();
            }
        }
        
        savePets(pets);
    }
    
    //Save pet data to file through ArrayList Object
    private static void savePets(ArrayList<Pet> pets) throws FileNotFoundException, IOException
    {
        FileOutputStream file = new FileOutputStream("petdata.txt");
        ObjectOutputStream objStream = new ObjectOutputStream(file);
        objStream.writeObject(pets);
        objStream.close();
    }
    
    //Load pet data from file and input it into ArrayList
    private static ArrayList<Pet> loadPets() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream file = new FileInputStream("petdata.txt");
        ObjectInputStream objStream = new ObjectInputStream(file);
        ArrayList<Pet> pets = (ArrayList<Pet>) objStream.readObject();
        objStream.close();
        return pets;
    }
    
    private static String[] parseArgument(String ln)
    {
        //Split user input
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
        //Get name and age
        System.out.print("add pet (name, age): ");
        line = scnr.nextLine();
        //Unless done, prompt for new pet
        while (!line.equals("done"))
        {
            //Must only be at max 5 pets
            if (petCount < 5)
            {
                parsed = parseArgument(line);
                //Must have name and age in input
                if (parsed.length == 2)
                {
                    //Age must be between 1 and 20
                    if (Integer.parseInt(parsed[1]) >= 1 && Integer.parseInt(parsed[1]) <= 20)
                    {
                        addPet(parsed[0], Integer.parseInt(parsed[1]));
                        numAdded += 1;
                        System.out.print("add pet (name, age): ");
                        line = scnr.nextLine();
                    }
                    else
                    {
                        System.out.println("Error: Age is not within 1-20 range");
                        line = "done";
                    }
                }
                else
                {
                    System.out.println("Error: " + line + " is not a valid input.");
                    line = "done";
                }
            }
            else
            {
                System.out.println("Error: Database is full");
                line = "done";
            }
        }
        System.out.println("" + numAdded + " pets added.");
    }
    
    private static void showAllPets()
    {
        //Displays all pets
        int rowCount = 0;
        printTableHeader();
        //Loop through pet arraylist
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
        //Index must be within ArrayList range
        if (id <= petCount-1 && id >= 0)
        {
            //Remove pet from ArrayList
            System.out.println("" + pets.get(id).getName() + " has been removed.");
            pets.remove(id);
            petCount -= 1;
        }
        else
        {
            System.out.println("Error: Index not within database range");
        }
    }
    
    private static void searchPetsByAge()
    {
        Scanner scnr = new Scanner(System.in);
        int age;
        int rowCount = 0;
        System.out.print("Enter an age to search: ");
        //Get age user input
        age = scnr.nextInt();
        printTableHeader();
        //Search for age in arraylist
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
        //Get name user input
        name = scnr.next();
        printTableHeader();
        //Search for name in Arraylist
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
        //Prints a table row
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

//Pet class is serializable, meaning it can be written to a file
class Pet implements Serializable {
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
