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


import java.util.Stack;

/*import org.junit.internal.Throwables;*/

import driver.Directory;
import driver.FileClass;

/**
 * @author c5trania
 *
 */
public class Copy extends Command {
  /**
   * Object representing "cp" command, which copies files and directories in
   * the system.
   */

  @Override
  public String run(String[] parameters) {
    // TODO Auto-generated method stub
    if (parameters.length == 2) {
      String oldPath = parameters[0];
      String newPath = parameters[1];
      String sourcePath = checkSourcePath(oldPath);
      String destinationPath = checkDestinationPath(sourcePath, newPath);
      switch (sourcePath + " " + destinationPath){
        case "FileClass relative FileClass relative":
          return addCloneFileBothRelative(oldPath, newPath);
        case "FileClass relative FileClass absolute":
          return addCloneFileOldRelative(oldPath, newPath);
        case "FileClass absolute FileClass relative":
          return addCloneFileNewRelative(oldPath, newPath);
        case "FileClass absolute FileClass absolute":
          return addCloneFileBothAbsolute(oldPath, newPath);
        case "Directory relative Directory relative":
          return addCloneDirBothRelative(oldPath, newPath);
        case "Directory relative Directory absolute":
          return addCloneDirOldRelative(oldPath, newPath);
        case "Directory absolute Directory relative":
          return addCloneDirNewRelative(oldPath, newPath);
        case "Directory absolute Directory absolute":
          return addCloneDirBothAbsolute(oldPath, newPath);
          
      }
      return "";
    }
    else{
      return "Invalid Parameters";
    }
  }
  
  /**
   * Checks the source path a user inputs, returns details concerning type
   * and path.
   * @param path The path to the Directory or file the user wants to copy 
   * @return returns a string describing the nature of the source, eg type
   * of the source as well as whether it is relatively located or absolutely
   * located
   */
  protected String checkSourcePath(String path){
    Stack <Object> sources = new Stack<Object>();
    FileClass relativeF = Directory.currentDir.getFile(path);
    FileClass absoluteFromCurrentF = Directory.currentDir.getFileByPath(path);
    FileClass absoluteFromRootF = Directory.rootDir.getFileByPath(path);
    Directory relativeD = Directory.currentDir.getDirectory(path);
    Directory absoluteFromCurrentD = 
        Directory.currentDir.getDirectoryByPath(path);
    Directory absoluteFromRootD =
        Directory.rootDir.getDirectoryByPath(path);
    sources.push(relativeF);
    sources.push(absoluteFromCurrentF);
    sources.push(absoluteFromRootF);
    sources.push(relativeD);
    sources.push(absoluteFromCurrentD);
    sources.push(absoluteFromRootD);
    while (!sources.isEmpty()){
      Object sourceType = sources.pop();
      if (sourceType != null){
        if (path.contains("/"))
          return sourceType.getClass().getSimpleName() + " absolute";
        else 
          return sourceType.getClass().getSimpleName() + " relative";
      }
    }
    return "Error Invalid Parameter(s).";
  }
  
  /**
   * Checks the destination (given from user input) and returns details to
   * describe the type of operation (e.g copying FileClass into Directory, both
   * from absolute paths).
   * @param source The type of the source
   * @param path The destination path, where the user wants to put the copied
   * object
   * @return Details regarding the type of destination, and the type of path
   */
  protected String checkDestinationPath (String source, String path){
    if (!path.contains("/")){
      if (source.contains("FileClass"))
        return "FileClass relative";
      else
        return "Directory relative";
    }
    else if (path.contains("/")){
      if (source.contains("FileClass"))
        return "FileClass absolute";
      else if (source.contains("Directory"))
        return "Directory absolute";
    }
    return "Error Invalid Parameter(s)";
  }


  /**
   * Copies a file in the working directory and creates one with the same name
   * or a different filename.
   * @param oldPath name of the file in current working directory.
   * @param newPath name of the new file (clone of original) to be created
   *        in the working directory.
   * @return Error message depending on any issues (invalid filename)
   */
  protected String addCloneFileBothRelative(String oldPath, String newPath){
    try{
      FileClass cloneFile = Directory.currentDir.getFile(oldPath).clone();
      cloneFile.setName(newPath);
      if (!isNameTaken(Directory.currentDir, newPath))
        Directory.currentDir.addFile(cloneFile);
      else
        return "Name already exists in destination.";
      return "";
    } catch(NullPointerException e){
      return "Error Invalid Parameter(s).";
    }
  }
  
