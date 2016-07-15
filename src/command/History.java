package command;

import java.util.ArrayList;


/** Represents a History command
*/
public class History extends Command {

  /**
  * The history of all commands used.
  */
  private ArrayList<String> history;
  
  /**
   * The singleton instance of History.
   */
  private static History ref = null;
  
    
  /**
  * Instantiate a History object with an empty ArrayList
  * for storing the history.
  *
  * @return     void
  */
  private History() {
    history = new ArrayList<String>();
  }

  /**
  * Return a String of a list of commands that was used previously.
  * The parameter must be an integer otherwise return error message.
  *
  *
  * @param  parameters     a list of String parameters
  * @return                A list of commands or an error message
  */
  public String run(String[] parameters) {
    if (parameters.length == 1) {
      try {
        int numOfHistories = Integer.parseInt(parameters[0]);
        String hist = new String();
        int count;
        //return the history of the past [m] commands
        if (history.size() >=  numOfHistories) {
          count = history.size() - numOfHistories;
        } else {
          count = 0;
        }
        return getHistory(count);
      } catch (NumberFormatException notint) {
        return "Error invalid input, parameter should be a number";
      }
    } else if (parameters.length == 0) {
      //return the history of the past 5 commands
      return getHistory(0);
    } else {
      return "Error invalid input, should only have 1 or no parameters";
    }
  }

  /**
  * Return a String of a list of commands that was used previously.
  * The number of commands included in the list is based on the
  * parameter.
  *
  * @param    count   number of commands to include in the list
  * @return           A list of commands or an error message
  */
  private String getHistory(int count) {
    String hist = new String();
    for (int i = count; i < history.size(); i++) {
      hist += (Integer.toString(i + 1) + "       " + history.get(i) + "\n");
    }
    return hist;
  }

  /**
  * Add a command to the history of commands.
  *
  * @param    command    the command to add to the history of commands
  * @return              void
  */
  public void add_cmd(String command) {
    history.add(command);
  }

  
  /**
   * Creates a new instance of History as ref and returns it if one does not
   * already exist, or retrieves it if one already exists. Singleton design 
   * pattern.
   *
   * @return History the singleton instance of the History class.
   */
  public static History createHistory() {
    if (ref == null) {
      ref = new History();
    }
    return ref;
  }
  
  public ArrayList<String> getHistoryList() {
    return this.history;
  }
}