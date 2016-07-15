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

import command.ChangeDirectory;

import driver.Directory;

import driver.JShell;

import driver.FileClass;

import java.util.List;
import java.util.Stack;




public class PushDirectory extends Command {


  public PushDirectory() {

  }
    
  /**
  * Runs the pushd command which changes directory into the given direct-
  * ory and saves the previous directory where pushd was used.
  * @param parameters A String array representing the tokens of the input
  * @return appropriate message from the command running the given inputs
  */
    
  public String run(String[] parameters) {
    //Checks if there is a paramter given
    if (parameters.length == 0) {
      return "Error no other directory";
    } else {
      // Sets a pointer to your current directory and pushes it onto stack
      Directory reference = Directory.currentDir;
      JShell.terminalStack.push(reference);
      // Uses cd command to go into the new directory given
      ChangeDirectory cd = new ChangeDirectory();
      String[] value = {parameters[0]};
      String var = cd.run(value);
      if (!var.equals("cd")) {
        String message = parameters[0] + ": " + "Not a directory.";
        return message;
      } else {
        return Directory.currentDir.getPath();
      }
    }
  }
}