  /**
   * Clones a file from the working directory into a new destination, which is
   * designated by path.
   * @param oldPath The name of the file in the current working directory
   * @param newPath The destination folder for the new file
   * @return Any error messages related to the file names or destination names
   */
  protected String addCloneFileOldRelative (String oldPath, String newPath)
  {
    try{
      FileClass clonedFile = Directory.currentDir.getFile(oldPath).clone();
      String newFileName = 
          newPath.substring(newPath.lastIndexOf("/"),
              newPath.length()).replace("/", "");
      if (newFileName.equals("")){
        newFileName = clonedFile.getName();
      }
      String filePath = newPath.substring(0,newPath.lastIndexOf("/"));
      clonedFile.setName(newFileName);
      Directory destinationFromRoot = 
          Directory.rootDir.getDirectoryByPath(filePath);
      Directory destinationFromCurrent =
          Directory.currentDir.getDirectoryByPath(filePath);
      if (destinationFromCurrent == null && destinationFromRoot == null)
        return "Error Bad path.";
      else if (destinationFromCurrent != null){
        if (!isNameTaken(destinationFromCurrent, clonedFile.getName()))
          destinationFromCurrent.addFile(clonedFile);
        else
          return "Name already exists in destination";
      }
      else if (destinationFromCurrent == null && destinationFromRoot != null){
        if (!isNameTaken(destinationFromRoot, newFileName))
          destinationFromRoot.addFile(clonedFile);
        else
          return "Name already existsin destination";
      }
      return "";
    }catch (NullPointerException e){
      return "Invalid Parameter(s)";
    }catch (StringIndexOutOfBoundsException s){
      return "Invalid Parameter(s)";
    }
  }
  
  /**
   * Clones a file from a path and copies the clone into the working directory
   * with (potentially) a new name.
   * @param oldPath The path to the file that needs to be copied
   * @param newPath The new (or same) name of the file to be put in the working
   *        directory.
   * @return Blank string if the operation is successful, otherwise any error
   *         messages that arose from the operation.
   */
  protected String addCloneFileNewRelative (String oldPath, String newPath){
    try{
      FileClass clonedFile;
      if (oldPath.startsWith("/")){
        clonedFile = Directory.rootDir.getFileByPath(oldPath).clone();
        clonedFile.setName(newPath);
        if (isNameTaken(Directory.currentDir, clonedFile.getName()))
          return "Name already exists in destination";
        else
          putFileInDirectory(clonedFile, Directory.currentDir);
      }
      else if (!oldPath.startsWith("/")){
        clonedFile = Directory.currentDir.getFileByPath(oldPath).clone();
        clonedFile.setName(newPath);
        if (isNameTaken(Directory.currentDir, clonedFile.getName()))
          return "Name already exists in destination.";
        else 
          putFileInDirectory(clonedFile, Directory.currentDir);
      }
      return "";
    }catch(NullPointerException e){
      return "Error Invalid Parameter(s)";
    }
  }
  
  /**
   * Clones a File taken from an absolute path and puts that clone into another
   * directory, as a file with (potentially) a new name.
   * @param oldPath The path to the file that needs to be copied
   * @param newPath The full path for the clone to be placed
   * @return Blank string if operation is successful, otherwise an
   *         appropriate error message.
   */
  protected String addCloneFileBothAbsolute (String oldPath, String newPath){
    try{
      String result = "";
      String newFileName = 
          newPath.substring(newPath.lastIndexOf("/"), 
              newPath.length()).replace("/", "");
      String destinationPath = newPath.substring(0,newPath.lastIndexOf("/"));
      FileClass cloneFile;
      if (oldPath.startsWith("/")){
        cloneFile = Directory.rootDir.getFileByPath(oldPath).clone();
        cloneFile.setName(newFileName);
        result = checkDestinationType(cloneFile, destinationPath);
      } 
      else if (!oldPath.startsWith("/")){
        cloneFile = Directory.currentDir.getFileByPath(oldPath).clone();
        cloneFile.setName(newFileName);
        result = checkDestinationType(cloneFile, destinationPath);
      }
      return result;
    } catch (NullPointerException e){
      return "Error Invalid Parameter(s).";
    } catch (StringIndexOutOfBoundsException s){
      return "Error Invalid Parameter(s).";
    }
  }

