package sample;

public class AudioPlayerDriver{
//private String audioSpecification;
//private ItemType mediaType;

  public static void  testAudioPlayer(){

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