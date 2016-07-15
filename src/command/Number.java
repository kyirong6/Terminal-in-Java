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

import command.History;

import driver.Directory;

import driver.JShell;

import driver.FileClass;



public class Number extends Command {
  // Regex for one or more digits
  private String regex = "\\d+";
  
  
  public Number() {
      
  }
  
  /**
   * Runs the ! command which recalls and runs a command retrieved from History.
   * @param parameters A String array representing the tokens of the input
   * @return appropriate String message from the command running the given inputs
   */
  
  public String run(String[] parameters) {
    if (parameters[0].length() <= 1) {
      return "Error Not enough parameters.";
    } else {
        String param = parameters[0].substring(1);
        // Using regular expressions to check for digits
        if ((!param.matches(regex)) || (param.indexOf("0")==0) || (Integer.parseInt(param) >= History.createHistory().getHistoryList().size())) {
          return "Error Event not found.";
        } else {
            Integer historyNumber = Integer.parseInt(param);
            // A list of string to use for the input of the history command.
            String[] parts = History.createHistory().getHistoryList().get(historyNumber-1).split("\\s+");
            // Returns the output of the given command from the history of commands
            return History.createHistory().getHistoryList().get(historyNumber-1) + "\n" + JShell.processOutput(History.createHistory().getHistoryList().get(historyNumber-1));
        }
    }
  }
}