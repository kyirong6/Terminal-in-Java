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