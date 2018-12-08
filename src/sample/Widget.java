package sample;

abstract class Widget extends Product {
  //Widget extends Product
  //as Product is a abstract class and thus will not be able to make an object
  public Widget(String name) {
    //Widget accepts a name, then uses that name as a parameter for Product's constructor
    super(name);//The super of Widget is Product, this will call it's constructor.
  }

}