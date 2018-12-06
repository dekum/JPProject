package sample; /**
 * Philemon Petit-Frere
 * 9/27/2018
 * COP 3003 - OOP
 * Project: ProjectAlpha
 * File: Item.java
 * Create an enum called ItemType that stores:
 * Type Code
 * AUDIO AU
 * VISUAL VI
 * AUDIOMOBILE AM
 * VISUALMOBILE VM
 *
 * */

import java.util.Date;


public interface Item {


  //Methods to be further implemented by Product Class

  static final String MANUFACTURER = "OracleProduction";

  void setProductionNumber(int parameter);

  void setName(String name);

  String getName();

  Date getManufactureDate();

  int getSerialNumber();


}
