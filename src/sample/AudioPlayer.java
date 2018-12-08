package sample;

public class AudioPlayer extends  Product implements MultimediaControl {
  /**
   * AudioPlayer.java
   * @author Philemon Petit-Frere
   * A class extending from Product and
   * implements the MultimediaControl interface.
   * Created: 11/10/18
   */

  /**
   * audio Specification of this audioPlayer.
   */
  private final String audioSpecification;
  /**
   * the ItemType enum of this AudioPlayer.
   */
  private final ItemType mediaType;

  /**
   * A default constructor is used to test AudioPlayer class.
   */
  public AudioPlayer() {
    super("The Zune");
    audioSpecification = "MP3-A";
    mediaType = ItemType.AUDIO;

  }

  /**
   * Normal constructor used.
   *
   * @param  name the name of the AudioPlayer to be set by Product's constructor
   * @param  audioSpecification the Specification parameter.
   */
  public AudioPlayer(String name, String audioSpecification) {
    super(name);

    this.audioSpecification = audioSpecification;
    this.mediaType = ItemType.AUDIO;
  }

  /**
   * Prints Playing.
   */
  @Override
  public void play() {
    System.out.println("Playing");

  }

  /**
   * Prints Stopping.
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * Prints Previous.
   */
  @Override
  public void previous() {
    System.out.println("Previous");

  }

  /**
   * Prints Next.
   */
  @Override
  public void next() {
    System.out.println("Next");

  }

  /**
   * Modifies the toString Method.
   * Uses Product's toString to return the name.
   * Contents of each field are placed into the result with one per line.
   *
   * @return  the name, audio Specification, and mediaType
   */
  @Override
  public String toString() {
    return
        super.toString()
            + "\nAudio Spec : "
            + audioSpecification
            + "\nType : "
            + mediaType
        ;
  }

  /**
   * A driver method to test the AudioPlayer Class.
   *
   * @param  args an array of command-line arguments for the application
   */
  public static void main(String[] args) {
    AudioPlayer ap = new AudioPlayer("J","Lw");
    System.out.println(ap.toString());
  }

  /**
   * This method will call Product's compareTo, where it will sort by name.
   * @param  o is a object that will be accepted by this method
   */
  @Override
  public int compareTo(Object o) {
    //Product's compareTo result be will be the same as this
    return super.compareTo(o);
  }
}
