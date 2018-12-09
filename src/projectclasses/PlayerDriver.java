/**
 * PlayerDriver.java
 * @author Philemon Petit-Frere
 * Created: 11/10/18
 *
 * This class is used to demonstrate polymorphism, demonstrated in testPlayer method.
 */

package projectclasses;

public class PlayerDriver {


  /**
   * This methods shows polymorphism of MultimediaControl.
   * AudioPlayer and MoviePlayer implement MultimediaControl.
   * This method proves that they can be declared as MultimediaControl objects
   * and will call it's subclasses methods.
   */
  public static void testPlayer() {
    MultimediaControl mc1 = new  AudioPlayer();
    mc1.next();
    mc1.play();
    mc1.previous();
    mc1.stop();
    MultimediaControl mc2 = new MoviePlayer();
    mc2.next();
    mc2.play();
    mc2.previous();
    mc2.stop();
  }
}
