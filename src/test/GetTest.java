// **********************************************************
// Assignment2:
// Student1:Ian Tran
// CDF user_name:c5trania
// UT Student #:1002359119
// Author:Ian Tran
//
// Student2:Shuhan Zheng
// CDF user_name:c5zhengs
// UT Student #:1002599333
// Author:Shuhan Zheng
//
// Student3:Ngawang Kyirong
// CDF user_name:c5kyiron
// UT Student #:1001237967
// Author:
//
// Student4:HanChen Shen
// CDF user_name:c5shenhb
// UT Student #:1002155794
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************

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