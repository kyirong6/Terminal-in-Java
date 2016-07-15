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

package driver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * FileClass, class which details the behavior of files, such as returning
 * their content to JShell, or being editable.
 * @author See honor code above
 * 
 */
public class FileClass {
  private String filename; //the name of the file
  //contents of the file
  private ArrayList<String> contents = new ArrayList<String>();
  
  private FileClass(String name, String contents) {
    this.filename = name;
    this.contents.add(contents);
  }
  
  private FileClass(String name) {
    this.filename = name;
  }

  /**
   * Factory method to create a FileClass object with text.
   * @param name The filename
   * @param contents The text inside the file
   * @return FileClass object with content
   */
  public static FileClass createFileWithContents(String name, String contents) {
    return new FileClass(name, contents);
  }

  /**
   * Factory method to create a FileClass object with no content.
   * @param name The filename
   * @return FileClass object with no content
   */
  public static FileClass createFileWithoutContents(String name) {
    return new FileClass(name);
  }
  
  /**
   * Retrieves the content of this FileClass object.
   * @return The text, formatted for JShell
   */
  public String getContent() {
    StringBuilder text = new StringBuilder();
    Iterator<String> contentIterator = this.contents.iterator();
    while (contentIterator.hasNext()) {
      text.append(contentIterator.next() + "\n");
    }
    return text.toString() + "\n\n";
  }
  
  /**
   * Adds text on a newline to the File.
   * @param newContent the text to be added
   */
  public void addContent(String newContent) {
    this.contents.add(newContent);
  }
  
  /**
   * Adds contents to the File but overwrites any existing text.
   * @param newContent the text to be added (and which will overwrite)
   */
  public void overwriteContent(String newContent) {
    this.contents = new ArrayList<String>();
    this.contents.add(newContent);
  }
  
  /**
   * Clones this FileClass object and returns an identical copy, but different
   * reference
   * @return A clone of the file with identical contents and name
   */
  public FileClass clone(){
    if (this.contents.isEmpty()){
      return new FileClass(this.filename);
    }
    else{
      FileClass clonedFile = new FileClass(this.filename);
      clonedFile.contents = new ArrayList<String>(this.contents);
      return clonedFile;
    }
  }
  
  /**
   * Getter method for File name.
   * @return The filename
   */
  public String getName() {
    return this.filename;
  }

  @Override
  public String toString() {
    return this.filename;
  }
  
  public void setName(String name) {
    this.filename = name;
  }


}
