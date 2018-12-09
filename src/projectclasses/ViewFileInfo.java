/**
 * ViewFilesInfo.java
 * @author Philemon Petit-Frere
 * This class reads textFile created by the ProcessFiles class
 * The path to the file is C:\LineTest\TestResults.txt
 * The class FileReader is used, and the fine is read char by char
 * Created: 11/30/18
 */

package projectclasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewFileInfo {

  /**
   * Driver class to read the TestResults.txt textfile.
   * Will return errors if file isn't read or problem occurs.
   *
   * @param args an array of command-line arguments for the application.
   */
  public static void main(String[] args) {

    FileReader fr = null;
    try {
      fr = new FileReader("C:\\LineTest\\TestResults.txt");
      int i;
      while ((i = fr.read()) != -1) {
        System.out.print((char)i);
      }

      fr.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found.");

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem with file.");

    }
  }
}
