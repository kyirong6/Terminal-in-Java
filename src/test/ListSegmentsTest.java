package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import command.Command;
import command.ListSegments;
import driver.Directory;
import driver.FileClass;

public class ListSegmentsTest {
  
  private Map <String, Command> map;
  private Directory rootDir;
  @Before
  public void setUp(){
    map = new HashMap<String, Command>();
    map.put("ls", new ListSegments());
    
    rootDir = Directory.rootDir;
  }
  
  @Test
  /**
   * Tests ls command when -r is inputted (i.e., recursive retrieval of all
   * directories)
   */
  public void testRecursiveRetrieval() {
    rootDir.addDirectory(new Directory("home", "/home"));
    rootDir.addDirectory(new Directory("school", "/school"));
    rootDir.getDirectory("home").addDirectory(new Directory("room", 
        "/home/room"));
    rootDir.getDirectory("home").addDirectory(new Directory("kitchen",
        "/home/room/kitchen"));
    String output = map.get("ls").run(new String []{"-r"});
    assertEquals(output.trim(), "[[/, home, school], [/home, room, kitchen]]");
  }
  
  @Test
  /**
   * Tests ls command when inputting a relative path.
   */
  public void testFromRelative() {
    Directory testDir = new Directory("folder", "/folder");
    rootDir.addDirectory(testDir);
    rootDir.addFile(FileClass.createFileWithoutContents("newfile.txt"));
    String output = map.get("ls").run(new String []{""});
    assertEquals(output.trim(), "folder  newfile.txt");
  }
  
  @Test
  /**
   * Tests ls command when inputting an absolute path
   */
  public void testFromAbsoluteRoot(){
    Directory testDir2 = new Directory("folder2", "/folder2");
    testDir2.addFile(FileClass.createFileWithoutContents("newfile"));
    testDir2.addDirectory(new Directory("newfolder", "/folder/newfolder"));
    rootDir.addDirectory(testDir2);
    String output = map.get("ls").run(new String []{"/folder2"});
    assertEquals(output.trim(), "newfolder  newfile");
  }
}
