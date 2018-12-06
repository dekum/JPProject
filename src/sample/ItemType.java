package sample;

enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");
  //Remember to include "" in parenthesis, this was causing a error earlier.

  String code;

  ItemType(String code) {
    //Constuctor method for ItemType enum
    //Without this method, the enum fields for the codes will cause errors
    this.code = code;
  }


  public String getCode() {
    //used by it (in main) to return the code value
    return code;
  }

}