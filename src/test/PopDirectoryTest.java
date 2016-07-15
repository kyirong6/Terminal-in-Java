package test;

import command.MakeDirectory;

import command.PopDirectory;

import command.PrintWorkingDirectory;

import command.PushDirectory;

import driver.JShell;

import java.util.HashMap;

import java.util.Map;

import org.junit.*;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;


public class PopDirectoryTest {
    
  /**
  * A map to store the commands.
  */
  private final Map<String, Command> map = new HashMap<String, Command>();
    
  @Before
    
  /** Instantiates PopDirectory class and stores in map.
  * Also creates 4 directories for testing purposes
  */
    
  public void setUp() {
    map.put("pushd", new PushDirectory());
    map.put("pwd", new PrintWorkingDirectory());
    map.put("popd", new PopDirectory());
    MakeDirectory mkdir = new MakeDirectory();
    mkdir.run(new String[]{"dir1"});
    mkdir.run(new String[]{"dir1/a"});
    mkdir.run(new String[]{"dir1/a/b"});
    mkdir.run(new String[]{"dir1/a/b/c"});
  }
    
  @Test
    
  /** Tests that the command popd changes directory
  * to the proper directory.
  */
    
  public void testForCorrectDirectoryChange() {
    String[] empty = new String[]{};
    map.get("pushd").run(new String[]{"/dir1/a/b"});
    assertEquals("/dir1/a/b", map.get("pwd").run(empty));
    assertEquals("/", map.get("popd").run(empty));
  }
    
  @Test
    
  /** Tests that the command popd returns the proper
  * error message if there is no directory in stack.
  */

  public void testForError() {
    String[] empty = new String[]{};
    assertEquals("Error stack empty", map.get("popd").run(empty));
  }
}