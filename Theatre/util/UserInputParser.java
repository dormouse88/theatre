package util;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInputParser {

    private Scanner reader;
    
    public UserInputParser() 
    {
        reader = new Scanner(System.in);
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
