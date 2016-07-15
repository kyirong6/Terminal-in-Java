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











