package sample; /**
 * Philemon Petit-Frere
 * 9/27/2018
 * COP 3003 - OOP
 * Project: ProjectAlpha
 * File: OldMain
 * Purpose of Project: design a template in Java for creating and recording all future
 * production line items. Also also allow easy modification to handle different products
 *Sources for help: Stackexchange, Garrett Grabber, github.com
 *
 */

import java.io.IOException;
import  java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OldMain {

  /**
   * Main  is the driver class which creates 2 Widgets, Widgets extends Product so when Widget is mad
   * Product's constructor also runs.
   * Lastly Main prints the values of codes of the ItemList Enum
   */
  public static void main(String args[]) {

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
     * Since AudioPlayer and MoviePlayer are subclasses of Product they also need Comparable's method.
     * I have my AP and MP classes call super.compareTo which will call Product's compareTo method which I changed
     * (1/2)/ Product's compareTo method, takes an object and casts as a Product, which I can do because I know a product will be sent to it.
     * (2/3) Since the object is considerd a Product now, i can use it's .getName method and use
     * (3/3) String class's compareTo method to compare the names of two Product's.
     * Product's compareTo will return the result which is either a -1,0 or 1.
     * Thus Collections.sort will use AudioPlayer's and Movieplayer's compareTo Method, and will sort by Names of objects.
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
    EmployeeInfo empInfo = new EmployeeInfo();
    System.out.println(empInfo.toString());
    ProcessFiles pf = new ProcessFiles();
    // EmployeeInfo emp = new EmployeeInfo();
    try {
      pf.WriteFile(empInfo);
      pf.WriteFile(productList);
    } catch (IOException e) {
      e.printStackTrace();
    }


    //System.out.println("\n\n Now Print Audio players");
    //Product.printType(productList,AudioPlayer.class);
    //System.out.println("\n\n\n print only MoviePlayers");
    //Print only MoviePlayer objects
    //Product.printType(productList,MoviePlayer.class);



    //Class<?> aClass = productList;
//    MoviePlayer j = new MoviePlayer();
//    System.out.println("\n\n \n ONLY MOIE PLAYER YO"); Bad Way of doing this
//    Product.printType(productList, j.getClass() );
//AudioPlayer w = new AudioPlayer();
//    System.out.println("\n\n \n AudioPlayers Show up");
//    Product.printType(productList, w.getClass() );

    //Step 15
    //Compelete the header for the testCollection method here










  }
  public static void print(ArrayList<Product> products){
    /**
     * Prints content of arraylist in the parameter.
     * In this program it shows that the arraylist is sorted by name.
     */

    for (Product p :products
    ) {
      System.out.println(p.toString());

    }
  }

  private static ArrayList<Product> testCollection() {
    /**
     * Adds element to the ArrayList of products
     * Returns the newly added elements to a ArrayList<Product> in main
     */
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

abstract class Widget extends Product {
  //Widget extends Product, as Product is a abstract class and thus will not be able to make an object
  public Widget(String name) {
    //Widget accepts a name, then uses that name as a parameter for Product's constructor
    super(name);//The super of Widget is Prodct, this will call it's constructor.
  }

}