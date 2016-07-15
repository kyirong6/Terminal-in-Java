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

import command.Echo;

import driver.Directory;
import driver.JShell;
import driver.FileClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.net.URLConnection;
import java.net.URL;
import java.net.MalformedURLException;




public class Get extends Command {
  

  public Get() {

  }
  
  /**
   * Runs the get command which retrieves the file at that URL and adds it to
   * the current working directory.
   * @param parameters A String array representing the tokens of the input
   * @return appropriate String message from the command running the given inputs
   */

  public String run(String[] parameters) {
    if (parameters.length == 0) {
      return "Error Missing a parameter.";
    } else {
      String url = parameters[0];
      try {
        // Creates a URL object from the string rep. of the url that the user inputted.
        URL myurl = new URL(url);
        Echo echo = new Echo();
        // Using BufferedReader on an InputStream of the URL file.
        BufferedReader in = new BufferedReader(new InputStreamReader(myurl.openStream()));
        StringBuilder html = new StringBuilder();
        // Appending every line of the file into a string
        for (String line; (line = in.readLine()) != null; ) {
          html.append(line);
        }
        // Closing the BufferedReader
        in.close();
        Integer type = url.lastIndexOf(".");
        Integer dash = url.lastIndexOf("/");
        // the String[] is created to act as paramters of the Echo command.
        String[] list = {"\"" + html.toString() + "\"", ">>", url.substring(dash, url.length())};
        // Using the echo command to create/overwrite a file with the contents of the URL.
        return echo.run(list);
      } catch (MalformedURLException e) {
        return "Error connecting to URL";
      } catch (IOException j) {
        return "Error while opening stream";
      }
    }
  }
}











