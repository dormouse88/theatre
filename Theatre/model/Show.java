package model;

public class Show {
	private	int id;
	private String title;
	private String type; // (theatre, musical, opera, concert)
	private String description;
	private int duration;
	private String language;
	private String performer;

	public Show(int id, String title, String type, String description, int duration, String language, String performer) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.performer = performer;
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
	public String getPerformer() {
		return performer;
	}
}
