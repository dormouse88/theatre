package util;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

//import model.Command;

public class UserInputParser {

    private Scanner reader;
//    private CommandWords commands;
    
    public UserInputParser() 
    {
//        commands = new CommandWords();    	
        reader = new Scanner(System.in);
    }
	
	public int getCommand() {
		System.out.println("1 - List Shows");
		System.out.println("2 - List Shows By Title"); //{title}
		System.out.println("3 - List Shows By Date"); //{date}
		System.out.println("4 - View Show Details"); //{show_index}
		System.out.println("5 - List Performances"); //{show_index}
		System.out.println("6 - Make Purchase");
		System.out.println("7 - Add A Performance To Basket"); // {perf_index} {stalls} {adults} {kids}
		System.out.println("8 - View Basket");
		System.out.println("9 - Enter Customer Details"); // {name} {address} {cc}
		System.out.println("100 - quit");
		System.out.println();
        System.out.print("Choose an option: > ");
        
        int input = getInt("");
        if (input != 100 && input > 9)
        {
            input = 0;
        }
        return input;
	}
	
	public String getString(String prompt) {
		if (prompt.length() > 0) {
			System.out.println(prompt);
			System.out.print("> ");
		}
        String inputLine = reader.nextLine();
		return inputLine;
	}
	
	public int getInt(String prompt) {
		if (prompt.length() > 0) {
			System.out.println(prompt);
			System.out.print("> ");
		}
        int input = 0;
        try {
        	input = reader.nextInt();
        }
        catch (InputMismatchException e) {} //input stays as 0
        finally {
        	reader.nextLine();
        	return input;
        }
	}
}




//Scanner tokenizer = new Scanner(inputLine);
//while (tokenizer.hasNext()) {
//	parameters.add( tokenizer.next() );
//}

//for (String s: parameters) {
//	System.out.println(s);
//}
