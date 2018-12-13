/**
 * ProcessFiles.java
 * @author Philemon Petit-Frere
 * This class created a directory and textfile C:\LineTest\TestResults.txt if it doesn't exist.
 * It writes to the file: The Employee Info, and the productlist
 * Created: 11/30/18
 */

package projectclasses;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProcessFiles {


  /**
   * First part of file path.
   * C:\\LineTest
   */
  private Path path;
  /**
   * Second part of foio;e path, txtFile name and extension.
   * TestResults.txt
   */
  private Path path2;
  /**
   * Combined path and p1 into one file path.
   * C:\LineTest\TestResults.txt
   */
  private Path path3;

  /**
   * Constructor to create a ProcessFiles object.
   * Sets that path fields to a absolute path  C:\LineTest\TestResults.txt
   */
  public ProcessFiles() {
    path = Paths.get("C:\\LineTest"); //file path
    path2 = Paths.get("TestResults.txt"); //name of text file
    path3 = path.resolve(path2); //combined to one text file
    //Check styles give dodgy code error
    //It says this shouldnt' be a absoloute path

    System.out.println(path3);
    createDirectory();

  }

  /**
   * Creates a folder in C: called LineTests.
   * If the directory already exists then method does nothing.
   * @throws  IOException  If an input or output
   *                      exception occurred
   */
  private void createDirectory() {
    if (Files.notExists(path)) {
      try {
        System.out.println("Success");
        Files.createDirectories(path);

      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Problem creating directories.");
      }
    }
  }

  /**
   * An overloaded method with EmployeeInfo object as a parameter.
   * Uses FileWriter and PrinterWriter to edit textfile with EmployeeInfo.
   * FileWriter object is created, with the path3, filepath  to textfile
   * append is set to true, so textfile content wont be erased if it already exists.
   * PrintWriter object writes the file content.
   * The information written is the Emploee code and deptId.
   *
   * @param  emp EmployeeInfo object which stores code and deptId in toString.
   * @throws  IOException  If an input or output
   *                      exception occurred
   */
  public void writeFile(EmployeeInfo emp) throws IOException {
    FileWriter writer      = new FileWriter(String.valueOf(path3), true);
    PrintWriter printWriter = new PrintWriter(writer);

    printWriter.println(emp.toString());
    printWriter.close(); //remember to close.

  }

  /**
   * An overloaded method with ArrayList of Products are parameter.
   * Uses FileWriter and PrinterWriter to edit textfile with product list.
   * FileWriter object is created, with the path3, filepath  to textfile
   * append is set to true, so textfile content wont be erased if it already exists.
   * PrintWriter object writes the file content.
   * The information written is the toStrings of products in product list.

   *
   * @param  products A list of products.
   * @throws  IOException  If an input or output
   *                      exception occurred
   */
  public void writeFile(ArrayList<Product> products) throws IOException {
    FileWriter writer      = new FileWriter(String.valueOf(path3), true);
    PrintWriter printWriter = new PrintWriter(writer);

    for (Product p: products
    ) {
      //with println(p.toString()) a line is between every object

      //Loop through productList and write to file it's toString.
      printWriter.println(p.toString());

    }
    printWriter.close();

  }

}
