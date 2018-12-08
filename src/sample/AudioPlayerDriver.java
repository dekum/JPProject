package sample;

/**
 * AudioPlayerDriver.java
 * @author Philemon Petit-Frere
 * 11/10/2018
 * This class is used to test objects of AudioPlayer Class
 */

public class AudioPlayerDriver {


  public static void  testAudioPlayer() {
    /**
     * this static methods is used to test the functionality of AudioPlayer Class.
     */

    AudioPlayer ap = new AudioPlayer("ipod Mini", "MP3");
    ap.play();
    ap.stop();
    ap.next();
    ap.previous();
    System.out.println(ap.toString());
    AudioPlayer ap2 = new AudioPlayer("Walkman", "WAV");
    System.out.println(ap2.toString());


  }


}