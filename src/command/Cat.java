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
// Author: Ngawang Kyirong
//
// Student4:HanChen Shen
// CDF user_name:c5shenhb
// UT Student #:1002155794
// Author: HanChen Shen
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************

package command;

import driver.Directory; 
import driver.FileClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Cat class, oversees the behavior of the cat JShell command, outputs the
 * contents of a file into JShell.
 * @author See honor code above
 *
 */
public class Cat extends Command {

  public Cat() {

  }

  /**
   * Runs the cat command in JShell, which takes in relative paths or absolute
   * paths to a file as parameters, and outputs the file contents to JShell. cat
   * command can have n parameters where n is number of files (relative or
   * absolute). File contents are separated by lines.
   * 
   * @param parameters String array containing the file paths e.g: /folder/file
   * @return Appropriate String if file/directory is not found, or String
   *         representation of file contents.
   */
  public String run(String[] parameters) {
    List<String> allContents = new ArrayList<String>();
    // Checks validity of parameters
    if (parameters.length >= 1) {
      // parameters are filenames or absolute paths to files, goes iterates
      // through each file or path
      for (String params : parameters) {
        // if the parameter is an absolute path
        if (params.contains("/")) {
          if (params.substring(0, 1).equals("/")) { //path starting from root
            allContents.add(this.getFromRootDir(params));
          } else { //user typed absolute path starting from current directory
            allContents.add(this.getFromCurrentDir(params));
          }
          //user expects the file to be inside the current directory
        } else {
          allContents.add(this.getFromRelative(params));
        }
      }
      return this.formatContent(allContents);
    } else {
      return "Error Invalid input, possibly missing arguments";
    }
  }

  /**
   * Gets contents of the file from a path which is starting from a non root
   * directory.
   * 
   * @param path The path from the current directory to the file to the file
   * @return returns the contents of the file at the path, or an error message
   */
  private String getFromCurrentDir(String path) {
    FileClass destination = Directory.currentDir.getFileByPath(path);
    try {
      return destination.getContent();
    } catch (NullPointerException e) {
      return "Error " + path + " > Directory not found \n\n\n";
    }
  }

  /**
   * Gets the contents of the file from the path, which is starting from the
   * root directory.
   * 
   * @param path The path from the root directory to the file to the file
   * @return returns the contents of the file at the path, or an error message
   */
  private String getFromRootDir(String path) {
    FileClass destination = Directory.rootDir.getFileByPath(path);
    try {
      return destination.getContent();
    } catch (NullPointerException e) {
      return "Error " + path + " > Directory not found \n\n\n";
    }
  }

  /**
   * Gets the contents of the file with filename provided it is inside the
   * current working directory.
   * 
   * @param filename The filename (which resides in the current directory)
   * @return returns the contents of the file in the directory, or an error
   *         message
   */
  private String getFromRelative(String filename) {
    FileClass catFile = Directory.currentDir.getFile(filename);
    try {
      return catFile.getContent();
    } catch (NullPointerException e) {
      return "Error " + filename + " > File not found \n\n\n";
    }
  }

  /**
   * Formats the contents of all files inputed to JShell into a form that is
   * neater to output to JShell.
   * 
   * @param fileContents Contents of all files in the command parameters
   * @return Single String object with all file contents, formatted to fit in
   *         JShell.
   */
  private String formatContent(List<String> fileContents) {
    StringBuilder content = new StringBuilder();
    for (String text : fileContents) {
      content.append(text);
    }
    return content.toString();
  }

}
