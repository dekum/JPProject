package sample;

public class MoviePlayerDriver{
//private String audioSpecification;
//private ItemType mediaType;

  public static void  testMoviePlayer(){
/**
 * Create objects of MoviePlayer
 */
    MoviePlayer mp = new MoviePlayer("DBPOWER MK101",new Screen("720x480",40,22),MonitorType.LCD);
    System.out.println(mp.toString());
    System.out.println("\n");
    MoviePlayer mp2 = new MoviePlayer("Pyle PDV156BK",new Screen("1366x768",40,22),MonitorType.LED);
    System.out.println(mp2.toString());





  }
}