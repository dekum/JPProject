# ProjectAlpha

 * Philemon Petit-Frere
 * 10/29/2018
 * COP 3003 - OOP
 * Project: JPProject
 * Purpose of Project: design a template in Java for creating and recording all future
 * production line items. Also also allow easy modification to handle different products
 *Sources for help: Stackexchange, Garrett Graber, Nathalie Crespo, github.com, http://tutorials.jenkov.com/java-regex/pattern.html, geeksforgeeks,https://www.tutorialspoint.com/, 
 
<h4>Goal:</h4>
<p>Create a production plant for any type of product ranging from a simple packaging system to a variety of electronic devices.
create a production line for multimedia devices which include music and movie players.
Design a template in Java for creating and recording all future production line items.  
For this particular production facility you will only implement a concrete class for music and movie players.</p>
<h4>Step 1</h4>
<p>Create a Item inteface with the following methods

  void setProductionNumber(int parameter);

  void setName(String name);

  String getName();

  Date getManufactureDate();

  int getSerialNumber();</p></p>
  <h4>Step 2</h4>
Create ItemType Enum with a type: (type,code)

  Type | Code
------------ | -------------
Audio | AU
Visual | VI
  AUDIOMOBILE| AM
  VISUALMOBILE|VM
  
  <h4>Step 3</h4>
  <p>Create a abstract class called Product which will implement the methods created earlier. It will add {} and code to the methods defined in Item interface. These fields are also created
  <br>
  Date manufacturedOn;<br> 
  String name;<br>
  final String MANAFACTURER = "OracleProduction";<br>

  static int currentProductionNumber = 0;

<h3> Summary </h3>

* There exists a Main class, a Widget class, Product class, and Item interface.
* Main Class is the driver class, which also contains widget
* Widget class, extends Product and will intialize it when in Main method
* Product class is an abstract class that implements Item's methods, and provides methods for Widget
* Item interface creates classes for Product to implement. Also contain ItemType Enum with fields.

When user runs Main(), it will display:

* Manufacturer
* Serial Number
* Date created
* Name of product

Next then it will display ItemType's fields.

Tested on Windows 10, IntelJ IDEA 32-bit on 10/28/18.

</p>

<h3>Techniques
 <p>Program demostrates <hr> 
 **Polymorphism**: is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object.

Any Java object that can pass more than one IS-A test is considered to be polymorphic. In Java, all Java objects are polymorphic since any object will pass the IS-A test for their own type and for the class Object.

In this project, Chasis is a child class of __




