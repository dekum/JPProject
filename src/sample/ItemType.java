/**
 * ItemType.java
 * 9/27/2018
 * COP 3003 - OOP
 * @author Philemn Peti-Frere
 * Good Source:
 * https://stackoverflow.com/questions/6549821/how-to-javadoc-a-classs-individual-enums
 * Create an enum called ItemType that stores:
 * Type Code
 * AUDIO AU
 * VISUAL VI
 * AUDIOMOBILE AM
 * VISUALMOBILE VM
 * Links
 * <li>{@link #AUDIO}</li>
 * <li>{@link #VISUAL}</li>
 * <li>{@link #AUDIOMOBILE}</li>
 * <li>{@link #VISUALMOBILE}</li>
 */

package sample;


enum ItemType {
  /**
   * Audio.
   */
  AUDIO("AU"),
  /**
   * Visual.
   */
  VISUAL("VI"),
  /**
   * AUDIOMOBILE.
   */
  AUDIOMOBILE("AM"),
  /**
   * VISUALMOBILE.
   */
  VISUALMOBILE("VM");
  //Remember to include "" in parenthesis, this was causing a error earlier.

  String code;

  /**
   * Constructor for ItemType enum.
   * @param code the ItemTYpe code
   */
  ItemType(String code) {
    //Constructor method for ItemType enum
    //Without this method, the enum fields for the codes will cause errors
    this.code = code;
  }

  /**
   * Returns the ItemType code.
   * @return the ItemType code
   */
  public String getCode() {
    return code;
  }

}