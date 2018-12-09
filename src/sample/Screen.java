/**
 * Screen.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This class implements ScreenSpec and its methods
 * This class is used by MoviePlayer to store Screen data
 */

package sample;

public class Screen implements ScreenSpec {

  /**
   * The screen resolution for ex. "1280x720"
   */
  String resolution;
  /**
   * The refresh rate.
   */
  int refreshRate;
  /**
   * the response time.
   */
  int responseTime;

  /**
   * Gets resolution.
   *
   * @return  value of resolution
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * Gets refreshRate.
   *
   * @return  value of refreshRate
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * Gets responseTime.
   *
   * @return  value of responseTime.
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Modifies the toString Method.
   * Contents of each field are placed into the result with one per line.
   *
   * @return  the resolution, refresh rate, and response time.
   */
  @Override
  public String toString() {
    String line = "Resolution : " + resolution + "\n"
        + "Refresh Rate : "
        + refreshRate
        + "\n"
        + "Response Time : "
        + responseTime;

    //I use line because I felt it was easier to code with
    return line;

  }

  /**
   * Creates a Screen object.
   * Screen objects are generally created in the MoviePlayer class
   *
   * @param  resolution screen resolution
   * @param  refreshRate screen refresh rate
   * @param  responseTime screen response time
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }
}
