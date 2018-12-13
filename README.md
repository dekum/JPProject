# JP Project

 * Philemon Petit-Frere
 * 12/5/2018
 * COP 3003 - Object Oriented Programming
 * Project: JPProject
 * Purpose of Project: design a template in Java for creating and recording all futureproduction line items. Also also allow easy modification to handle different products
 * Sources for help: https://stackexchange.com/, https://stackoverflow.com/,  Garrett Graber, Nathalie Crespo, github.com, http://tutorials.jenkov.com/java-regex/pattern.html, https://www.geeksforgeeks.org/ , https://www.tutorialspoint.com/, 
 
 <img src="https://img.shields.io/badge/release-v2.0.2-blue.svg" />
<img src="https://img.shields.io/badge/package-v2.2.0-orange.svg" />

## Table of Contents
- [Goal](#Goal)
- [Overview](#OverView)
- [Images](#Images)
- [Diagrams](#Diagrams)
- [Demo](#Demo)
- [Bug/Feature Request](#bugfeature-request)
- [HowToInstall](#How-To-Install)
- [Updates](#Updates)
- [Credits](#Credits)
- [License](#License)


<h3>Goal</h4>
 <hr>
<p>
 * Create a production plant for any type of product ranging from a simple packaging system to a variety of electronic devices.
* Create a production line for multimedia devices which include music and movie players.
* Design a template in Java for creating and recording all future production line items.  

<h3>OverView</h3>
<hr>
<p> This is a Java GUI project, that is designed to allow the user to create Audio Players and Movie Player products of their choice. They will be able to input their specifications of the products, and the products will be displayed on the Home Window in a table View. The user can also see the statistics of the movie Player, such as the total number of products, how many are movie players and how many are unique.</p>

This project now implements a database. Using [Derby](https://db.apache.org/derby/) and sql statements, the program now interacts with a database which stores the Audio Players, Products and MoivePlayer objects created by the user. The user can also add, delete, or update products in the database.
 
 
 This project followed [GUI Design Principles](https://en.wikibooks.org/wiki/GUI_Design_Principles) by first making the project aesthetically pleasing and easy to look at. The Stats window shows a graph to easily see data, and the contrast between colors is clear. Clarity is emphasized, by color and text, buttons are clearly defined and don't offer any surprises to the user. When tableView rows are clicked, the color changes to show the change the same as buttons. Each window is consistent, and the buttons perform the action as stated. The program was extensively tested so runtime errors s should infrequent if any. The control is given to the user to perform the tasks they want. Textboxes offer a "CTRL-Z" function to undo mistakes. The user's work isn't lost when they enter bad inputs, errors clearly display what the user did wrong.

</p>
<h3>Images</h3>
<h4>Home Window </h4>
 <hr>
 
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/HomeScreen.png)<br>
* The HomeScreen is the screen the user sees when they open the GUI. 
* On the left is a Table with the list of products. The program starts with its own default example list. 
* The table displays the name of the product and whether it is a Movie player or Audio Player. <br>The table updates as the user adds more products. 
* On the right the user can click 3 buttons to open the other 3 windows listed
* If the User wished to know more information about a certain product they can click the product and press the "More Info" button.

<h4>Home Window - Information Alert</h4>
<hr>

![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/HomeScreenMoreInfo.png)<br>
 * This alert box popups, when a product is clicked on and the "More Info" button is pressed. *
 * The alert displays the product's toString method which contains its name, type, manufactured date,  serial number, amongst other details.

  <h4>Add AudioPlayer Window</h4>
 <hr> 
 
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddAudioPlayerWindow.png)<br>
* This window is displayed when User wants to add an Audio Player. The textfields have default inputs for user-easiness. 
* The user enters the name, audio Specification, and how many copies of this product they want. If successfully an alert will signal success. 
* However if there are bad inputs, an error alert will tell the user what's wrong.<br>
  <hr>
  <h4>Add AudioPlayer Window- Success</h4>
  
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddAudioPlayerSuccess.png)<br>
* This is the alert that will pop up if the User successfully creates an Audio Player. 
* The number of copies and product name is shown. The Movie Player window has a similar pop-up. <br>

<h4>Add Movie Player Window</h4>
<hr>

 ![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddMoviePlayerWindowEx.png)<br>
 <p> This window is displayed when the user selected "Add Movie Player". Just like the Audio Player window, default data is already filled in textfields. <br>Movie Player window has a choice of Monitor Type, which is displayed in a drop-down choice box element. <br>The data is this picture has an invalid # of copies to demonstrate the error message shown in the next picture.
</p> <br>

  <h4>Add Movie Player Window- Error Alert</h4>
  <hr>
  
![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/AddMoviePlayerError.png)<br>
* This is the popup Alert the user sees if they enter 0 or a negative number in the number of the Copies text field.
* The error message changes depending on which fields are invalid.

 <br>

<h4>Statistic Window</h4>
<hr>

![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/StatsWindow.png)<br>
* This window has a table of Stats that shows analysis the product List the user created. 
* The Statistics automatically update as new products are created. 
* The Stats described here:<br>
 
 | Stat Name | Description |
| --- | --- |
| Total Products | The count of audio players and Movie Players |
| Total Unique | The count of products there are, minus the duplicates. |
| Total Duplicates | The count of products subtracted by the total unique. |
| Total Audio Players | The count of Audio Players.  |
| Total Movie Players | The count of Movie Players.  |
| Total LCDs | The count of Movie players with LCD Monitor Type.  |
| Total LEDs| The count of Movie players with LED Monitor Type.  |
 
* Duplicates are calculated by creating a Set, with the elements of the productList. 
* Set will automatically remove any duplicates using's Product class's equals method and hascode method.
* If two products have the same class and same name, one is considered unique and the other duplicate.

`projectclasses.Product` equals method
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

`fxmlandcontrollers.ControllerStats` initalize method for calcuating duplicates and uniques.
```
/*This checks how many are original or copies by creating a Set of the productList
    Since Set lists cannot contain duplicates, it will count how many are unique
    */
    Set inputSet = new HashSet(productList);
    nubOfOriginal = inputSet.size();
    if (inputSet.size() < productList.size()) {
      numOfCopies = productList.size() - inputSet.size(); //All - unique
      nubOfOriginal = inputSet.size(); //numOfOrigoma;
    }
 ```

 <h3>Diagrams</h3>
  <hr>
  
 ![Gui Picture]( https://github.com/dekum/JPProject/blob/master/images/fxmlsandcontrollersUML.png)<br>
UML Diagram for the controllers <br>
 
 ![Gui Picture](https://github.com/dekum/JPProject/blob/master/images/projectclassesUML.png)<br>
 UML Diagram for the classes of this program. Blue Lines show inheritance, and green lines show implementing.<br>
Item is an interface with its own method headers  that is implemented by Product, thus no objects can be created from it. <br> Product is an public abstract class that implements, Item class and its methods. Since it's abstract no objects can be created from it.
<br> Movie Player and AudioPlayer are subclasses of product, and call Product's constructor when objects are made from them. The two classes also implement Multimedia Control interface, and have defined methods that were implemented.

[JavaDoc](https://dekum.github.io/JPProject/docs/index.html) Javadocs can be seen here.

![DataBaseDiagram](https://github.com/dekum/JPProject/blob/master/images/DataBaseDiagram.png)
<p> The database diagram. The AudioPlayer and MoviePlayer tables are joined to the Product Table where the serial numbers match.
<br>
 
<h3>Demo</h3>
<hr>

![Gui Demo]( https://github.com/dekum/JPProject/blob/master/images/Demo.gif)<br>
This gif shows an example of how the GUI works. On the home screen, the user can select a product and see more information. Next is the AudioPlayer create the screen, where successful inputs are selected. The Movie Player window is shown afterward, and the error message is displayed because of invalid inputs. Lastly, the stat Windows is shown.

<h3>Bug/Feature Request</h3>
<hr>
<p>If you find a bug, please report it by opening an issue.

If you'd like to request a new function, feel free to do so by opening an issue [here](https://github.com/dekum/JPProject/issues).</p>

<h4>How To Install</h4>
<hr>
<p>
* Needed: Windows Vista or higher. Java 1.8 or higher.
* A java IDE is needed if you going to import from github.
 <br>
* For those without IDE, a jar file is provided, and works withoout the use of one.
 * The Jar file is located in deliverables>artifacts>JPProject.jar
</p>
<br> 

<h3>Updates</h3>
<hr>
<p>
 
*  2.0.0: Added database functionality. Using Derby this project is now connected to a database. The user can now add, remove and update products in the database. Now the information is stored so when the user closes the program, their changes are still there.
 
 * 1.4.0: Added CSS to make the project look more presentable. A slider was added to add MoviePlayer, to allow better options for resolution.
 
 * 1.2.0: Added function to play music, via a "Play and pause" button. This required the use of mediaView and MediaPlayer classes.
 
 </p>
<br>
<h3>Credits</h4>
<hr>
 JP Project is a scenario created by Oracle Academy for the purpose of teaching Java.
 
This program is authored by Philemon Petit-Frere

This project is supported by:
<a href="https://www.jetbrains.com/idea/">
    <img src="https://github.com/Hexworks/zircon/blob/master/images/idea_logo.png" width="40" height="40" />
</a>

<h3>License</h4>
<hr>
<p>JP Project is released under the  <a href="https://www.gnu.org/licenses/gpl-3.0.en.html">GNU GPLv3</a> license.</p>
