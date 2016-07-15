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
