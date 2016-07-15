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

import command.*;

import driver.JShell;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class NumberTest {
  
  /**
   * A map to store the commands.
   */
  private final Map<String, Command> map = new HashMap<String, Command>();
  private History hist;
  
  @Before
  
  public void setUp() {
    map.put("pwd", new PrintWorkingDirectory());
    map.put("!", new command.Number());
    hist = History.createHistory();
    hist.add_cmd("cd");
  }
  
  
  @Test
  
  public void testForError() {
    String[] empty = new String[2];
    empty[0] = "a";
    // Tests for length of input <= 1
    assertEquals("Error Not enough parameters.", map.get("!").run(empty));
    empty[0] = "afdf";
    // Tests for random parameter given
    assertEquals("Error Event not found.", map.get("!").run(empty));
    String[] clone1 = new String[1];
    clone1[0] = "!1";
    // Tests for valid parameter given
    assertEquals("Error Event not found.", map.get("!").run(clone1));
  }
}