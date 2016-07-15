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

import java.util.*;

import driver.*;

/**
 * MakeDirectory Class, oversees the behavior and execution of mkdir command in 
 * JShell which creates new directories.
 * @author See honor code above
 *
 */
public class MakeDirectory extends Command {
  public MakeDirectory() {
        
  }
    
  /**
  * Runs the mkdir command in JShell. Creates a new directory or directories
  * with the input Strings as names if such directories of the same name do
  * not already exist in the given paths.
  * @param parameters String array containing String representations of the
  *                   to-be-made directories' names and paths.
  * @return Appropriate String if path is not found, or blank line String.
  */
  public String run(String[] parameters) {
    // checks if correct number of arguments is given
    if (parameters.length < 1) {
      return "Error invalid input, should have atleast 1 parameter";
      // when there is only one directory to make
    } else if (parameters.length == 1) {
      return makeDirectoryHelper(parameters);
      // when there are multiple directories to be made
    } else {
      return makeMultipleDirectoryHelper(parameters);
    }
  }
    
  /**
  * Helper function the mkdir command in JShell. Handles cases where a single
  * directory is to be made.
  * @param parameters String array containing String representations of the
  *                   to-be-made directories' names and paths.
  * @return Appropriate String if path is not found, or blank line String.
  */
  private String makeDirectoryHelper(String[] parameters) {
    String[] inputs = parameters[0].split("/");
    String fileName = inputs[inputs.length - 1];
    inputs = Arrays.copyOf(inputs, inputs.length - 1);
    String path = "";
    // concactenates path together
    for (String i : inputs) {
      path += i + "/";
    }
    // when a name, not a path, is given
    if (inputs.length == 0) {
      return inputNotPathHelper(inputs, fileName);
    }
    // when full path is given
    if (path.substring(0,1).equals("/")) {
      return inputFullPathHelper(inputs, fileName, parameters, path);
      // when neither a name nor a full path is given
    } else {
      return inputOtherPathHelper(inputs, fileName, parameters, path);
    }
  }
    
  /**
  * Helper function the mkdir command in JShell. Handles cases where multiple
  * directories are to be made.
  * @param parameters String array containing String representations of the
  *                   to-be-made directories' names and paths.
  * @return Appropriate String if path is not found, or blank line String.
  */
  private String makeMultipleDirectoryHelper(String[] parameters) {
    for (String parameter : parameters) {
      String[] inputs = parameter.split("/");
      String fileName = inputs[inputs.length - 1];
      inputs = Arrays.copyOf(inputs, inputs.length - 1);
      String path = "";
      // concactenates path together
      for (String i : inputs) {
        path += i + "/";
      }
      // when a name, not a path, is given
      if (inputs.length == 0) {
        if (Directory.currentDir.getDirectoryByPath(fileName) == null) {
          Directory.currentDir.addDirectory(new Directory(
              fileName, Directory.currentDir.getPath() + fileName + "/"));
        } else {
          return "Error Directory already exists";
        }
        // continues the method so loop functions properly
        continue;
      }
      // when full path is given
      if (path.substring(0,1).equals("/")) {
        return inputFullPathHelper(inputs, fileName, parameters, path);
        // when neither a name nor a full path is given
      } else {
        return inputOtherPathHelper(inputs, fileName, parameters, path);
      }
    }
    return "";
  }
    
  /**
  * Helper function the mkdir command in JShell. Used in makeDirectoryHelper,
  * handles cases when a name rather than a path is given.
  * @param inputs Modified array of Strings based on input with '/''s taken
  *     out.
  * @param fileName Intended String name for the directory to be created.
  * @return Appropriate String if path is not found, or blank line String.
  */
  private String inputNotPathHelper(String[] inputs, String fileName) {
    if (inputs.length == 0) {
      // checks if a directory of the same name already exists in given path.
      // if not, creates the directory.
      if (Directory.currentDir.getDirectoryByPath(fileName) == null) {
        Directory.currentDir.addDirectory(new Directory(
            fileName, Directory.currentDir.getPath() + fileName + "/"));
        // if directory does already exists, returns error message.
      } else {
        return "Error Directory already exists";
      }
      return "";
    }
    return "";
  }
    
  /**
  * Helper function the mkdir command in JShell. Used in makeDirectoryHelper
  * and makeMultipleDirectoryHelper, handles cases when a full path is given.
  * @param inputs Modified array of Strings based on input with '/''s taken
  *     out.
  * @param fileName Intended String name for the directory to be created.
  * @param parameters String array containing String representations of the
  *                   to-be-made directories' names and paths.
  * @param path Intended String representation of the path fin whch the
  *             directory is to be created.
  * @return Appropriate String if path is not found, or blank line String.
  */
  private String inputFullPathHelper(String[] inputs, String fileName,
        String[] parameters, String path) {
    if (path.substring(0,1).equals("/")) {
      inputs = Arrays.copyOf(inputs, inputs.length - 1);
      Directory temp = Directory.rootDir.getDirectoryByPath(path);
      // checks if given directory path exists, if so, creates directory
      Directory possibleError = Directory.rootDir.getDirectoryByPath(parameters[0]);
      if (temp != null &&  possibleError == null) {
        temp.addDirectory(new Directory(fileName, temp.getPath() + fileName + "/"));
        // if directory path doesn't exist, returns error message
      } 
      else if (temp != null && possibleError != null) {
        return "Error Directory already exists";
      } else {
        return "Error no such directory exists";
      }
    }
    return "";
  }
    
  /**
  * Helper function the mkdir command in JShell. Used in makeDirectoryHelper
  * and makeMultipleDirectoryHelper, handles cases where neither a name nor
  * full path is given.
  * @param inputs Modified array of Strings based on input with '/''s taken
  *     out.
  * @param fileName Intended String name for the directory to be created.
  * @param parameters String array containing String representations of the
  *     to-be-made directories' names and paths.
  * @param path Intended String representation of the path fin whch the
  *             directory is to be created.
  * @return Appropriate String if path is not found, or blank line String.
  */
  private String inputOtherPathHelper(String[] inputs,
                                      String fileName, String[] parameters, String path) {
    inputs = Arrays.copyOf(inputs, inputs.length - 1);
    Directory temp = Directory.currentDir.getDirectoryByPath(path);
    // checks if given directory path exists, if so, creates directory
    if (temp != null && Directory.currentDir.getDirectoryByPath(parameters[0]) == null) {
      temp.addDirectory(new Directory(fileName, temp.getPath() + fileName + "/"));
      // if directory path doesn't exist, returns error message
    } else {
      return "Error no such directory exists";
    }
    return "";
  }
}