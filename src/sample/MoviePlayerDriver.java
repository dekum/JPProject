/**
 * MoviePlayerDriver.java
 * @author Philemon Petit-Frere
 * 11/10/2018
 * This class is used to test objects of MoviePlayer class.
 */

package sample;

public class MoviePlayerDriver {

  /**
   * Create objects of MoviePlayer, and display it's toStrings.
   */
  public static void  testMoviePlayer() {

    MoviePlayer mp = new MoviePlayer("DBPOWER MK101",new Screen("720x480",40,22),MonitorType.LCD);
    System.out.println(mp.toString());
    System.out.println("\n");
    MoviePlayer mp2 = new MoviePlayer("Pyle PDV156BK",new Screen("1366x768",40,22),MonitorType.LED);
    System.out.println(mp2.toString());
  }
}