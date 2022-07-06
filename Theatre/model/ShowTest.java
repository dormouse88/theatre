package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author soumyakcherian
 *
 */
class ShowTest {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}

	@Test
	void testShow() {
		
			int id = 1;
			String title= "testtitle";
			String type = "test type";
			String description = "description of show";
			int duration = 120;
			String language = "english";
			String performer = "Performer1";
			Show show = new Show(id,title,type,description,duration,language,performer); 
			assertTrue(show!=null, " Verify Show object not null.");
			assertEquals(id, show.getID(),"Verify getID");
			assertEquals(title,show.getTitle(),"Verify getTitle");
		    assertEquals(type,show.getType(),"Verify getType");
			assertEquals(description,show.getDescription(),"Verify getDescription");
			assertEquals(duration, show.getDuration(), "Verify getDuration");
			assertEquals(language, show.getLanguage(), "Verify getLanguage");
			assertEquals(performer,show.getPerformer(), "Verify getPerformer");
		
	}

	@Test
	void testPrint() {
		int id = 1;
		String title= "testtitle";
		String type = "test type";
		String description = "description of show";
		int duration = 120;
		String language = "english";
		String performer = "Performer1";
		Show show = new Show(id,title,type,description,duration,language,performer); 
		show.print();
		assertTrue(outputStreamCaptor.toString().contains(title), " Verify title is printed out.");
		assertTrue(outputStreamCaptor.toString().contains(description), " Verify description is printed out");
		assertTrue(outputStreamCaptor.toString().contains(Integer.toString(duration)), " Verify duration is printed out");
		assertTrue(outputStreamCaptor.toString().contains(language), " Verify language is printed out");
		assertTrue(outputStreamCaptor.toString().contains(performer), " Verify performer name is printed out");
	}

}
