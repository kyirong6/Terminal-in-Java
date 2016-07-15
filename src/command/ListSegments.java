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

package command;

import java.util.List;

import driver.Directory;

import driver.FileClass;

/**
 * ListSegments Class, oversees the behavior and execution of ls command in 
 * JShell which returns the contents of a file or directory.
 * @author See honor code above
 *
 */
public class ListSegments extends Command {
     
  public ListSegments() {
        
  }
    
  /**
  * Runs the ls command in JShell. Takes no path or one path. If no path is
  * given, returns the contents of the current file or directory. If a path
  * is given, returns the contents of the file or directory at the path.
  * @param parameters String array containing the file path, if there is one.
  * @return Appropriate String if file/directory is not found, or String.
  */
    
  public String run(String[] parameters) {
    String output = "";
    // checks if there are too many arguments in the input
    if (parameters.length > 2) {
      return "Error Too many arguments.";
    }
    
    // if ls is called without accessing the root or current directory
    if (parameters.length == 0) {
      return normalAccess();
    // if ls is called with -r
    } else if (parameters[0].indexOf("-") == 0 && (parameters[0].indexOf("r")==1
        || parameters[0].indexOf("R")==1)){
      if (parameters.length == 1) {
        return Directory.currentDir.getAllDirectories().toString();
      }
      else {
        return recursiveRetrieval(parameters[1]);
      }
      // if ls is called while accessing the root directory
    } else if (parameters[0].indexOf("/") == 0) {
      return rootAccess(parameters[0]);
      // if ls is called while accessing the current directory
    } else {
      return currentAccess(parameters[0]);
    }
  }
    
  private boolean toLowerCase(int indexOf) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
  * Helper method for the ls command in JShell. Called for cases where ls
  * is called such that it accesses the root directory.
  * @param parameters String array containing the file path, if there is one.
  * @return Appropriate String if file/directory is not found, or String.
  */
  private String rootAccess(String input) {
    String output = "";
    // checks whether the path leads to a directory
    if (Directory.rootDir.getDirectoryByPath(input + "/")
        instanceof Directory) {
      // uses for loops to create String of directory names to return
      for (Directory i : Directory.rootDir.getDirectoryByPath(
          input + "/").getDirectories()) {
        output += i.getDirectoryName() + "  ";
      }
      for (FileClass i : Directory.rootDir.getDirectoryByPath(
          input + "/").getFiles()) {
        output += i.getName() + "  ";
      }
      return output;
      // checks if the path leads to a file, returns input path if so
    } else if (Directory.rootDir.getFileByPath(input + "/")
               instanceof FileClass) {
      return input;
    }
    // error message
    return "Error No such file or directory";
  }
    
  /**
  * Helper method for the ls command in JShell. Called for cases where ls
  * is called such that it accesses the current directory.
  * @param parameters String array containing the file path, if there is one.
  * @return Appropriate String if file/directory is not found, or String.
  */
  private String currentAccess(String input) {
    String output = "";
    // checks whether the path leads to a directory
    if ((Directory.currentDir.getDirectoryByPath(
        input + "/") instanceof Directory)) {
      // uses for loops to create String of directory names to return
      for (Directory i : Directory.currentDir.getDirectoryByPath(
          input + "/").getDirectories()) {
        output += i.getDirectoryName() + "  ";
      }
      for (FileClass i : Directory.currentDir.getDirectoryByPath(
          input + "/").getFiles()) {
        output += i.getName() + "  ";
      }
      return output;
      // checks if the path leads to a file, returns input path if so
    } else if (Directory.currentDir.getFileByPath(
        input + "/") instanceof FileClass) {
      return input;
    }
    // error message
    return "Error No such file or directory";
  }
    
  /**
  * Helper method for the ls command in JShell. Called for cases where ls
  * is called such that it does not access the root directory or the current
  * directory.
  * @param parameters String array containing the file path, if there is one.
  * @return Appropriate String if file/directory is not found, or String.
  */
  private String normalAccess() {
    // uses for loops to create String of directory names to return
    String output = "";
    for (FileClass file : Directory.currentDir.getFiles()) {
      output += file.getName() + "  ";
    }
    for (Directory dir : Directory.currentDir.getDirectories()) {
      output += dir.getDirectoryName() + "  ";
    }
    return output;
  }
  
  /**
   * Helper method for the ls command in JShell. Called for cases -r is called
   * and a directory path is given as a following argument. Finds the given
   * directory and calls the getAllDirectories() method on said directory to
   * retrieve all directories and sub-directories of the current directory, and
   * their sub-directories if there are any.
   * @param String A string representation of the path of the directory.
   * @return String A String representation of a list of lists, with each sub-
   *                list formatted as ['Directory path', sub-directory 2,
   *                sub-directory 2, ...]
   */
  private String recursiveRetrieval(String path) {
    Directory target = Directory.rootDir.getDirectoryByPath(path);
    return target.getAllDirectories().toString();
  }
}
