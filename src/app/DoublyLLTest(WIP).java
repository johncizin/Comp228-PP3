package apps;

import adts.*;

import java.io.*;
import java.util.Scanner;

public class DoublyLLTest {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
			DoublyLL<Integer> DLList = new DoublyLL<>();
			boolean running = true;
	        while (running == true) {
	            System.out.println("\n--- Sorted Double Link List Viewer ---");
	            System.out.println("1. Display list (sorted Order)");
	            System.out.println("2. Display list (reverse Order)");
	            System.out.println("3. Display list (Random Order)");
	            System.out.println("4. Quit");
	            System.out.print("Enter choice: ");
	            
	            int choice = -1;
	            try {
	                choice = Integer.parseInt(input.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a number.");
	                continue;
	            }
	            switch (choice) {
                case 1:
                    //display the list in order, front to back
                	/*
                	System.out.println("*** ordered list operations: ***");
                	DLList.add(1);
                	DLList.add(2);
                	DLList.add(3);
                	System.out.println("\nordered list after adding: " + DLList());
                	DLList.remove(1);
                	DLList.remove(2);
                	DLList.remove(3);
                	System.out.println("\nordered list after removing: " + DLList());
                	System.out.println("\nordered list is empty: " + DLList.isEmpty());
                	DLList.add(1);
                	DLList.add(2);
                	DLList.add(3);
                	DLList.add(4);
                	DLList.add(5);
                	DLList.add(6);
                	System.out.println("\nordered list after adding: " + DLList());
                	System.out.println("\nsize of the ordered list: " + DLList.size());
                	System.out.println("\nordered list gets x: " + DLList.get(x));
                	System.out.println("\nordered list contains x: "DLList.contains(x));
                	*/
                    break;
                    
                case 2:
                	//display the list in reverse order
                	/*
                	System.out.println("*** reverse list operations: ***");
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	System.out.println("\nreversed list after adding: " + DLList());
                	DLList.remove();
                	DLList.remove();
                	DLList.remove();
                	System.out.println("\nreversed list after removing: " + DLList());
                	System.out.println("\nreversed list is empty: " + DLList.isEmpty());
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	System.out.println("\nreversed list after adding: " + DLList());
                	System.out.println("\nsize of the ordered list: " + DLList.size());
                	System.out.println("\nreversed list gets x: " + DLList.get(x));
                	System.out.println("\nreversed list contains x: "DLList.contains(x));
                	*/
                	break;
                
                case 3:
                	//display the list in a random order
                	/*
                	System.out.println("*** randomized list operations: ***");
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	System.out.println("\nrandomized list after adding: " + DLList());
                	DLList.remove();
                	DLList.remove();
                	DLList.remove();
                	System.out.println("\nrandomized list after removing: " + DLList());
                	System.out.println("\nrandomized list is empty: " + LDList.isEmpty());
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	DLList.add();
                	System.out.println("\nrandomized list after adding: " + DLList());
                	System.out.println("\nsize of the ordered list: " + DLList.size());
                	System.out.println("\nrandomized list gets x: " + DLList.get(x));
                	System.out.println("\nrandomized list contains x: "DLList.contains(x));
                	*/
                	break;
                case 4:
                	//stops the program
                	running = false;
                    System.out.println("stopping program...");
                    break;
                default:
                    System.out.println("Please enter 1-4.");
	            }
	        }
	        input.close();
	}
}
