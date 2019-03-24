public class Class_SimpleLocation
{
  //Member variable: should always be private, because the Class designer should have ultimate control over who gets to see and see the data that's stored in each object of the class
  private double latitude;
  private double longitude;


  //Default constructor: no pass-in parameters
  //Overloading: there are two different copies of the constructor that takes different numbers and arguments
  public SimpleLocation()
  {
    this.latitude = 44.98;
    this.longitude = -93.27;
  }

  //Parameter Constructor

  public SimpleLocation(double lat, double lon)
  {
    this.latitude = lat;
    this.longitude = lon;
  }

  //getter: takes the value of a member variable that's private and expose if outside of that class.

  public double getLatitude()
  {
    return this.latitude;

  }

  //setter: allows somebody to change the value of a member variable; you should build some logic inside your setter to check whether a user defined value is legal to change your member variable
  public void setlatitude(double lat)
  {
    if (lat < -180 || lat > 180){
      System.out.println("Illegal value for latitude")
    }
    else {
      this.latitude = lat
    }
  }



}
