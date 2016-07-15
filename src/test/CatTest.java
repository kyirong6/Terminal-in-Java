package test;

import static org.junit.Assert.*;

import command.*;
import driver.Directory;
import driver.FileClass;
import driver.JShell;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CatTest {
  
  private Map<String, Command> map; 
  
  @Before
  /**
   * Setup the cat command every test.
   */
  public void setUp() {
    map = new HashMap<String, Command>();
    map.put("cat", new Cat());
  }
  
  @Test
  /**
   * Tests the cat command when given a relative path.
   */
  public void testRelativePath(){
    FileClass testFile = FileClass.createFileWithContents("file.txt", "Hello");
    Directory rootTest = Directory.rootDir;
    rootTest.addFile(testFile);
    String output = map.get("cat").run(new String []{"file.txt"});
    assertEquals(output.trim(), "Hello");
  }
  
  @Test
  /**
   * Tests the cat command when given an absolute path
   */
  public void testAbsolutePath(){
    //set up a file inside "folder" directory, which is in the root directoy
    FileClass testFile = FileClass.createFileWithContents("file.txt", "Hello");
    Directory rootTest = Directory.rootDir;
    Directory folderTest = new Directory("folder", "/folder");
    rootTest.addDirectory(folderTest);
    folderTest.addFile(testFile);
    String output = map.get("cat").run(new String []{"/folder/file.txt"});
    assertEquals(output.trim(), "Hello");
  }
}
