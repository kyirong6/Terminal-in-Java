package driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Directory {
  /**
   * Directory, class which details the behavior of directories, such as storing
   * their paths and containing other directories and files.
   * @author See honor code above
   *
   */
    
  List<Directory> directories = new ArrayList<Directory>();
  List<FileClass> files = new ArrayList<FileClass>();
  String path = new String();
  String directoryName = new String();
  
  // The ROOT of all directories. Accessed by methods. Static.
  public static Directory rootDir = new Directory("root", "/");
    
  // The CURRENT directory that the user is on. Static. Accessed and manipulated
  // via methods.
  public static Directory currentDir = rootDir;

  /**
   * Constructor of Directory. Sets the directory's name and path.
   * @param name The String name of the directory.
   * @param newpath The String path (location) of the directory relative to the
   *        root directory.
   */
  public Directory(String name, String newpath) {
    directoryName = name;
    path = newpath;
  }
  
  /**
   * Cloning method, recursively copies a directory, resulting directory
   * has same name and contents but is separate entity (along with contents)
   * @return Cloned directory (all contents are cloned as well)
   */
  @Override
  public Directory clone(){
    //directory has no sub directories, so create copy of "end" of tree
    if (this.directories.size() == 0){
        Directory clonedDir = new Directory(this.directoryName, this.path);
        for (FileClass file : this.files){
          clonedDir.addFile(file.clone());
        }
        return clonedDir;
      }
    //directory has sub directories
    else{
      Directory clonedDir = new Directory(this.directoryName, this.path);
      for (FileClass file : this.files){
        clonedDir.addFile(file.clone());
      }
      for (Directory dir : this.directories){
        clonedDir.addDirectory(dir.clone());
      }
      return clonedDir;
    }
  }
  /**
   * Equals method to satisfy clone method
   * @param d Directory we are comparing with
   * @return returns True iff this directory name is the same as Directory
   * d's name
   */
  public boolean equals(Object d){
    if (d.getClass().equals(this.getClass())){
      return this.directoryName.equals(((Directory) d).directoryName); 
    }
    else
      return false;
  }
    
  /**
   * Method to retrieve a sub-directory of this directory. Usually called on
   * the root directory or the current directory to assist commands.
   * @param path the String path of the directory to retrieve.
   * @return Directory the directory located at the given path, or null if
   *         no such directory exists.
   */
  public Directory getDirectoryByPath(String path) {
    String restOfPath = path;
    String firstWord;
    Directory curr = this;
    if (path.equals("/")) {
      return rootDir;
    }
    // removes root directory from path to be traversed
    if (path.substring(0,1).equals("/")) {
      restOfPath = restOfPath.substring(1);
    }
    if (restOfPath.substring(restOfPath.length() - 1).equals("/")) {
      restOfPath = restOfPath.substring(0, restOfPath.length() - 1);
    }
    // immediate check on whether there is any remaining directories to traverse
    if (!restOfPath.contains("/")) {
      curr = curr.getDirectory(restOfPath);
      if (curr == null) {
        return null;
      } else {
        return curr;
      }
    }
    // traverses through directories, similar to linked list traversal
    while (restOfPath.indexOf("/") != -1) {
      String[] arr = restOfPath.split("/", 2);
      firstWord = arr[0];
      restOfPath = arr[1];
      curr = curr.getDirectory(firstWord);
      if (curr == null) {
        return null;
      }
    }
    // returns the last directory in path or null if no such directory exists
    if (curr.getDirectory(restOfPath) != null) {
      return curr.getDirectory(restOfPath);
    } else {
      return null;
    }
  }
    
  /**
   * Method which takes an input file path and returns the FileClass at that 
   * path. If the path does not exist, returns null.
   * @param path the String path of the directory to retrieve.
   * @return FileClass the file located at the given path, or null if
   *         no such file exists.
   */
  public FileClass getFileByPath(String path) {
    String restOfPath = path;
    String firstWord;
    Directory curr = this;
        
    // removes root directory from path to be traversed
    if (path.substring(0,1).equals("/")) {
      restOfPath = restOfPath.substring(1);
    }
    if (restOfPath.substring(restOfPath.length() - 1).equals("/")) {
      restOfPath = restOfPath.substring(0, restOfPath.length() - 1);
    }
    // traverses through directories, similar to linked list traversal
    while (restOfPath.indexOf("/") != -1) {
      String[] arr = restOfPath.split("/", 2);
      firstWord = arr[0];
      restOfPath = arr[1];
      curr = curr.getDirectory(firstWord);
            
      if (curr == null) {
        return null;
      }
    }
    // gets the last file in path or null if no such file exists
    if (curr.getFile(restOfPath) != null) {
      return curr.getFile(restOfPath);
    } else {
      return null;
    }
  }
    
  /**
   * Getter method to retrieve the directory name of this instance of Directory.
   * @return String the name of the directory.
   */  
  public String getDirectoryName() {
    return directoryName;
  }
  
  
  /**
   * Getter method to retrieve a list of the directories contained within this 
   * instance of Directory.
   * @return List<Directory> list of directories contained in this directory.
   */  
  public List<Directory> getDirectories() {
    return directories;
  }
    
  /**
   * Getter method to retrieve a list of the files contained within this
   * instance of Directory.
   * @return List<FileClass> list of files contained in this directory.
   */  
  public List<FileClass> getFiles() {
    return files;
  }
    
  /**
   * Adds a new Directory to the directories contained within this instance of 
   * Directory.
   * @param direct The Directory to be added.
   */  
  public void addDirectory(Directory direct) {
    directories.add(direct);
  }
    
  /**
   * Adds a new FileClass to the files contained within this instance of 
   * Directory.
   * @param file The FileClass to be added.
   */ 
  public void addFile(FileClass file) {
    files.add(file);
  }
    
  /**
   * Setter method to set the directory's path. Used to assist commands which
   * involve moving the directory.
   * @param newpath The new String path of this directory.
   */ 
  public void setPath(String newpath) {
    path = newpath;
  }
    
  /**
   * Getter method to get the directory's path.
   * @return String The path of this directory.
   */ 
  public String getPath() {
    return path;
  }
  
  public String getParentPath(){
    String [] parentPath = this.path.split("/");
    if (parentPath.length < 3)
      return "/";
    else{
      StringBuilder fullPath = new StringBuilder();
      for (int i = 0; i < parentPath.length-1; i++){
        fullPath.append("/");
        fullPath.append(parentPath[i]);
      }
      return fullPath.replace(0, 1, "").toString().trim();
    }
  }
    
  /**
   * Returns a Directory immediately contained within this directory by name,
   * or null if no such directory exists.
   * @param directoryName The name of the directory to be returned.
   * @return Directory the directory to be returned, or null if no such
   *         directory exists.
   */ 
  public Directory getDirectory(String directoryName) {
    // loops through the directories within this directory, searching for match
    for (int i = 0; i < directories.size(); i++) {
      if (directories.get(i).getDirectoryName().equals(directoryName)) {
        return (Directory) directories.get(i);
      }
    }
    return null;
  }
    
  /**
   * Returns a fileClass immediately contained within this directory by name,
   * or null if no such directory exists.
   * @param fileName The name of the file to be returned.
   * @return fileClass the file to be returned, or null if no such file exists.
   */
  public FileClass getFile(String fileName) {
    // loops through the files in this directory, searching for a match
    for (FileClass file: files) {
      FileClass tempFile = file;
      if (tempFile.toString().equals(fileName)) {
        return tempFile;
      }
    }
    return null;
  }
    
  /**
   * Removes and returns a Directory with the input name that is immediately 
   * contained within this Directory or null if no such directory exists.
   * @param directoryName The name of the directory to be removed and returned.
   * @return Directory the directory to be removed and returned, or null if no
   * such directory exists.
   */
  public Directory removeDirectory(String directoryName) {
    // loops through directories contained in this directory
    Object[] tempDirectories = directories.toArray();
    for (int i = 0; i < tempDirectories.length; i++) {
      // if a directory matching the input name is found, removes and returns it
      if (((Directory) tempDirectories[i]).getDirectoryName().
          equals(directoryName)) {
        Directory toBeReturned = directories.remove(i);
        return toBeReturned;
      }
    }
    return null;
  }
    
  /**
   * Removes and returns a FileClass with the input name that is immediately 
   * contained within this Directory or null if no such fileClass exists.
   * @param fileName The name of the file to be removed and returned.
   * @return fileClass the file to be removed and returned, or null if no
   * such file exists.
   */
  public FileClass removeFile(String fileName) {
    // loops through the files in this directory
    Object[] tempFiles = files.toArray();
    for (int i = 0; i < tempFiles.length; i++) {
      // if a file with a matching name is found, removes and returns it
      if (((FileClass) tempFiles[i]).getName().equals(fileName)) {
        FileClass toBeReturned = (FileClass) tempFiles[i];
        files.remove(i);
        return toBeReturned;
      }
    }
    return null;
  }
  
  /**
   * Recursively finds all directories and their sub-directories.
   * @return ArrayList<List> A list of all directories which have
   *                         sub-directories, with each list in the format of
   *                         ['Directory path', sub-directory 1, sub-directory 
   *                         2, ... ].
   */
  
  public ArrayList<List> getAllDirectories() {
    
    Collection<List> listOfDirs = new ArrayList<List>();
    List<Directory> dirs = this.getDirectories();
    
    if (dirs.toString() == "[]") {
      return new ArrayList<List>();
    }
    else {
      // adds own sub-directories as a List<String> to the ArrayList<List> to
      // be returned
      List<String> containedDirs = new ArrayList<String>();
      containedDirs.add(this.getPath());
      for (Directory dir:dirs) {
        containedDirs.add(dir.getDirectoryName());
      }
      listOfDirs.add(containedDirs);
      for (int i=0; i<dirs.size(); i++) {
        
        // recursively retrieves lists of sub-directory directories and joins
        // them to current ArrayList<List>
        Collection<? extends List> directoriesOfDir = dirs.get(i).getAllDirectories();
        if (directoriesOfDir.toString() != "[]") {
          List<List> temp = new ArrayList<List>();
          temp.addAll(listOfDirs);
          temp.addAll(directoriesOfDir);
          listOfDirs = new ArrayList<List>();
          listOfDirs.addAll(temp);
        }
      }
      return (ArrayList<List>) listOfDirs;
    } 
  }
  
}