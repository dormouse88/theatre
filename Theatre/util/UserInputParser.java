package util;

import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/**
 * This is a class to facilitate the collection of different types of user text input.
 * @author dor
 */
public class UserInputParser {

    private Scanner reader;
    
    public UserInputParser() 
    {
        reader = new Scanner(System.in);
    }

    /**
     * Reads a string from the user and returns it.
     */
	public String getString(String prompt) {
		if (prompt != null && !prompt.isBlank()) {
			System.out.println(prompt);
			System.out.print("> ");
		}
        String inputLine = reader.nextLine();
		return inputLine;
	}
	
	/**
	 * Reads an integer from the user and returns it. 
	 * Returns 0 in the event of an invalid input.
	 */
	public int getInt(String prompt) {
		if (prompt != null && !prompt.isBlank()) {
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
        }
    	return input;
	}
	
	/**
	 * Reads a date from the user in yyyy-mm-dd format.
	 * Throws an exception if the input cannot be interpreted as a date.
	 */
	public LocalDate getDate() throws DateTimeParseException {
		System.out.println("Please enter the date in yyyy-mm-dd format.");
		System.out.print("> ");
		String input = reader.nextLine();
		LocalDate date = LocalDate.parse(input);
		return date;
	}
}
