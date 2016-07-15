package command;


/** An object for the exit command
*/
public class Exit extends Command {

  public Exit() {
 
  }

  /** If no parameter is given then returns "exit", otherwise
  * returns error message.
  *
  * @return String  the string "exit" or an error message
  */
  public String run(String[] parameters) {
    if (parameters.length != 0) {
      return "Error invalid input, shouldnt have any parameters";
    } else {
      return "exit";
    }
  }
}