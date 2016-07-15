package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Man Class, oversees the behaviour and execution of man command in JShell
 * which provides documentation on each command available. (Prints description
 * as well as example of usage).
 * @author See honor code above
 *
 */
public class Man extends Command {

  public Man() {

  }
  /**
   * Runs the man command in JShell, which displays documentation of
   * JShell commands in the form of a description, followed by an example
   * command with parameters. Documentation can be found in JShellDoc.txt
   * @param parameters A String array representing the tokens of the input
   * @return the documentation of the command
   */
  
  public String run(String[] parameters) {
    if (parameters.length != 1) {
      return "Error invalid input, should have only 1 parameter";
    } else {
      // show the doc for the command, read from text file JShellDoc.txt
      try {
        Scanner docs = new Scanner(new File("JShellDoc.txt"));
        StringBuilder toCmd = new StringBuilder();
        while (docs.hasNextLine()) {
          if (docs.nextLine().equals(parameters[0])) {
            toCmd.append(docs.nextLine() + "\n");
            toCmd.append(docs.nextLine());
            docs.close();
            return toCmd.toString();
          }
        }
        docs.close();
        return "Error Unknown command";
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        return "Error failed";
      }
    }
  }
}
