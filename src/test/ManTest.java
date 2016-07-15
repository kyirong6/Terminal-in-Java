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
