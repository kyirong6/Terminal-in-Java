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

/**
 * PrintWorkingDirectory Class, oversees the behavior and execution of pwd 
 * command in JShell which returns the path of the current working directory.
 * @author See honor code above
 *
 */
public class PrintWorkingDirectory extends Command {
  
  
  public PrintWorkingDirectory() {

  }
  
  /**
  * Runs the pwd command in JShell. Should take no extra arguments to function.
  * Prints the path of the current working directory.
  * @param parameters String array containing extra arguments.
  * @return String representation of the directory's path.
  */
  public String run(String[] parameters) {
    // checks if argument count is 0
    if (parameters.length != 0) {
      return "Error invalid input, shouldnt have any parameters";
    } else {
      String output = Directory.currentDir.getPath();
      // if current directory is the root directory, immediately returns input
      if (Directory.currentDir == Directory.rootDir) {
        return output;
        // if current directory is not the root directory, cuts off the / at
        // the end of the input.
      } else {
        output = output.substring(0, output.length() - 1);
        return output;
      }
    }
  }
}