package sample;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmployeeInfo.javan
 * 10/26/2018
 * Philemon Petit-Frere
 * Done with step 20
 * The department code is made up of four letters and two numbers.
 * The format of the department code is the first letter must be in uppercase with the following three all
 * being lowercase and no spaces.
 *
 *
 * sources: https://regexr.com/ (For making java patterns)
 */

public class EmployeeInfo {

  StringBuilder name = new StringBuilder(); //Need to Initalize a stringbuilder because you can append it.
  String code;
  String deptId;
  Pattern p;
  Scanner in;

  public String reverseString(String id){
    char letter = id.charAt(id.length()-1);

    if ( id.length() == 1){
      return Character.toString(letter);

    }else{
      // System.out.println(letter);
      return letter+reverseString(id.substring(0,id.length()-1));
    }

  }

  public String getDeptId(){
    System.out.println("Please enter the department ID:");
    String input2 = in.nextLine();
    //   String input2 = "Jake22";;
    return input2;

  }
  private void setDeptId(){
    String inputId = getDeptId();
    //Boolean isValid = validId(deptId);
    if (validId(inputId)){
      //if the ID is valid, then match then reverse the String
      deptId=reverseString(inputId);
     // deptId=inputId;

    }else{
      deptId = "None01";
    }
    // System.out.println(deptId);

  }
  private String getId(){
    return deptId;
  }
  private boolean validId(String id){
    Boolean matches =false;

    Matcher matcher = p.matcher(id);
    matches = matcher.matches();
    // if (matches){
    //   System.out.println("MATCGHES!!!");
    // }

    return matches;
    // return deptId.equals(id);

  }

  public StringBuilder getName(){
    return name;
  }
  public String getCode(){
    return code;
  }
  private void setName()
  {
    name.append(inputName());
  }
  private void createEmployeeCode(StringBuilder name)
  {
    Boolean containsSpace = name.toString().contains(" ");
    if (containsSpace){
      code = name.toString().substring(0,1);
      int spot = name.toString().indexOf(" ");
      int spot1 = spot;
      code += name.toString().substring(++spot,name.length());

    } else
    {
      //No space default guest
      code = "Guest";
    }
    //System.out.println(code);



  }
  private String inputName(){
    //Scanner scanner = new Scanner(System.in);

    System.out.print("Please enter your first and last name: ");
    String input1 = in.nextLine();
    //name.append(input1);
    //System.out.println(input1);

    return input1;

  }
  private boolean checkName(StringBuilder name)
  {
    Boolean validInput = false;
    // if (name.length()==0){
    //   System.out.println("Sorry you didn't enter anything try again");
    //   inputName();
    // } else{

    // }
    Boolean containsSpace = name.toString().contains(" ");
    if (containsSpace)
    {
      //String isn't empty and has a space  so it must be valid
      validInput= true;
    }
    // else
    // {
    //   System.out.println("No Space");

    // }
    return validInput;
  }

  public EmployeeInfo() {
    in = new Scanner(System.in);

    setName();
    checkName(name);
    createEmployeeCode(name);
    String patternString = "([A-Z])[a-z][a-z][a-z]\\d\\d";
    p = Pattern.compile(patternString);
    setDeptId();
    in.close();


  }

  @Override
  public String toString() {
    return "Employee Code : " + code +
        "\nDepartment Number : " + deptId ;
  }
}
