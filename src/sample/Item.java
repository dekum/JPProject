package sample; /**
 * @author: Philemon Petit-Frere
 * 9/27/2018
 * COP 3003 - OOP
 * Project: ProjectAlpha
 * File: Item.java
 * An interface to be implemented by other classes
 * */

import java.util.Date;


public interface Item {


  //Methods to be further implemented by Product Class

  static final String MANUFACTURER = "OracleProduction";

  /**
   * Used by Product subclass to set production number.
   *
   * @param  productionNumber the production number
   */
  void setProductionNumber(int productionNumber);

  /**
   * Used by Product subclass to set name.
   *
   * @param  name the name of the product
   */
  void setName(String name);

  /**
   * Used by Product subclass to get name.
   *
   * @return name of Product
   */
  String getName();

  /**
   * Used by Product subclass to get Manufacturer Date.
   *
   * @return date when Manufactured
   */
  Date getManufactureDate();

  /**
   * Used by Product subclass to get Manufacturer Date.
   *
   * @return the Serial Number
   */
  int getSerialNumber();


}
