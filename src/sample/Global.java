/**
 * Global.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This class stores information to be used by the controllers of FXML
 */

package sample;

import java.util.ArrayList;

public class Global {

  /**
   * Product list to be used by all controllers.
   */
  public static final  ArrayList<Product> productList = new ArrayList<>();
  //FindBugs suggested to make productList a final.
}
