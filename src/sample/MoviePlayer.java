/**
 * MoviePlayer.java
 * @author Philemon Petit-Frere
 * 10/10/18
 * A class extending from Product and implements the MultimediaControl interface.
 * To create a moviePlayer Object, a new Screen a
 * and enum MonitorType must be passed to constructor.
 */

package sample;

public class MoviePlayer extends  Product implements  MultimediaControl {
  /**
   * The screen object for this MoviePlayer.
   */
  Screen screen;
  /**
   * MonitorType enum for this Movie Player.
   */
  MonitorType monitorType;

  /**
   * Returns the monitor type enum.
   *
   * @return  the monitor type.
   */
  public MonitorType getMonitorType() {
    return monitorType;
  }

  /**
   *  Constructor to create a MoviePlayer object.
   *
   * @param  name the name of the movie player.
   * @param  screen a screen object with a resolution,refresh rate, and response Time.
   * @param  monitorType the monitor type parameter.
   */
  public MoviePlayer(String name, Screen screen, MonitorType monitorType) {
    super(name);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * A constructor to test the MoviePlayer class.
   */
  public MoviePlayer() {
    super("Windows Media PLayer Classic");
    screen = new Screen("200x200", 33,33);
    monitorType = MonitorType.LCD;
  }

  /**
   * Prints Playing movie to console.
   */
  @Override
  public void play() {

    System.out.println("Playing movie");
  }

  /**
   * Prints Stop movie to console.
   */
  @Override
  public void stop() {
    System.out.println("Stop movie");

  }

  /**
   * Prints Previous movie to console.
   */
  @Override
  public void previous() {
    System.out.println("Previous movie");

  }

  /**
   * Prints Next movie to console.
   */
  @Override
  public void next() {
    System.out.println("Next movie");

  }

  /**
   * Modifies the toString Method.
   * Uses Product's toString to return the name.
   * Contents of each field are placed into the result with one per line.
   *
   * @return  the name, screen and monitorType.
   */
  @Override
  public String toString() {
    return super.toString()
        + "\nScreen : "
        + screen
        + " \nMonitorType: "
        + monitorType;
  }

  /**
   * This method will call Product's compareTo, where it will sort by name.
   *
   * @param  o is a object that will be accepted by this method.
   */
  @Override
  public int compareTo(Object o) {

    return super.compareTo(o);
  }
}
