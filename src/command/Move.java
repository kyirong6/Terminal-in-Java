package command;

import driver.Directory;
import driver.FileClass;

/**
 * @author c5trania
 *
 */
public class Move extends Copy {
  /**
   * Move command Class, deals with moving files and directories around the
   * system. Extends the copy command class, since a move operation is 
   * essentially a copy command that deletes the source.
   */
  @Override
  public String run(String[] parameters) {
      return super.run(parameters);
  }
  
  @Override
  /**
   * See copy class doc string, moves a file into the working directory
   * with a different name, then deletes the original. Calls the parent
   * copy method.
   * @param oldPath String indicating the name of the file in working directory
   * @param newPath String indicating the name of the new file to be made
   * @return Blank space indicating operation success, or summary of errors
   *         encountered
   */
  protected String addCloneFileBothRelative(String oldPath, String newPath) {
    String result = super.addCloneFileBothRelative(oldPath, newPath);
    if (result.isEmpty()){
      FileClass source = Directory.currentDir.getFile(oldPath);
      Directory sourceDirectory = Directory.currentDir;
      removeOldFile(source, sourceDirectory);
    }
    return result;
  }
  
  @Override
  /**
   * See copy class doc string, moves a file from working directory into
   * another destination folder provided by an absolute path. Calls the parent
   * copy method.
   * @param oldPath String indicating the name of the file in working directory
   * @param newPath String indicating the absolute path to the directory
   * @return Blank space indicating operation success, or summary of errors
   *         encountered
   */
  protected String addCloneFileOldRelative(String oldPath, String newPath) {
    String result = super.addCloneFileOldRelative(oldPath, newPath);
    FileClass source = Directory.currentDir.getFile(oldPath);
    Directory sourceDirectory = Directory.currentDir;
    removeOldFile(source, sourceDirectory);
    return result;
  }
  
  @Override
  /**
   * See copy class doc string, moves a file from absolute path into the 
   * working directory. Calls the parent copy method.
   * @param oldPath String indicating the name of the file from absolute path
   * @param newPath String indicating the new name of the file in working
   *        directory
   * @return Blank space indicating operation success, or summary of errors
   *         encountered
   */
  protected String addCloneFileNewRelative(String oldPath, String newPath) {
    String result = super.addCloneFileNewRelative(oldPath, newPath);
    if (result.isEmpty()){
      Directory sourceDirectory;
      FileClass source;
      String sourceName = 
          oldPath.substring(oldPath.lastIndexOf("/"),
              oldPath.length()).replace("/", "");
      String sourcePath;
      if (oldPath.startsWith("/")){
        source = Directory.rootDir.getFileByPath(oldPath);
        sourcePath = oldPath.substring(0,(oldPath.lastIndexOf(sourceName)));
        sourceDirectory = Directory.rootDir.getDirectoryByPath(sourcePath);
      }
      else{
        source = Directory.currentDir.getFileByPath(oldPath);
        sourcePath = oldPath.substring(0,(oldPath.lastIndexOf(sourceName)));
        sourceDirectory = Directory.currentDir.getDirectoryByPath(sourcePath);
      }
      removeOldFile(source, sourceDirectory);
    }
    return result;
  }
  
  @Override
  /**
   * See copy class doc string, moves a file given from absolute path into a 
   * directory given from absolute path (may have same name).
   * @param oldPath String indicating the path to the source file.
   * @param newPath String indicating the path to the destination.
   * @return Blank space indicating operation success, or summary of errors
   *         encountered
   */
  protected String addCloneFileBothAbsolute(String oldPath, String newPath) {
    String result = super.addCloneFileBothAbsolute(oldPath, newPath);
    if (result.isEmpty()){
      Directory sourceDirectory;
      FileClass source;
      String sourceName = 
          oldPath.substring(oldPath.lastIndexOf("/"),
              oldPath.length()).replace("/", "");
      String sourcePath;
      if (oldPath.startsWith("/")){
        source = Directory.rootDir.getFileByPath(oldPath);
        sourcePath = oldPath.substring(0,(oldPath.lastIndexOf(sourceName)));
        sourceDirectory = Directory.rootDir.getDirectoryByPath(sourcePath);
      }
      else{
        source = Directory.currentDir.getFileByPath(oldPath);
        sourcePath = oldPath.substring(0,(oldPath.lastIndexOf(sourceName)));
        sourceDirectory = Directory.currentDir.getDirectoryByPath(sourcePath);  
      }
      removeOldFile(source, sourceDirectory);
    }
    return result;
  }
 