  /**
   * Copies a directory (including all contents) into another existing
   * directory.
   * 
   * @param oldPath The relative path the user wants to copy from
   * @param newPath The relative destination path for the copied folder
   * @return Blank String for confirmation or message containing details on
   *         input errors
   */
  protected String addCloneDirBothRelative(String oldPath, String newPath) {
    try {
      Directory clonedDir = Directory.currentDir.getDirectory(oldPath).clone();
      Directory destination = Directory.currentDir.getDirectory(newPath);
      if (isNameTaken(destination, oldPath)) {
        return "Name already exists in destination.";
      }
      Directory.currentDir.getDirectory(newPath).addDirectory(clonedDir);
      return "";
    } catch (NullPointerException e) {
      return pathCheck(oldPath, newPath);
    }
  }

  /**
   * Copies a directory (including all contents) into another existing
   * directory.
   * 
   * @param oldPath The relative path the user wants to copy from
   * @param newPath The absolute destination path for the copied folder
   * @return Blank String for confirmation or message containing details on
   *         input errors
   */
  protected String addCloneDirOldRelative(String oldPath, String newPath) {
    try {
      Directory clonedDir = Directory.currentDir.getDirectory(oldPath).clone();
      Directory destination;
      if (newPath.startsWith("/")) {
        destination = Directory.rootDir.getDirectoryByPath(newPath);
        if (isNameTaken(destination, clonedDir.getDirectoryName()))
          return "Name already exists in destination.";
        destination.addDirectory(clonedDir);
      } else if (!newPath.startsWith("/")) {
        destination = Directory.currentDir.getDirectoryByPath(newPath);
        if (isNameTaken(destination, clonedDir.getDirectoryName()))
          return "Name already exists in destination.";
        destination.addDirectory(clonedDir);
      }
      return "";

    } catch (NullPointerException e) {
      return pathCheck(oldPath, newPath);
    }
  }

  /**
   * Copies a directory (including all contents) into another existing
   * directory.
   * 
   * @param oldPath The absolute path the user wants to copy from
   * @param newPath The relative destination path for the copied folder
   * @return Blank String for confirmation or message containing details on
   *         input errors
   */
  protected String addCloneDirNewRelative(String oldPath, String newPath) {
    try {
      Directory clonedDir;
      Directory destination = Directory.currentDir.getDirectory(newPath);
      if (oldPath.startsWith("/")){
        clonedDir = Directory.rootDir.getDirectoryByPath(oldPath).clone();
        if (isNameTaken(destination, clonedDir.getDirectoryName()))
          return "Name already exists in destination.";
        putDirectoryInDirectory(clonedDir, destination);
      }else if (!oldPath.startsWith("/")){
        clonedDir = Directory.currentDir.getDirectoryByPath(oldPath).clone();
        if (isNameTaken(destination, clonedDir.getDirectoryName()))
          return "Name already exists in destination.";
        putDirectoryInDirectory(clonedDir, destination);
      }
      return "";
    } catch (NullPointerException e) {
      return pathCheck(oldPath, newPath);
    }
  }
  
