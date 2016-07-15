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

/**
 * ChangeDirectory, oversees the behavior and execution of cd command in 
 * JShell which changes the current working directory to a directory at a given
 * path.
 * @author See honor code above
 *
 */
public class ChangeDirectory extends Command {
    
  public ChangeDirectory() {
        
  }
    
  /**
  * Runs the cd command in JShell. Takes no path or one path. If no path is
  * given does nothing. If a path is given, checks whether that a directory
  * located by input path exists and if it does, changes the current
  * directory to the directory at that path.
  * @param parameters String array containing the file path, if there is one
  *                   in the input.
  * @return Appropriate String if directory is not found, or String cd
  *         if found.
  */
  public String run(String[] parameters) {
    // checks if too many arguments are in the input
    if (parameters.length > 1) {
      return "Error Too many arguments.";
    }
    // if input directory is current directory
    if (parameters.length == 0) {
      Directory.currentDir = Directory.rootDir;
      return "";
  
    } else {
      // if getting parent directory
      if (parameters[0].equals("..")) {
        getParentDirectoryHelper(parameters);
        return "";
      } else if (parameters[0].equals(".")) {
        // if getting current directory
        return "";
      } else {
        // if input is not any of the previous cases
        return changeDirectoryHelper(parameters);
      }
    }
  }
    
  /**
  * Helper method for run method. Handles several cases for the
  * ChangeDirectory command if ".." is the input.
  * @param parameters String array containing the file path, if there is one
  *     in the input.
  * @return None
  */
  private void getParentDirectoryHelper(String[] parameters) {
    // retrieves parent directory if it exists
    Directory temp = Directory.rootDir.getDirectoryByPath(
            Directory.currentDir.getPath().replace(Directory.currentDir
                                              .getDirectoryName() + "/", ""));
    // if parent directory doesn't exist
    if (temp == null) {
      String path = Directory.currentDir.getPath().replace(Directory
               .currentDir.getDirectoryName(), "");
      Directory.currentDir = Directory.rootDir.getDirectoryByPath(
           path.substring(1,path.length() - 1));
    } else {
      // if parent directory exists, sets it to parent directory
      Directory.currentDir = temp;
    }
  }
    
  /**
  * Helper method for run method. Handles several cases for the
  * ChangeDirectory command depending on input, excluding "..".
  * @param parameters String array containing the file path, if there is one
  *                   in the input.
  * @return Appropriate String if directory is not found, or String cd
  *         if found.
  */
  private String changeDirectoryHelper(String[] parameters) {
    // if only / is entered, sets current directory to root directory if it
    // exists.
    if (parameters[0].substring(0,1).equals("/") && parameters[0]
          .length() == 1) {
      Directory.currentDir = Directory.rootDir;
      return "";
    }
      
    // if / and other characters are entered, checks if such a path exists
    // and sets current directory to it if such a path does exist.
    if (parameters[0].substring(0,1).equals("/") && Directory.rootDir
          .getDirectoryByPath(parameters[0]) != null) {
      Directory.currentDir = Directory.rootDir.getDirectoryByPath(parameters[0]);
      return "";
          
      // if the same input as the previous case is entered but with a /
      // character at the end of the input path. Checks and sets current
      // directory to input path if path exists.
    } else if (!parameters[0].substring(0,1).equals("/")
               && Directory.currentDir
               .getDirectoryByPath(parameters[0] + "/") != null) {
      Directory.currentDir = Directory.currentDir.getDirectoryByPath(
                parameters[0] + "/");
      return "";
        
    } else {
      // error message, there is no directory at the input path.
      String message = "Error " + parameters[0] + ": " + "Not a directory.";
      return message;
    }
  }
}