/**
 * MultimediaControl.java
 * @author Philemon Petit-Frere
 * 10/10/2018
 * This is an interface to be implemented by AudioPLayer and MoviePlayer class.
 */

package projectclasses;

public interface MultimediaControl {


  /**
   * Play method for subclasses.
   */
  public void play();

  /**
   * Stop method for subclasses.
   */
  public void stop();

  /**
   * Previous method for subclasses.
   */
  public void previous();

  /**
   * Next method for subclasses.
   */
  public void next();

}
