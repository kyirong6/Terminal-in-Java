package test;

import static org.junit.Assert.*;

import command.*;

import driver.JShell;

import org.junit.Before;

import org.junit.Test;

import java.util.HashMap;

import java.util.Map;

public class EchoTest {
  
  /** 
  * A map to store the commands.
  */
  private final Map<String, Command> map = new HashMap<String, Command>();
    
  @Before
  
    
  /** Instantiates Echo class and store in map.
  * Makes 3 Directories for testing.
  *
  */
  public void setUp() {
    map.put("echo", new Echo());
    map.put("cat", new Cat());
    MakeDirectory mkdir = new MakeDirectory();
    mkdir.run(new String[]{"dir1"});
    mkdir.run(new String[]{"dir1/a"});
    mkdir.run(new String[]{"dir1/a/b"});
  }
    
  @Test
    
  /** Tests that Echo.run() will return empty string when
  * correct input is given and an error message otherwise
  */
  public void testForInputs() {
    //test for correct format
    assertEquals((map.get("echo")).run("\"x\" > a".split(" ")), "");
        
    //test for when missing "
    assertEquals((map.get("echo")).run("\"x > a".split(" ")),
                 "Input string is not in correct format");
        
    //test for when missing > and >>
    assertEquals((map.get("echo")).run("\"x\"  a".split(" ")),
                 "invalid input");
        
    //test for when missing target path
    assertEquals((map.get("echo")).run("\"x\" >> ".split(" ")),
                 "invalid input");
        
    //test for when target partial path does not exist
    assertEquals((map.get("echo")).run("\"x\" >> dir2/c".split(" ")),
                 "Directory does not exist");
        
    //test for when target full path does not exist
    assertEquals((map.get("echo")).run("\"x\" >> /dir2/c".split(" ")),
                 "Directory does not exist");
        
    //test for when only a string is given
    assertEquals((map.get("echo")).run("\"testphrase\"".split(" ")),
                 "testphrase");
        
    //test for when only string is given but missing "
    assertEquals((map.get("echo")).run("\"testphrase".split(" ")),
                 "invalid string");
    
    //test for when echo is given relative path
    assertEquals((map.get("echo")).run("\"x\" >> dir1/a/c/x".split(" ")),
                   "");
      
    //test for when echo is given full path
    assertEquals((map.get("echo")).run("\"x\" >> /dir1/a/c/x".split(" ")),
                   "");
  }
    
}