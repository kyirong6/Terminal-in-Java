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

import driver.FileClass;

import org.junit.Before;
import org.junit.Test;

public class FileClassTest {
  private FileClass fileWithContent;
  private FileClass fileNoContent;
  
  @Before
  /**
   * Set up 2 files, one with content
   */
  public void setUp() {
    fileWithContent = FileClass.createFileWithContents("txt1", "HelloWorld");
    fileNoContent = FileClass.createFileWithoutContents("txt2");
  }
  
  @Test
  /**
   * Tests that filenames are accurate.
   */
  public void testGetFileName() {
    assertEquals(fileWithContent.getName(), "txt1");
    assertEquals(fileNoContent.getName(), "txt2");
  }

  @Test
  /**
   * Tests that a File that has content has the proper text and that
   * the File with no content has no text.
   */
  public void testFileContent() {
    assertEquals(fileWithContent.getContent().trim(), "HelloWorld");
    assertEquals(fileNoContent.getContent().trim(), "");
  }
  
  @Test
  /**
   * Tests that adding text works properly, does not overwrite but appends.
   */
  public void testAddContent() {
    fileWithContent.addContent("test");
    assertEquals(fileWithContent.getContent().trim(), "HelloWorld\ntest");
    fileNoContent.addContent("test");
    assertEquals(fileNoContent.getContent().trim(), "test");
  }
  
  @Test
  /**
   * Tests that adding content by overwriting works properly, if file has no
   * content should just create text within the file.
   */
  public void testOverwriteContent() {
    fileWithContent.overwriteContent("test");
    assertEquals(fileWithContent.getContent().trim(), "test");
    fileNoContent.overwriteContent("test");
    assertEquals(fileNoContent.getContent().trim(), "test");
  }
  

}
