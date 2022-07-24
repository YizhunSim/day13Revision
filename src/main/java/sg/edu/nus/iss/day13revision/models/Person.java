package sg.edu.nus.iss.day13revision.models;

import java.util.UUID;

public class Person {
  private final String id;
  private String firstName;
  private String lastName;

  public Person(String firstName, String lastName){
    this.id = UUID.randomUUID().toString().substring(0, 8);
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

    @Override
  public String toString(){
    return "Person [id=" + this.id + ", firstname=" + this.firstName + ", lastname=" + this.lastName + "]";
  }

}
