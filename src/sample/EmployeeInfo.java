/**
 * EmployeeInfo.java
 * 10/26/2018
 * @author Philemon Petit-Frere
 * The department code is made up of four letters and two numbers.
 * The format of the department code is the first letter must be in uppercase with the following
 * three all being lowercase and no spaces.
 * The Employee code is the first letter of Employee firstname plus last name.
 * sources: https://regexr.com/ (For making java patterns)
 */

package sample;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeInfo {

  /**
   * First and Last name of the Employee, which user inputs.
   */
  StringBuilder name = new StringBuilder();
  /**
   * The employee code, which is the first latter of First name
   * combined with last name.
   */
  String code;
  /**
   * deptID is 6 characters.
   * First Leter is capital followed by 4 lower case letters and then followed by 2 numbers.
   * Is it stored in reverse for security.
   */
  String deptId;
  /**
   * Pattern used to check is user inputted a valid ID.
   * The pattern is Capital letter, 3 lower case, 2 numbers.
   */
  Pattern pattern;
  /**
   * Scanner to read input used multiple times in this class.
   */
  Scanner in;

  /**
   * This method is a recursive method that reverse the employeeId.
   *
   * @param  id the employee code, to be reversed.
   * @return  the employee code reversed.
   */
  public String reverseString(String id) {
    char letter = id.charAt(id.length() - 1);

    if (id.length() == 1) {
      return Character.toString(letter);

    } else {
      return letter + reverseString(id.substring(0,id.length() - 1));
    }
  }

  /**
   * This method is used to prompt the user to enter an input for the deptID.
   */
  public String getDeptId() {
    System.out.println("Please enter the department ID:");
    String input2 = in.nextLine(); //get scanner input
    return input2;

  }

  /**
   * This method is used to set the DeptId by calling getDeptId() .
   * And  then checking if it's valid with validID.
   * If it's a valid Input then the deptID is set to the reverse of the input.
   * Otherwise, deptID is set to "None01".
   */
  private void setDeptId() {
    String inputId = getDeptId();
    //Boolean isValid  =  validId(deptId);
    if (validId(inputId)) {
      //if the ID is valid, then match then reverse the String
      deptId = reverseString(inputId);

    } else {
      deptId = "None01";
    }

  }

  /**
   * This method is used to return string deptID.
   * @return the deptId
   */
  private String getId() {
    return deptId;
  }

  /**
   * This method checks if the user inputted id is a valid deptID code.
   * A valid deptID is 6 characters, first Uppercase. 3 lower case, 2 numbers.
   *
   * @param  id the deptID, the user input that will be checked
   * @return  the boolean result of matching the id with the regex pattern.
   */
  private boolean validId(String id) {
    Boolean matches = false; //initialize the boolean

    //pattern complied in constructor
    Matcher matcher = pattern.matcher(id); //Check id matches pattern
    matches = matcher.matches(); //true or false

    return matches;

  }

  /**
   * Returns the name of Employee.
   *
   * @return the name of Employee
   */
  public StringBuilder getName() {
    return name;
  }

  /**
   * Returns the employee Code.
   *
   * @return the employee code
   */
  public String getCode() {
    return code;
  }

  /**
   * Changes the name of the employee by using the inputName method.
   */
  private void setName() {
    name.append(inputName());
  }

  /**
   * Create an employee code, using the name sent in the parameter.
   * This method checks if the name contains a space, then will split the string at the space
   * and will set the employee code to first Letter of nam combined with last name
   * If there is no space detected, the code will be "Guest
   *
   * @param name the name of the Employee inputted by the user
   */
  private void createEmployeeCode(StringBuilder name) {

    if (checkName(name)) {
      code = name.toString().substring(0,1);
      int spot = name.toString().indexOf(" ");
      int spot1 = spot;
      code += name.toString().substring(++spot,name.length());
    } else {
      //No space default guest
      code = "Guest";
    }

  }

  /**
   * Prompts user to input first name and last name.
   * A scanner is used to read input
   *
   * @return input1 The name the user inputted.
   */
  private String inputName() {
    //Scanner scanner = new Scanner(System.in);

    System.out.print("Please enter your first and last name: ");
    String input1 = in.nextLine();
    //name.append(input1);
    //System.out.println(input1);

    return input1;

  }

  /**
   * Checks the employee Name for a space and returns a boolean result of that check.
   *
   * @param  name the user inputted name.
   * @return validInput a boolean that is true if parameter name has s space.
   */
  private boolean checkName(StringBuilder name) {
    Boolean validInput = false;
    // if (name.length() == 0) {
    //   System.out.println("Sorry you didn't enter anything try again");
    //   inputName();
    // } else{

    // }
    Boolean containsSpace = name.toString().contains(" ");
    if (containsSpace) {
      //String isn't empty and has a space  so it must be valid
      validInput = true;
    }
    // else
    // {
    //   System.out.println("No Space");

    // }
    return validInput;
  }

  /**
   * Constructor to create a EmployeeInfo object.
   * Asks user to input a name, checks the name for a space then calls createEmployeeCode
   * Then it sets a pattern for the deptID which the user inputs a deptID.
   * The deptId is then validated and the EmployeeInfo object is created.
   */
  public EmployeeInfo() {
    in = new Scanner(System.in);

    setName();
    createEmployeeCode(name);
    String patternString = "([A-Z])[a-z][a-z][a-z]\\d\\d";
    pattern = Pattern.compile(patternString);
    setDeptId();
    in.close();


  }

  /**
   * Modifies class's toString.
   * Contents of each field are placed into the result with one per line.
   *
   * @return code and deptID
   */
  @Override
  public String toString() {
    return "Employee Code : "
        + code
        + "\nDepartment Number : " + deptId;
  }
}
