package sample;

public class Screen implements ScreenSpec {
  String resolution;
  int refreshRate;
  int responseTime;

  @Override
  public String getResolution() {
    return null;
  }

  @Override
  public int getRefreshRate() {
    return 0;
  }

  @Override
  public int getResponseTime() {
    return 0;
  }

  @Override
  public String toString() {
    String line = "Resolution : " + resolution + "\n" +
        "Refresh Rate : " + refreshRate + "\n" +
        "Response Time : " + responseTime;

    //I use line because I felt it was easier to code with
    return line;

  }
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }
}
