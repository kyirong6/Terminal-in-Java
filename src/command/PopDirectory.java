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

import driver.JShell;

import driver.FileClass;

import java.util.Stack;


public class PopDirectory extends Command {

  public PopDirectory() {
 
  }
    
  /**
  * Runs the popd command which changes directory into the last saved
  * directory from the pushd command and removes it.
  * @param parameters A String array representing the tokens of the input
  * @return appropriate message from the command running
  */
    
  public String run(String[] parameters) {
    // Checks if the stack is empty
    if (JShell.terminalStack.isEmpty() == true) {
      return "Error stack empty";
    } else {
      // Sets the current directory to the popped directory from the stack
      Directory.currentDir = JShell.terminalStack.pop();
      return Directory.currentDir.getPath();
    }
  }
}