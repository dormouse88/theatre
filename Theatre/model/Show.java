package model;

import java.util.ArrayList;

public class Show {
	private	int id;
	private String title;
	private String type; // (theatre, musical, opera, concert)
	private String description;
	private int duration;
	private String language;
	private ArrayList<String> performers;

	public Show(int id, String title, String type, String description, int duration, String language, ArrayList<String> performers) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.performers = performers;
	}
	
	public void print() {
		System.out.println("Title: "+ title);
		System.out.println("Type: "+ type);
		System.out.println("Description: "+ description);
		System.out.println("Duration: "+ duration + " minutes.");
		System.out.println("Language: "+ language);
		if (performers != null) {
			System.out.print("Music by: ");
			for (String p: performers) {
				System.out.print(p + ", ");
			}
			System.out.println("");
		}
	}

	public int getID() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	public int getDuration() {
		return duration;
	}
	public String getLanguage() {
		return language;
	}
	public ArrayList<String> getPerformers() {
		return performers;
	}
}
