package test;

import driver.JShell;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

import command.*;

public class HistoryTest {
    
  /** A private History object.
  */
  private History hist;

  @Before
  
    
  /** Instantiates a new History object and add commands to it.
  */
  public void setUp() {
    hist = new History();
    hist.add_cmd("cd");
    hist.add_cmd("man");
    hist.add_cmd("ls");
    hist.add_cmd("pwd");
    hist.add_cmd("pushd");
    hist.add_cmd("popd");
    hist.add_cmd("cd..");
    hist.add_cmd("cd direct1");
  }


  @Test

  public void test() {
    //test for when no parameter is given
    assertEquals(hist.run(new String[]{}), "1       cd\n2       man\n3       ls\n"
            + "4       pwd\n5       pushd\n6       popd\n7       cd..\n8       cd direct1\n");
        
    //test for when correct parameter is given
    assertEquals(hist.run(new String[]{"3"}), "6       popd\n7       cd..\n8       cd direct1\n");
        
    //test that when parameter number exceeds number
    //of commands history all commands in history is printed
    assertEquals(hist.run(new String[]{"140000"}), "1       cd\n2       man\n3       ls\n"
            + "4       pwd\n5       pushd\n6       popd\n7       cd..\n8       cd direct1\n");
        
    //test for when incorrect format is given
    assertEquals(hist.run(new String[]{"wrongInput"}),
                 "invalid input, parameter should be a number");
  }
    
}