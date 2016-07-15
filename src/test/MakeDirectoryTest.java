package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import command.*;
import driver.Directory;

public class MakeDirectoryTest {

  /**
   * A map to store the commands.
   */
  private Map<String, Command> map;

  @Before

  public void setUp() {
    map = new HashMap<String, Command>();
    map.put("mkdir", new MakeDirectory());
  }
  
  @Test
  /**
   * Tests whether or not multiple directories can be made properly.
   */
  public void testMultiple() {
    // i.e., "mkdir a b c d e" then check with ls?
    map.get("mkdir").run(new String[] {"c", "d"});
    assertEquals(Directory.rootDir.getDirectories().size(), 3);
  }

  @Test
  /**
   * Basic test to check whether a directory can be made as an immediate sub
   * directory.
   */
  public void testImmediate() {
    // can use check name/ls/etc to assert. immediate as in, just "mkdir a"
    map.get("mkdir").run(new String[] {"a"});
    assertEquals(Directory.rootDir.getDirectory("a").getDirectoryName(), "a");
  }

  @Test
  /**
   * Test to see whether a directory can be made based on a given path.
   */
  public void testByPath() {
    // i.e., make directories a/b/c and check whether or not can "mkdir a/b/c/d"
    map.get("mkdir").run(new String[] {"/b"});
    assertEquals(
        Directory.rootDir.getDirectoryByPath("/b").getDirectoryName(), "b");
  }

  @Test
  /**
   * Tests to see if an error message is returned properly if a given path for a
   * directory to be made does not exist.
   */
  public void testByPathError() {
    // i.e., make folders a/b and check whether or not can "mkdir a/b/c/d"
    String output = map.get("mkdir").run(new String[] {"/404/nofolder"});
    assertEquals(output.trim(), "no such directory exists");
  }
}
