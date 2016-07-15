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