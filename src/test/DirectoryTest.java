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

import org.junit.Test;

import driver.Directory;

public class DirectoryTest {
  
  private Directory rootDir;
  
  @Test
  /**
  * Tests that getDirectoryName works properly.
  */
  public void testGetDirectoryName() {
    rootDir = new Directory("root", "/");
    assertEquals(rootDir.getDirectoryName(), "root");
  }
  
  @Test
  /**
   * Tests that addDirectory works properly.
   */
  public void testAddDirectory() {
    rootDir = new Directory("root", "/");
    rootDir.addDirectory(new Directory("desk", "/"));
    assertEquals(rootDir.getDirectory("desk").getDirectoryName(), "desk");
  }
  
  @Test
  /**
   * Tests that getDirectories works properly, i.e. returns a non-empty list
   * of directories.
   */
  public void testGetDirectories() {
    rootDir = new Directory("root", "/");
    rootDir.addDirectory(new Directory("desk", "/"));
    assertNotEquals(rootDir.getDirectories().toString(), "[]");
  }
  
  @Test
  /**
   * Tests that getDirectoryByPath works properly, returning the correct
   * directory.
   */
  public void testGetDirectoryByPath() {
    rootDir = new Directory("root", "/");
    rootDir.addDirectory(new Directory("desk", "/"));
    rootDir.getDirectory("desk").addDirectory(new Directory
        ("pencil", "/desk/pencil"));
    assertEquals(rootDir.getDirectoryByPath("/desk/pencil").getDirectoryName(),
        "pencil");
  }
  
  @Test
  /**
   * Tests whether removedDirectory works, i.e. the directory is successfully
   * removed and returned.
   */
  public void testRemoveDirectory() {
    rootDir = new Directory("root", "/");
    rootDir.addDirectory(new Directory("desk", "/"));
    Directory removedDirectory = rootDir.removeDirectory("desk");
    assertEquals(removedDirectory.getDirectoryName(), "desk");
    assertEquals(rootDir.getDirectories().toString(), "[]");
  }

}
