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