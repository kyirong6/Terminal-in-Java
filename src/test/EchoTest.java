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