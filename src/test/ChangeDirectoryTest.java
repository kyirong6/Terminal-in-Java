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

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import command.*;
import driver.Directory;

public class ChangeDirectoryTest {

  /** 
   * A map to store the commands.
   */
   private Map<String, Command> map; 
   @Before
   public void setUp(){
     Directory.currentDir = Directory.rootDir;
     map = new HashMap<String, Command>();
     map.put("cd", new ChangeDirectory());
   }
   @Test   
   /** 
    * Tests that cd returns an error properly when a directory is not located
    * at the given path.
    */
   public void testNotADirectory() {
     String output = map.get("cd").run(new String []{"NoDir"});
     assertEquals(output, "NoDir: Not a directory.");
   }
   
   @Test   
   /** 
    * Tests that cd works properly with inputs not involving "." or ".."
    */
   public void testNormal() {
     Directory testDir = new Directory("folder", "/folder");
     Directory.rootDir.addDirectory(testDir);
     map.get("cd").run(new String[]{"folder"});
     assertEquals(Directory.currentDir.getDirectoryName(), "folder");
   }
   
   @Test
   /**
    * Tests whether cd works properly with input ".." (i.e.
    * parent access)
    */
   public void testParent() {
     map.get("cd").run(new String[] {".."});
     assertEquals(Directory.currentDir.getDirectoryName(), "root");
   }
   
   @Test
   /**
    * Tests whether cd works properly with input "." (i.e 
    * current directory access)
    */
   public void testCurrent() {
     Directory testDir2 = new Directory("folder2", "/folder2");
     Directory testDir3 = new Directory("folder3", "/folder2/folder3");
     testDir2.addDirectory(testDir3);
     Directory.rootDir.addDirectory(testDir2);
     map.get("cd").run(new String[]{"folder2"});
     assertEquals(Directory.currentDir.getDirectoryName(), "folder2");
     map.get("cd").run(new String[]{"folder3"});
     assertEquals(Directory.currentDir.getDirectoryName(), "folder3");
     map.get("cd").run(new String[]{"."});
     assertEquals(Directory.currentDir.getDirectoryName(), "folder3");
   }
   
   @Test
   /**
    * Tests whether cd works properly with absolute path
    * as an input
    */
   public void testAbsolute(){
     Directory testDir4 = new Directory("folder4", "/folder4");
     Directory testDir5 = new Directory("folder5", "/folder4/folder5");
     testDir4.addDirectory(testDir5);
     Directory.rootDir.addDirectory(testDir4);
     map.get("cd").run(new String[]{"/folder4/folder5"});
     assertEquals(Directory.currentDir.getDirectoryName(), "folder5");
   }
}
