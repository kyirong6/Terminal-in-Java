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
