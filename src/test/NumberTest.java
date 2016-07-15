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