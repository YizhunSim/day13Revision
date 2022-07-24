package sg.edu.nus.iss.day13revision.models;

public class PersonForm {
  private String id;
  private String firstName;
  private String lastName;
  public String getId(){
    return this.id;
  }
  public String getFirstName() {
    return this.firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return this.lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
