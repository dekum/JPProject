/**
 * @author Philemon Petit-Frere
 * 9/27/2018
 * COP 3003 - OOP
 * Project: JPProject
 * File: Product.java
 * Since its a abstract class it cannot create objects of it.
 * Abstract classes work great as blueprints, and encourages reusing code.
 * Product is extended by subclasses, AudioPlayer and MoviePlayer.
 * Product implements Item interface and it's methods.
 * Product implements Comparable interface,
 * and overrides its compareTo method.
 */

package projectclasses;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public abstract class Product implements Item, Comparable {
  /**
   * Abstract classes cannot be implemented,
   * but are useful for classes that have similar methods to extend from.
   * Product will implement the basic functionality
   * that all items on a production line should have.
   */

  /**
   * The serial number of this product, set using currentProductNumber.
   */
  int serialNumber;
  /**
   * The date and time this product was created, using Java Date class.
   */
  Date manufacturedOn; //manufacturedOn is a object of Date
  /**
   * The name of the product, used in the constructor.
   */
  String name;//Name of the Product, to be initialized in constructor
  /**
   * The name of the manufacturer, will always be "OracleProduction".
   */
  static final String manufacturer = Item.MANUFACTURER; //name of manufacturer
  //findbugs suggested to make this a static field to increase performance.

  /**
   * The production number, used to set serial number.
   * This is a static field and is incremented
   * every time a product is created.
   */
  static int currentProductionNumber = 1; //This is static so it can constantly be incremented.

  /**
   * The name of this product to be shown in table view.
   */
  SimpleStringProperty nameProperty = new SimpleStringProperty();
  /**
   * The class of this product to be shown in table view.
   * Either AudioPlayer or MoviePlayer, since product cannot create objects.
   */
  SimpleStringProperty typeProperty = new SimpleStringProperty();

  /**
   * Date manufacturedON in a object that be stored into a database.
   * Use a prepared statement to insert into database.
   */
  LocalDate localDateManufactured;

  /**
   * This product's color, given by colorpicker.
   * default value is white
   */
  String color = "White";

  /**
   * The equals method allows java to check if two objects are the same.
   * In this class, its overridden to check only the name fields.
   * If the name fields are the same, then this program considers
   * them to be the same object, or a duplicate in the stats.
   *
   * @param  o another object is compared to this product
   * @return  boolean true or false depending if two products match.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(getName(), product.getName());
  }



  /**
   * If equals is changed, then the hashCode must change as well.
   *
   * @return  hash of getName().
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  /**
   * Gets localDateManufactured.
   *
   * @return  value of localDateManufactured.
   */
  public LocalDate getLocalDateManufactured() {
    return localDateManufactured;
  }

  /**
   * Changes the date manufactured, when loading from database.
   *
   * @param  localDateManufactured the stored date in database.
   */
  public void setLocalDateManufactured(LocalDate localDateManufactured) {
    this.localDateManufactured = localDateManufactured;
  }

  /**
   * Get name property, which is displayed in the product table.
   *
   * @return  nameProperty field.
   */
  public SimpleStringProperty getNameProperty() {
    return nameProperty;
  }

  /**
   * Modifies this product's nameProperty to parameter.
   *
   * @param  nameProperty sets this product's name Property
   */
  public void setNameProperty(String nameProperty) {
    this.nameProperty.set(nameProperty);
  }

  /**
   * Get the typeProperty.
   *
   * @return  typeProperty the name of this class.
   */
  public SimpleStringProperty getTypeProperty() {
    return typeProperty;
  }

  /**
   * Gets the class name.
   *
   * @return String result of the Type, which is the class type.
   */
  public String getType() {
    return  typeProperty.get();
  }

  /**
   * Changes this typeProperty to parameter.
   *
   * @param  typeProperty number of stat to be set
   */
  public void setTypeProperty(String typeProperty) {
    this.typeProperty.set(typeProperty);
  }

  /**
   * This will sort objects of projects by their field "name"
   * Since the objects we make in program are AudioPlayer
   * and MoviePlayer I have them call this method to sort the elements.
   * This method return an Int because the result of a compareTo is either a -1,0,1
   * -1, if first string is lower lexicographic than the second.,
   * 0 if equal And 1 if first  is greater.
   *
   * @param o object being compared to
   * @return boolean result of the comparision.
   */
  @Override
  public int compareTo(Object o) {

    return name.compareTo(((Product) o).getName());
  }

  /**
   * Overriden Collection.sort's methos to use this method.
   * Without it wouldn't know what to sort.
   * Compare lexicographic order of two product's name field.
   * Returns 1 is this product is greater than.
   * Returns 0 if two products are equal.
   * Returns -1 is this product is lesser than.
   * @param p product being compared to.
   * @return boolean result of the comparision.
   */
  public int compareTo(Product p) {
    return name.compareTo(p.getName());
  }

  /**
   * Changes this productionNumber to parameter.
   *
   * @param  productionNumber the production number.
   */
  public void setProductionNumber(int productionNumber) {
    serialNumber = productionNumber;
  }

  /**
   * Changes this productionNumber to parameter.
   *
   * @param  name the name of the product to be set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Changes this product's color to parameter.
   *
   * @param color the hex code of the color.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Gets serialNumber of this product.
   *
   * @return value of serialNumber.
   */
  @Override
  public int getSerialNumber() {
    return serialNumber;
  }

  /**
   * Gets manufacturedOn of this product.
   *
   * @return value of manufacturedOn.
   */
  public Date getManufactureDate() {
    return manufacturedOn;
  }

  /**
   * Gets name of this product.
   *
   * @return value of name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Constructor to create a Product object
   * However since Product is a abstract Class,
   * only objects of MoviePlayer and AudioPlayer can access this constructor.
   * This constructor needs the name, to set the name of the product.
   * The serial number is set to the current production number
   * and then the product number in incremented by one.
   * product Number is a static field, so it is constantly changing.
   * Thus no object serial number should be the same.
   * manufacturedOn is set to current Date and time the program ran.
   * Lastly the Name and Type Properties are set.
   * Type property is just the name of the subclass.
   *
   * @param  name name of Product to be set
   */
  Product(String name) {
    this.name = name; //Product's instance name will be set to parameter name
    serialNumber = currentProductionNumber++;//SeriesNumber received productionNumber
    // and productNumber will increment by one
    manufacturedOn = new Date();
    //manufacturedOn will receive new Date, set to current time the program runs

    localDateManufactured = LocalDate.now();
    System.out.println(localDateManufactured + "\n" + manufacturedOn);
    setNameProperty(name);
    setTypeProperty(this.getClass().getSimpleName());

  }

  /**
   * This method accepts a list of generic objects, and Class,
   * and then will check list to see if any of them match Class parameer
   * In this program this will be used to check a list of products
   * to see if they are matching the Class parameter
   * Either a AudioPlayer or MoviePlayer
   * When call this method it will look like this:
   * printType(List>Product< pList, AudioPlayer.class)
   * With the diamonds inverted.
   * If object and class match, this method will print the toString.
   *
   *
   * @param list A generic parameter that accepts list arguments.
   * @param c A generic that accepts Subclasses of Product
   */
  static void printType(List<?> list, Class<? extends Product> c) {
    for (Object o : list) {
      if (c.isInstance(o)) { //Check if Object's class is a
        //Class Matches
        System.out.println(o.toString());
      }
    }
  }

  /**
   * Modifies the toString Method.
   * Contents of each field are placed into the result with one per line.
   *
   * @return  manufacturer, serialNumber, getManufactureDate and name.
   */
  @Override
  public String toString() {

    String line =
        "Manufacturer  : "
            + manufacturer + "\n"
            + "Serial Number : "
            + serialNumber + "\n"
            + "Date          : " + getLocalDateManufactured() + "\n"
            + "Color         : " + color + "\n"
            + "Name          : " + name;
    //I use line because I felt it was easier to code with
    return line;

  }
}
