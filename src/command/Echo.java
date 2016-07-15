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

import driver.Directory;

import driver.FileClass;

import driver.JShell;

import java.util.Arrays;


/** Represents an Echo command
*/
public class Echo extends Command {
    
  public Echo() {
        
  }
 
  /** Based on the input parameters either return the input string or insert
  * input string into a file at a given path.
  *
  * @param      parameters an Array of parameters used to determine what the method should do
  * @return                empty String or error message or input String
  */
  public String run(String[] parameters) {
    if (parameters.length == 3) {
      if (parameters[1].equals(">") || parameters[1].equals(">>")) {
        String content;
        String[] inputs = parameters[2].split("/");
        String fileName = inputs[inputs.length - 1];
        inputs = Arrays.copyOf(inputs, inputs.length - 1);
        String path = listToPath(inputs);
        if (isValidString(parameters[0])) {
          content = parameters[0].substring(1,parameters[0].length() - 1);
        } else {
          return "Error Input string is not in correct format, should have be surrounded \"\" ";
        }
        if (inputs.length == 0
            && createFileInCurrentDirectory(parameters[2], content, fileName).equals("")) {
          return "";
        }
        String result = updateOrCreateFileWithPath(parameters[2],
                                                      path, content, fileName, parameters[1]);
        return result;
      }
      return "Error invalid input";
    } else if (parameters.length == 1) {
      if (isValidString(parameters[0])) {
        return parameters[0].substring(1,parameters[0].length() - 1);
      }
      return "Error invalid string";
    }
    return "Error invalid input";
  }
 
  /** Takes the input array of string and create a path where every element
  * in the input is a directory.
  *
  *
  * @param      inputs  an array of string to be turned into a path
  * @return             a String that is path
  */
  private String listToPath(String[] inputs) {
    String path = "";
    for (String i : inputs) {
      path += i + "/";
    }
    return path;
  }

  /** Takes in an string as a parameter and outputs whether or not
  * the string has the correct format of being surrounded by "".
  *
  * @param     input A string to be tested
  * @return    bool - whether the input string is the correct format
  */
  private boolean isValidString(String input) {
    if ((input.indexOf("\"") == 0)
        && (input.lastIndexOf("\"") == input.length() - 1)) {
      return true;
    }
    return false;
  }

  /** Either append to or replace the contents at the given filePath, if the the file
  * does not exist then create a new file with the given fileName.
  *
  * @param filePath      The path of the file, could be full or relative
  * @param content       The content to be added to the file
  * @param locationPath  The path of the directory that the file is situated in
  * @param fileName      The name of the file
  * @param action        The action to perform, > represents to replace, >> represents to append
  * @return              empty string or an error message
  */
  private String updateOrCreateFileWithPath(String filePath,
        String locationPath, String content, String fileName, String action) {
    if (!filePath.substring(0,1).equals("/")) {
      if (Directory.currentDir.getFileByPath(filePath) != null) {
        addOrReplaceContentAtFile(Directory.rootDir.getFileByPath(filePath), action, content);
        return "";
      } else if (Directory.currentDir.getDirectoryByPath(filePath) != null){
        return "Error A directory with the same name exists at given path";
      } else if (Directory.currentDir.getDirectoryByPath(locationPath) != null) {
        FileClass file = FileClass.createFileWithContents(fileName,content);
        Directory.currentDir.getDirectoryByPath(locationPath).addFile(file);
        return "";
      }
      return "Error Directory does not exist";
    } else {
      if ((Directory.rootDir.getFileByPath(filePath)) != null) {
        addOrReplaceContentAtFile(Directory.rootDir.getFileByPath(filePath), action, content);
        return "";
      } else if (Directory.currentDir.getDirectoryByPath(filePath) != null){
          return "Error A directory with the same name exists at given path";
      } else if (Directory.rootDir.getDirectoryByPath(locationPath) != null) {
        FileClass file = FileClass.createFileWithContents(fileName,content);
        Directory.rootDir.getDirectoryByPath(locationPath).addFile(file);
        return "";
      }
      return "Error Directory does not exist";
    }
  }

  /** Create a new file in the current working Directory with the given
  * file name and content.
  *
  * @param    filePath    The path of the file, could be full or relative
  * @param    content     The content to be added to the file
  * @param    fileName    The name of the file
  * @return               empty String or error message
  */
  private String createFileInCurrentDirectory(String filePath, String content, String fileName) {
    if (Directory.currentDir.getDirectoryByPath(filePath) != null){
      return "Error A directory with the same name exists at given path";
    }
    if (Directory.currentDir.getFileByPath(filePath) == null) {
      FileClass file = FileClass.createFileWithContents(fileName,content);
      Directory.currentDir.addFile(file);
      return "";
    }
    return "Error file alreadyExists";
  }

  /** Append contents to a file or replace the contents in a file with the given content
  * based on the action given. 
  * If action is > then write over the file
  * If action is >> then append to the file
  *
  * @param    file      A file to append to or write over
  * @param    action    The action to perform, > represents to replace, >> represents to append
  * @param    content   The content to add to the file
  * @return             void
  */
  private void addOrReplaceContentAtFile(FileClass file, String action, String content) {
    if (action.equals(">")) {
      file.overwriteContent(content);
    } else {
      file.addContent(content);
    }
  }
    
}