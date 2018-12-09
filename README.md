# JP Project

 * Philemon Petit-Frere
 * 12/5/2018
 * COP 3003 - Object Oriented Programming
 * Project: JPProject
 * Purpose of Project: design a template in Java for creating and recording all futureproduction line items. Also also allow easy modification to handle different products
 * Sources for help: https://stackexchange.com/, https://stackoverflow.com/,  Garrett Graber, Nathalie Crespo, github.com, http://tutorials.jenkov.com/java-regex/pattern.html, https://www.geeksforgeeks.org/ , https://www.tutorialspoint.com/, 
 
 <img src="https://img.shields.io/badge/release-v1.1.0-blue.svg" />
<img src="https://img.shields.io/badge/package-v2.0.0-orange.svg" />

## Table of Contents
- [Goal](#Goal)
- [Overview](#OverView)
- [Images](#Images)
- [UMl Diagrams)(#UMl Diagrams)
- [Demo](#Demo)
- [Bug/Feature Request](#Bug/Feature Request)
- [Credits](#Credits)
- [License](#License)

 <hr>
<h3>Goal</h4>
<p>Create a production plant for any type of product ranging from a simple packaging system to a variety of electronic devices.
create a production line for multimedia devices which include music and movie players.
<br>Design a template in Java for creating and recording all future production line items.  
<hr>
<h3>OverView</h3>
This is a Java GUI project, that is designed to allow the user to create Audio Players and Movie Player products of their choice. They will be able to input their specifications of the products, and the products will be displayed on the Home Window in a table View. The user can also see the statistics of the movie Player, such as the total number of products, how many are movie players and how many are unique.

<hr>
<h3>Images</h3>

<h4>Home Window </h4>
 
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/HomeScreen.png)<br>
<p>The HomeScreen is the screen the user sees when they open the GUI. <br>On the left is a Table with the list of products. The program starts with its own default example list. <br>The table displays the name of the product and whether it is a Movie player or Audio Player. <br>The table updates as the user adds more products. <br> On the right the user can click 3 buttons to open the other 3 windows listed
<br>If the User wished to know more information about a certain product they can click the product and press the "More Info" button.</p><br>
<hr>
<h4>Home Window - Information Alert</h4>

![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/HomeScreenMoreInfo.png)<br>
 <p> This alert box popups, when a product is clicked on and the "More Info" button is pressed. <br>The alert displays the product's toString method which contains its name, type, manufactured date,  serial number, amongst other details.
<hr>
  <h4>Add AudioPlayer Window</h4>
  
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddAudioPlayerWindow.png)<br>
<p> This window is displayed when User wants to add an Audio Player. The textfields have default inputs for user-easiness. <br>The user enters the name, audio Specification, and how many copies of this product they want. If successfully an alert will signal success. <br>However if there are bad inputs, an error alert will tell the user what's wrong.<br>
  <hr>
  <h4>Add AudioPlayer Window- Success</h4>
  
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddAudioPlayerSuccess.png)<br>
<p> This is the alert that will pop up if the User successfully creates an Audio Player. <br>The number of copies and product name is shown. The Movie Player window has a similar pop-up. </p><br>
<hr>
<h4>Add Movie Player Window</h4>

 ![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddMoviePlayerWindowEx.png)<br>
 <p> This window is displayed when the user selected "Add Movie Player". Just like the Audio Player window, default data is already filled in textfields. <br>Movie Player window has a choice of Monitor Type, which is displayed in a drop-down choice box element. <br>The data is this picture has an invalid # of copies to demonstrate the error message shown in the next picture.
</p> <br>
<hr>
  <h4>Add Movie Player Window- Error Alert</h4>
  
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddMoviePlayerError.png)<br>
<p> This is the popup Alert the user sees if they enter 0 or a negative number in the number of the Copies text field.<br>
 The error message changes depending on which fields are invalid.

</p> <br>
<hr>
<h4>Statistic Window</h4>


![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/StatsWindow.png)<br>
<p> This window has a table of Stats that shows analysis the product List the user created. The Statistics automatically update as new products are created. <br>The Stats described here:<br>
 
 | Stat Name | Description |
| --- | --- |
| Total Products | The count of audio players and Movie Players |
| Total Unique | The count of products there are, minus the duplicates. |
| Total Duplicates | The count of products subtracted by the total unique. |
| Total Audio Players | The count of Audio Players.  |
| Total Movie Players | The count of Movie Players.  |
| Total LCDs | The count of Movie players with LCD Monitor Type.  |
| Total LEDs| The count of Movie players with LED Monitor Type.  |
 
<br>Duplicates are calculated by creating a Set, with the elements of the productList. Set will automatically remove any duplicates using's Product class's equals method and hascode method. If two products have the same class and same name, they are considered to be the same product.

`sample.Product` equals method
``` 
@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(getName(), product.getName());
  }
```
<br>
`fxmlandcontrollers.ControllerStats` initalize method for calcuating duplicates and uniques.
```/*This checks how many are original or copies by creating a Set of the productList
    Since Set lists cannot contain duplicates, it will count how many are unique
    */
    Set inputSet = new HashSet(productList);
    nubOfOriginal = inputSet.size();
    if (inputSet.size() < productList.size()) {
      numOfCopies = productList.size() - inputSet.size(); //All - unique
      nubOfOriginal = inputSet.size(); //numOfOrigoma;
    }
 ```
 <hr>
 <h3>UMl Diagrams</h3>
 
  
 ![Gui Picture]( https://github.com/dekum/JPProject/blob/master/images/Package%20fxmlsandcontrollers.png)<br>
UML Diagram for the controllers <br>
 
 ![Gui Picture]( https://github.com/dekum/JPProject/blob/master/images/sample.png)<br>
 UML Diagram for the classes of this program. Blue Lines show inheritance, and green lines show implementing.<br>
Item is an interface with its own method headers  that is implemented by Product, thus no objects can be created from it. <br> Product is an public abstract class that implements, Item class and its methods. Since it's abstract no objects can be created from it.
<br> Movie Player and AudioPlayer are subclasses of product, and call Product's constructor when objects are made from them. The two classes also implement Multimedia Control interface, and have defined methods that were implemented.
<br>
 <hr>
<h3>Demo</h3>

![Gui Demo]( https://github.com/dekum/JPProject/blob/master/images/Demo.gif)<br>
This gif shows an example of how the GUI works. On the home screen, the user can select a product and see more information. Next is the AudioPlayer create the screen, where successful inputs are selected. The Movie Player window is shown afterward, and the error message is displayed because of invalid inputs. Lastly, the stat Windows is shown.

<h3>Bug/Feature Request</h3>
<p>If you find a bug, please report it by opening an issue.

If you'd like to request a new function, feel free to do so by opening an issue [here](https://github.com/dekum/JPProject/issues).</p>
<hr>
<h4>Credits</h4>
 JP Project is a scenario created by Oracle Academy for the purpose of teaching Java.
 
This program is authored by Philemon Petit-Frere

This project is supported by:
<a href="https://www.jetbrains.com/idea/">
    <img src="https://github.com/Hexworks/zircon/blob/master/images/idea_logo.png" width="40" height="40" />
</a>
<hr>
<h4>License</h4>

JP Project is released under the [GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/) license.
