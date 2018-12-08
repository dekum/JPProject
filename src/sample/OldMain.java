package sample;

/**
 * Philemon Petit-Frere
 * 9/27/2018
 * COP 3003 - OOP
 * Project: JPProject
 * File: OldMain.java
 * Purpose of Project: design a template in Java for creating and recording all future
 * production line items. Also also allow easy modification to handle different products
 * Sources for help: Stackexchange, Garrett Grabber, github.com
 * Sources for JavaDoc: https://www.oracle.com/technetwork/java/javase/tech/index-137868.html
 * https://blog.joda.org/2012/11/javadoc-coding-standards.html
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class OldMain {

  /**
   * Main  is the driver class which creates 2 Widgets,
   * Widgets extends Product so when Widget is made.
   * Product's constructor also runs.
   * Lastly Main prints the values of codes of the ItemList Enum.
   *
   * @param  args an array of command-line arguments for the application.
   */
  public static void main(String [] args) {

    //    Widget w1 = new Widget("Widget 1");//Create Widget called Widget1
    //    System.out.println(w1.toString());//Use Product's toString method to print information.
    //    Widget w2 = new Widget("Widget 2");//Create Widget called Widget2
    //    System.out.println(w2.toString()); //Use Product's toString method to print information.
    //
    //    for (ItemType it : ItemType.values()) {
    //      //For loop ends when, ItemType.values reaches the end, as this case 4 times.
    //      System.out.println(it + " " + it.code); //Print type and code of ItemType Enum
    //    }
    //

    //MoviePlayerDriver.testMoviePlayer();
    //    PlayerDriver.testPlayer();

    /**
     * Create a arraylist of Products called productList
     * The item in the arraylist will be set by testCollections method()
     * After which Collections.sort will sort the elemnts in the productList.
     * Product needs to implement Comparable, and implement it's compareTo method.
     * Since AudioPlayer and MoviePlayer are subclasses
     * of Product they also need Comparable's method.
     * I have my AP and MP classes call super.compareTo
     * which will call Product's compareTo method which I changed.
     * Product's compareTo method, takes an object and casts as a Product,
     * which I can do because I know a product will be sent to it.
     * Since the object is considerd a Product now, i can use it's .getName method and use
     *  String class's compareTo method to compare the names of two Product's.
     * Product's compareTo will return the result which is either a -1,0 or 1.
     * Thus Collections.sort will use AudioPlayer's and Movieplayer's
     * compareTo Method, and will sort by Names of objects.
     * Lastly there a method to print whats in the arraylist List.
     *
     */
    //Write ome line of code to create and Array of products
    ArrayList<Product> productList = new ArrayList<>(); //Change to studentProducts for replit
    //Write one line of code to call testCollection and assign the result to the arraylist
    productList = testCollection();

    // write one line of code to sort arraylist
    Collections.sort(productList);
    //Call print method on the array list
    print(productList);

    /**
     * Test for ProcessFiles class, which displays employee info and productlist
     * into a textfile.
     */
    EmployeeInfo empInfo = new EmployeeInfo();
    System.out.println(empInfo.toString());
    ProcessFiles pf = new ProcessFiles();
    // EmployeeInfo emp = new EmployeeInfo();
    try {
      pf.writeFile(empInfo);
      pf.writeFile(productList);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //System.out.println("\n\n Now Print Audio players");
    //Product.printType(productList,AudioPlayer.class);
    //System.out.println("\n\n\n print only MoviePlayers");
    //Print only MoviePlayer objects
    //Product.printType(productList,MoviePlayer.class);

  }

  /**
   * A method to print the products in the array list parameter.
   *
   * @param  products Arraylist of products parameter.
   */
  public static void print(ArrayList<Product> products) {
    /**
     * Prints content of arraylist in the parameter.
     * In this program it shows that the array list is sorted by name.
     */
    for (Product p :products
    ) {
      System.out.println(p.toString());

    }
  }

  /**
   *  Adds element to the ArrayList of products.
   *  A ArrayList of Product is passed to this class and is amended.
   *
   * @return  products arraylist, populated by products in this method.
   */
  private static ArrayList<Product> testCollection() {

    ArrayList<Product> products = new ArrayList<>();
    AudioPlayer a1 = new AudioPlayer("iPod Mini", "MP3");
    AudioPlayer a2 = new AudioPlayer("Walkman ", "WAV");
    MoviePlayer m1 = new MoviePlayer("DBPOWER MK101", new Screen("720x480",40,22),MonitorType.LCD);
    MoviePlayer m2 = new MoviePlayer("Pyle PdV156BK", new Screen("1366x768",40,22),MonitorType.LED);

    products.add(a1);
    products.add(a2);
    products.add(m1);
    products.add(m2);
    return products;

  }

}

