package sg.edu.nus.iss.day13revision.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day13revision.models.Person;

@Service
public class PersonService {
  private List<Person> persons = new ArrayList<Person>();

  public PersonService(){
    persons.add(new Person("Mark", "Zucker"));
    persons.add(new Person("Elon", "Musk"));
  }

  public List<Person> getAllPersons(){
    return this.persons;
  }

  public Person getPerson(String id){
    for (Person p : persons){
      if(p.getId().equals(id)){
        return p;
      }
    }
    return null;
  }

  public void addPerson(Person p){
    persons.add(new Person(p.getFirstName(), p.getLastName()));
  }

  public String updatePerson(Person p){
    boolean personFound = false;
    for (Person currentPerson : persons){
      if (currentPerson.getId().equals(p.getId())){
        personFound = true;
        currentPerson.setFirstName(p.getFirstName());
        currentPerson.setLastName(p.getLastName());
      }
    }
    if (!personFound){
      persons.add(p);
      return "Person added Successfully";
    } else{
      return "Person updated Successfully";
    }

  }

  public String deletePerson(Person p){
    boolean personFound = false;
    for (Person currentPerson : persons){
      if (currentPerson.getId().equals(p.getId())){
        personFound = true;
        persons.remove(currentPerson);
        break;
      }
    }
    if (personFound){
      return "Person deleted successfully!";
    }
    return "No Person deleted";
  }

}
