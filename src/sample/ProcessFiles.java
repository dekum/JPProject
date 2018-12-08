package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProcessFiles {
  private Path p;
 private Path p2;
 private Path p3;

 public static void main(String[] args){

   // System.out.println( pf);
 }

  public ProcessFiles() {
    p = Paths.get("C:\\LineTest");
    p2 = Paths.get("TestResults.txt");
    p3 = p.resolve(p2); //right way

    System.out.println(p3);
    CreateDirectory();





  }

  private void CreateDirectory(){
   if (Files.notExists(p)){
     try {
       System.out.println("Success");
       Files.createDirectories(p);

     } catch (IOException e) {
       e.printStackTrace();
       System.out.println("Problem creating directories.");
     }
   }


  }
public void writeFile(EmployeeInfo emp) throws IOException {
  FileWriter writer      = new FileWriter(String.valueOf(p3), true);
  PrintWriter printWriter = new PrintWriter(writer);

 // printWriter.print(true);


//  printWriter.printf("TaBoTa");
//  printWriter.println();
//  printWriter.print("Op");
  printWriter.println(emp.toString());


  printWriter.close();

}
public void writeFile(ArrayList<Product> products) throws IOException {
  FileWriter writer      = new FileWriter(String.valueOf(p3), true);
  PrintWriter printWriter = new PrintWriter(writer);

  // printWriter.print(true);


//  printWriter.printf("TaBoTa");
//  printWriter.println();
//  printWriter.print("Op");
  for (Product p: products
  ) {
   // pattern.toString();
    printWriter.println(p.toString());

  }



  printWriter.close();

}



}
