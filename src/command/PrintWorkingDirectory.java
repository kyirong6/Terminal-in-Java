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