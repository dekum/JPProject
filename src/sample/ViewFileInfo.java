package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewFileInfo {

  public static void main(String[] args){

    FileReader fr= null;
    try {
      fr = new FileReader("C:\\LineTest\\TestResults.txt");
      int i;
      while((i=fr.read())!=-1)
        System.out.print((char)i);
      fr.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not fount");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem with file.");
    }


  }

}
