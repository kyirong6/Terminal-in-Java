package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import command.*;
import driver.Directory;

public class PrintWorkingDirectoryTest {
  
  private Map<String, Command> map = new HashMap<String, Command>();;
  
  @Before
  public void setUp(){
    Directory.currentDir = Directory.rootDir;
    map.put("pwd", new PrintWorkingDirectory());
    map.put("cd", new ChangeDirectory());
  }
  
  @Test
  /**
   * Test to check whether pwd returns root directory path properly.
   */
  public void testRoot() {
    String output = map.get("pwd").run(new String[]{});
    assertEquals(output,"/");
  }

  @Test
  /**
   * Test to check whether pwd returns non-root directory paths properly.
   */
  public void testOther() {
    Directory testDir = new Directory("abc", "/abc/");
    Directory.currentDir = testDir;
    String output = map.get("pwd").run(new String[]{});
    assertEquals(output, "/abc");
  }
}
