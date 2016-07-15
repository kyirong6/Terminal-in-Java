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