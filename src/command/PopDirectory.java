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