  @Override
  /**
   * Moves a directory from working directory into another directory (also in
   * working directory)
   * @param oldPath String indicating the name of the source directory
   * @param newPath String indicating the name of the destination
   *        directory
   * @return Blank space indicating operation success, or any errors encountered
   *         during the operation
   */
  protected String addCloneDirBothRelative(String oldPath, String newPath) {
    String result = super.addCloneDirBothRelative(oldPath, newPath);
    if (result.isEmpty()){
      Directory sourceDirectory = Directory.currentDir.getDirectory(oldPath);
      removeOldDirectory(sourceDirectory, Directory.currentDir);
    }
    return result;
  }

  @Override
  /**
   * See copy class doc string, moves a directory from working directory into
   * a directory designated by absolute path.
   * @param oldPath the name of the directory in the working directory
   * @param newPath the destination directory, given from absolute path
   * @return Blank space indicating operation success, or any errors encountered
   *         during the operation
   */
  protected String addCloneDirOldRelative(String oldPath, String newPath) {
    // TODO Auto-generated method stub
    String result  = super.addCloneDirOldRelative(oldPath, newPath);
    if (result.isEmpty()){
      Directory sourceDirectory = Directory.currentDir.getDirectory(oldPath);
      removeOldDirectory(sourceDirectory, Directory.currentDir);
      if (newPath.startsWith("/")){
        Directory.currentDir = Directory.rootDir.getDirectoryByPath(newPath);
      }
      else{
        Directory.currentDir = Directory.currentDir.getDirectoryByPath(newPath);
      }
    }
    return result;
  }

  @Override
  /**
   * See copy class doc string, moves a directory given from absolute path into
   * the working directory.
   * @param oldPath the source directory given from absolute path.
   * @param newPath the directory in the working directory
   * @return Blank space indicating operation success, or any errors encountered
   *         during the operation
   */
  protected String addCloneDirNewRelative(String oldPath, String newPath) {
    // TODO Auto-generated method stub
    String result = super.addCloneDirNewRelative(oldPath, newPath);
    Directory source;
    Directory parent;
    if (result.isEmpty()){
      if (oldPath.startsWith("/")){
        source = Directory.rootDir.getDirectoryByPath(oldPath);
        parent = Directory.rootDir.getDirectoryByPath(source.getParentPath());
      }
      else{
        source = Directory.currentDir.getDirectoryByPath(oldPath);
        parent = Directory.currentDir.getDirectoryByPath(source.getParentPath());
      }
      removeOldDirectory(source, parent);
    }
    return result;
  }

  @Override
  /**
   * See copy class doc string, moves a directory given from absolute path into
   * a destination directory, also provided by an absolute path
   * @param oldPath the source directory, designated by an absolute path
   * @param newPath the destination directory, designated by an absolute path
   * @return Blank space indicating operation success, or any errors encountered
   *         during the operation
   */
  protected String addCloneDirBothAbsolute(String oldPath, String newPath) {
    String result = super.addCloneDirBothAbsolute(oldPath, newPath);
    Directory source;
    Directory parent;
    if (result.isEmpty()){
      if (oldPath.startsWith("/")){
        source = Directory.rootDir.getDirectoryByPath(oldPath);
        parent = Directory.rootDir.getDirectoryByPath(source.getParentPath());
      }
      else{
        source = Directory.currentDir.getDirectoryByPath(oldPath);
        parent = Directory.currentDir.getDirectoryByPath(source.getParentPath());
      }
      Directory.currentDir = Directory.rootDir;
      removeOldDirectory(source, parent);
    }
    return result;
  }
  
  /**
   * Removes the source FileClass from directory sourceDirectory
   * @param source The file to be deleted
   * @param sourceDirectory The directory in which source is located
   */
  private void removeOldFile(FileClass source, Directory sourceDirectory){
    sourceDirectory.removeFile(source.getName());
  }
  /**
   * Removes a directory designated by source from its' parent directory
   * @param source The directory to be removed
   * @param parentDirectory The parent directory of source
   */
  private void removeOldDirectory(Directory source, Directory parentDirectory){
    parentDirectory.removeDirectory(source.getDirectoryName());
  }

  
}
