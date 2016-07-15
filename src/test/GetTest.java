package test;

import static org.junit.Assert.*;

import command.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetTest {
  
  /**
   * A map to store the commands.
   */
  
  private final Map<String, Command> map = new HashMap<String, Command>();
  
  
  @Before
  
  // Instantiates the Get command in the map.
  public void setup() {
    map.put("get", new Get());
  }
  
  
  @Test
  
  /** Tests that Get will return appropriate
   * messages for certain input
   */
  
  public void testForError() {
    String[] empty = new String[]{};
    // Test to see if not enough parameters are given
    assertEquals("Error Missing a parameter.",map.get("get").run(empty));
    String[] empty2 = new String[1];
    empty2[0] = "dasdsad";
    // Tests to see if url given is not a working url
    assertEquals("Error connecting to URL", map.get("get").run(empty2));
    String[] url = new String[1];
    // Tests to see if url is good and runs completely.
    url[0] = "http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html";
    assertEquals("", map.get("get").run(url));
  }
}