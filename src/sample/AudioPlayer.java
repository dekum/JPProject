package sample;

/**
 * AudioPlayer.java
 * A class extending from Product and implements the MultimediaControl interface.
 * @Philemon Petit-Frere
 * 10/10/18
 *
 */

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class AudioPlayer extends  Product implements MultimediaControl {
  private String audioSpecification;
  private ItemType mediaType;



  public AudioPlayer() {
    /**
     * A default constructor is test AudioPlayerclass
     */
    super("The Zune");
    audioSpecification = "MP3-A";
    mediaType = ItemType.AUDIO;

  }

  AudioPlayer(String name, String audioSpecification){
    /**
     * Normal constructor used.
     * @param name , set the object's name
     */
    super(name);


    this.audioSpecification= audioSpecification;
    this.mediaType= ItemType.AUDIO ;

  }

  public String getAudioSpecification() {
    return audioSpecification;
  }

  @Override
  public void play() {
    System.out.println("Playing");

  }

  @Override
  public void stop() {
    System.out.println("Stopping");

  }

  @Override
  public void previous() {
    System.out.println("Previous");

  }

  @Override
  public void next() {
    System.out.println("Next");

  }

  @Override
  public String toString() {
    return
        super.toString()+
            "\nAudio Spec : " + audioSpecification +
            "\nType : " + mediaType
        ;
  }

  public static void main(String[] args){
    AudioPlayer ap = new AudioPlayer("J","Lw");
    System.out.println(ap.toString());

  }


@Override
  public int compareTo(Object o) {
  /**
   * This method will call Product's compareTo, where it will sort by name
   */
  return super.compareTo(o); //Product's compareTo result be will be the same as this

  }
}
