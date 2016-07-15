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







public class PushDirectoryTest {
    
  /**
  * A map to store the commands.
  */
  private final Map<String, Command> map = new HashMap<String, Command>();
  
  @Before
    
  /** Instantiates PushDirectory class and store in map.
  * Makes 4 directories for testing purposes
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
    
  /** Tests that the command pushd changes directory
  * properly to the given directory and if an error
  * messages is returned if given a directory that
  * that does not exist or no directory given.
  */
    
  public void testForCorrectChangeDirectory() {
    String[] empty = new String[]{};
    assertEquals("/dir1/", map.get("pushd").run("dir1".split(" ")));
    assertEquals("/dir1", map.get("pwd").run(empty));
        
    assertEquals("/dir1/a/b/c/", map.get("pushd").run("/dir1/a/b/c".split(" ")));
    assertEquals("/dir1/a/b/c", map.get("pwd").run(empty));
    
    assertEquals("/dir1/", map.get("popd").run(empty));
    assertEquals("/", map.get("popd").run(empty));
        
    assertEquals("error: Not a directory.",JShell.processOutput(map, "pushd error".split(" ")));
    assertEquals("Error no other directory", JShell.processOutput(map, "pushd".split(" ")));
  }
}