  /**
   * Copies a directory (including all contents) into another existing
   * directory.
   * 
   * @param oldPath The absolute path the user wants to copy from
   * @param newPath The absolute destination path for the copied folder
   * @return Blank String for confirmation or message containing details on
   *         input errors
   */
  protected String addCloneDirBothAbsolute(String oldPath, String newPath) {
    try {
       Directory source;
       Directory destination;
       if (Directory.rootDir.getDirectoryByPath(oldPath) == null){
         source = Directory.currentDir.getDirectoryByPath(oldPath).clone();
         if (Directory.rootDir.getDirectoryByPath(newPath) == null){
           destination = Directory.currentDir.getDirectoryByPath(newPath);
           putDirectoryInDirectory(source, destination);
         }
         else{
           destination = Directory.rootDir.getDirectoryByPath(newPath);
           putDirectoryInDirectory(source, destination);
         } 
       }else{
         source = Directory.rootDir.getDirectoryByPath(oldPath).clone();
         if (Directory.rootDir.getDirectoryByPath(newPath) == null){
           destination = Directory.currentDir.getDirectoryByPath(newPath);
           putDirectoryInDirectory(source, destination);
         }
         else{
           destination = Directory.rootDir.getDirectoryByPath(newPath);
           putDirectoryInDirectory(source, destination);
         }
       }
      return "";
    } catch (NullPointerException e) {
      return pathCheck(oldPath, newPath);
    }
  }
  /**
   * Helper method for copying directories in the file system. Puts source
   * directory into the destination folder.
   * @param copy The directory that is to be added
   * @param destination the destination folder
   */
  protected void putDirectoryInDirectory (Directory copy, Directory destination)
  {
    if (!isNameTaken(destination, copy.getDirectoryName())){
      if (!checkIsChild(copy, destination)){
        copy.setPath(destination.getPath() + copy.getDirectoryName());
        destination.addDirectory(copy);
      }
      else{
        System.out.println("Cannot copy parent directory into child.");
      }
    }
    else
      System.out.println("Name already exists in destination.");
  }
  
  /**
   * Helper method for copying a file into another directory in the system.
   * Puts source file into destination directory
   * @param copy The file to be added(absolute or relative)
   * @param destination The directory to add the file into
   */
  protected void putFileInDirectory (FileClass copy, Directory destination){
    if (copy != null && destination != null){
      if (!isNameTaken(destination, copy.getName()))
        destination.addFile(copy);
    }
    else
      throw new NullPointerException();
  }
  
  /**
   * Checks the sub directories of a directory, returns true if the destination
   * is a child of the source.
   * @param source The directory to be copied
   * @param destination The supposed destination directory
   * @return True iff the destination is a child of the source.
   */
  protected boolean checkIsChild (Directory source, Directory destination){
    if (source.getDirectories().contains(destination)){
      return true;
    }
    else{
      boolean isChild = false;
      for (Directory subDirs : source.getDirectories()){
        isChild = checkIsChild(subDirs, destination);
      }
      return isChild;
    }
  }
  
  

  /**
   * Checks the user inputed paths and returns appropriate error message.
   * Possible errors include: destination path is a child of the source,
   * either directory or file is not found.
   * 
   * @param oldPath Path user is copying from
   * @param newPath Destination path for copied directory
   * @return
   */
  protected String pathCheck(String oldPath, String newPath) {
    if (Directory.currentDir.getDirectory(oldPath) == null) {
      return "Error Directory " + oldPath + " not found.";
    } else if (Directory.currentDir.getDirectory(newPath) == null) {
      return "Error Directory " + newPath + " not found.";
    } else {
      return "Error Both paths not found.";
    }
  }
  
  /**
   * Helper Method to adding a File into a directory, by determining whether
   * the user has inputed a path that starts from the root, or a path that
   * starts from the working directory.
   * @param cloneFile File Object to be added into the destination folder
   * @param newPath The destination path specified by the user
   * @return Error if the directory is not found, blank string if operation
   *         is successful.
   */
  protected String checkDestinationType(FileClass cloneFile, String newPath){
    Directory destination;
    if (newPath.startsWith("/")){
      destination = Directory.rootDir.getDirectoryByPath(newPath);
      if (isNameTaken(destination, cloneFile.getName()))
        return "Name already exists in destination.";
      putFileInDirectory(cloneFile, destination);
      return "";
    }
    else if (!newPath.startsWith("/")){
      destination = 
          Directory.currentDir.getDirectoryByPath(newPath);
      if (isNameTaken(destination, cloneFile.getName()))
        return "Name already exists in destination.";
      putFileInDirectory(cloneFile, destination);
      return "";
    }
    else
      throw new NullPointerException();
  }

  /**
   * Checks the destination path for directories of files with same name.
   * 
   * @param destination The destination folder the user wants to copy into
   * @param oldPath the name of the copied directory
   * @return False iff there are no directories or files with the same name as
   *         the copied directory
   */
  protected boolean isNameTaken(Directory destination, String oldPath) {
    for (Directory dir : destination.getDirectories()) {
      if (dir.getDirectoryName().equals(oldPath)) {
        return true;
      }
    }
    for (FileClass file : destination.getFiles()) {
      if (file.getName().equals(oldPath))
        return true;
    }
    return false;
  }
}
