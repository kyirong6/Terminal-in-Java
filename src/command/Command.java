package command;

abstract public class Command {
    
  public Command() {

  }
  //run method is unique to all subclasses. Checks if the input is correct.

  abstract public String run(String[] parameters);
}































