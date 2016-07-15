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

package driver;


import command.Cat;
import command.ChangeDirectory;
import command.Command;
import command.Copy;
import command.Echo;
import command.Exit;
import command.Get;
import command.Grep;
import command.History;
import command.ListSegments;
import command.MakeDirectory;
import command.Man;
import command.Move;
import command.Number;
import command.PopDirectory;
import command.PrintWorkingDirectory;
import command.PushDirectory;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class JShell {
  public final static Directory root = new Directory("root", "/");
  public static Directory currentDirectory = root;
  public static Stack<Directory> terminalStack = new Stack<>();
  private static Map<String, Command> map = new HashMap<String, Command>();
  

  public static void main(String[] args) {
    initilize();
  }
    
  /**
  * Run specific commands given by the user and give
  * specific output based on the users input.
  * @return   void
  */
  public static void initilize() {
    map.put("exit", new Exit());
    map.put("mkdir", new MakeDirectory());
    map.put("ls", new ListSegments());
    map.put("pwd", new PrintWorkingDirectory());
    map.put("pushd", new PushDirectory());
    map.put("history", History.createHistory());
    map.put("echo", new Echo());
    map.put("man", new Man());
    map.put("cd", new ChangeDirectory());
    map.put("popd", new PopDirectory());
    map.put("cat", new Cat());
    map.put("get", new Get());
    map.put("!", new Number());
    map.put("grep", new Grep());
    map.put("cp", new Copy());
    map.put("mv", new Move());
    String output = "";
    
    while (!output.equals("exit")) {
      Scanner userInputScanner = new Scanner(System.in);
      System.out.print("/# ");
      String input = userInputScanner.nextLine().trim();
      String[] parts = input.split("\\s+");
      ((History) map.get("history")).add_cmd(input);

      output = processOutput(input);
      if (output.equalsIgnoreCase("exit")) {
        userInputScanner.close();
        System.out.println("bye");
      } else {
        if (output.split(" ")[0].equals("Error")) {
          System.out.println(output.substring(6));
        } else if (!output.equals("")) {
          System.out.println(output);
        }
      }
    }
  }
  
  /**
  * Processes the input and outputs specific data
  * based on what initilize() inputs.
  * @return String
  */
  public static String processOutput(String params) {
    try {
      if (params.split("\\s+")[0].indexOf("!") == 0) {
        return map.get("!").run(params.split("\\s+"));
      }
      return checkIfRedirect(params);
    } catch (NullPointerException error) {
      return "Command not recognized";
    }
  }
  
  /** Takes in a Command and checks to see if its output should be displayed on the screen
  *   or if it should be saved into a file.
  * @param  params    The Command inputted by the user.
  * @return String    An empty string if the output is redirected into 
                      a file or an Error message or                
                      the actual output of the command if no redirection is indicated
  */
  private static String checkIfRedirect(String params) {
    String[] command;
    String output;
    String action = checkAction(params.split("\\s+"));
    if (params.contains("echo")) {
      output = getOutput(params);
      return output;
    }
    if (action.equals(">")) {
      command = params.split(">");
      output = getOutput(command[0]);
      if (!output.equals("") && !checkIfError(output)) {
        map.get("echo").run(new String[]{"\"" + output + "\"",">",command[1].trim()});
      }
      return "";
    } else if (action.equals(">>")) {
      command = params.split(">>");
      output = getOutput(command[0]);
      if (!output.equals("") && !checkIfError(output)) {
        map.get("echo").run(new String[]{"\"" + output + "\"",">>",command[1].trim()});
      }
      return "";
    } else {
      output = getOutput(params);
    }
    return output;
  }
  
  /**  Get the output received from calling a certain command.
  *    Some commands do not have an output such as cp, but they 
  *    could have an error message.
  *
  *
  * @param     input    The command inputted by the user
  * @return    String   The output of a command
  */
  private static String getOutput(String input) {
    return map.get(input.split("\\s+")[0])
    .run(Arrays.copyOfRange(input.split(" "), 1, input.split("\\s+").length));
  }
  
  /** Takes the user input and determine if any redirection is needed.
  *   if a > is present then return >, if a >> is present then return a
  *   >>, if neither is present then return an empty string.
  *
  *
  * @param    input   An array of strings of the user input
  * @return   String  An action, > for redirecting into a file(overwrites), 
              >> for redirecting into a file(adding to) or "" for a regular
              command.
  */
  private static String checkAction(String[] input) {
    String action = "";
    for (String i : input) {
      if (i.equals(">")) {
        action = ">";
        break;
      } else if (i.equals(">>")) {
        action = ">>";
        break;
      }
    }
    
    return action;
  }
  
  /** Take the output from a command class and determine if an error
  *   message is present or not.
  *
  *
  * @param   output   the output from a command class
  * @return  boolean  whether or not an error message is returned
  */
  private static boolean checkIfError(String output) {
    String[] outputs = output.split(" ");
    if (output.equals("")) {
      return false;
    } else if (outputs[0].equals("Error")) {
      return true;
    } else {
      return false;
    }
  }
}