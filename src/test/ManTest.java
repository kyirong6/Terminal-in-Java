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

package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import command.Command;
import command.Man;

public class ManTest {
  
  private Map<String, Command> map;
  @Before
  public void setUp(){
    map = new HashMap<String, Command>();
    map.put("man", new Man());
  }
  
  @Test
  /**
   * Tests a bad command when inputted to the man command
   */
  public void testUnkownCommand() {
    String output = map.get("man").run(new String[]{"No command"});
    assertEquals(output, "Unknown command");
  }
  
  @Test
  /**
   * Tests that all the command docs are outputted correctly
   */
  public void testManText() {
    String [] commands = new String []{"cat","cd","echo","exit","history","ls",
        "mkdir","popd","pushd","pwd"};
    for(String params : commands){
      String output = map.get("man").run(new String[]{params});
      assertNotEquals(output, "Unknown command");
    }
  }

}
