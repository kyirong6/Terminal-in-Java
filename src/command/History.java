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