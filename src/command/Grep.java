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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Grep class, oversees the behavior of the Grep JShell command.
 * @author See honor code above
 *
 */
public class Grep extends Command {

  public Grep() {

  }

  /**
   * Runs the grep command in JShell, which takes in a 
   * REGEX and searches for that REGEX in a file or
   * all the files in a directory recursively.
   *
   * @param parameters String array of the parameters given by the user
   * @return the location of the REGEX(line number) in the file if its found
   */
  public String run(String[] parameters) {
    if (parameters[0].equals("-r") || parameters[0].equals("-R")) {
      if (Directory.rootDir.getDirectoryByPath(parameters[2]) instanceof Directory) {
        return searchInAllFilesInDirectory(parameters[1], parameters[2]);
      }
      return "Error the directory does not exist";
    } else {
      if (Directory.rootDir.getFileByPath(parameters[1]) instanceof FileClass ) {
        System.out.println(parameters[0]);
        return findRegexInFile(parameters[1], parameters[0]);
        
      } else {
        return "Error file Does not exist";
      }
    }
    
  }
  
  /** Given a filePath and a REGEX, find all the lines in which the REGEX is present
  *   in the file.
  *
  * @param    filePath The path of the file to be searched in
  * @param    regex    The REGEX to be searched for
  * @return   String   The string output containing all the lines in which the REGEX is found.
  */
  private String findRegexInFile(String filePath, String regex) {
    String output = "";
    FileClass file = Directory.rootDir.getFileByPath(filePath);
    String content = file.getContent();
    String[] contentByLine = content.substring(0,content.length() - 3).split("\n");
    for (String line: contentByLine) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(line);
      if (matcher.find()) {
        output += filePath + ": " + line + "\n";
      }
    }
    return output;
  }
  
  /** Given the path of a directory and a regex, search for the regex in every
  *   file in the directory recursively.
  *
  * @param    regex   The REGEX to be searched for
  * @param    path    The path of the directory to be searched in recursively
  * @return   String  The String output containing all the lines in which the REGEX is found
  */
  private String searchInAllFilesInDirectory(String regex, String path) {
    Directory direct = Directory.rootDir.getDirectoryByPath(path);
    String output = "";
    for (FileClass file: direct.getFiles()) {
      if (path.equals("/")) {
        output += findRegexInFile(path + file.getName(), regex);
      } else {
        output += findRegexInFile(path + "/" + file.getName(), regex);
      }
    }
    for (Directory temp: direct.getDirectories()) {
      output += searchInAllFilesInDirectory(regex,
                    temp.getPath().substring(0, temp.getPath().length() - 1));
    }
    return output;
  }

}
