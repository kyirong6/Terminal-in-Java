package test;

import static org.junit.Assert.*;
import org.junit.Test; // for @Test
import org.junit.Before; // for @Before

import org.junit.Test;
import driver.*;
import command.Grep;

public class GrepTest {
  Grep grep;
  @Before
  public void setUp() {
    grep = new Grep();
  }
  @Test
  public void test() {
    String[] params = new String[]{"[a]"};
    assertEquals("Error invalid number of parameters",this.grep.run(params));
    params = new String[]{"[a]", "file.txt"};
    assertEquals("Error file Does not exist",this.grep.run(params));
    params = new String[]{"-R","[a]", "Dir"};
    assertEquals("Error the directory does not exist",this.grep.run(params));
  }	
  
}