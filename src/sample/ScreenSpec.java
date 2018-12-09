/**
 * ScreenSpec.java
 * @author Philemon Petit-Frere
 * 10/10/2018
 * This is an interface to be implemented by Screem
 */

package sample;


public interface ScreenSpec {

  /**
   * Gets screen resolution.
   *
   * @return  screen resolution
   */
  public String getResolution();

  /**
   * Gets refresh rate.
   * @return  refresh rate of Screen
   */
  public int getRefreshRate();

  /**
   * Gets Response time.
   *
   * @return  response time
   */
  public int getResponseTime();

}
