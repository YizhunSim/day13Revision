package sg.edu.nus.iss.day13revision.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.day13revision.models.Person;
import sg.edu.nus.iss.day13revision.models.PersonForm;
import sg.edu.nus.iss.day13revision.services.PersonService;

@Controller
@RequestMapping(path="/")
public class PersonController {
  private List<Person> personList = new ArrayList<Person>();

  @Autowired
  PersonService personService;

  @Value("${welcome.message}")
  private String message;

  @Value("${error.message}")
  private String errorMessage;

  @RequestMapping(value={"/", "/home", "/index"}, method=RequestMethod.GET)
  public String index(Model model){
    model.addAttribute("message", message);
    return "index";
  }

  @RequestMapping(value="/testRetrieve", method=RequestMethod.GET, produces="application/json")
  public @ResponseBody List<Person> getAllPersons(){
    personList = personService.getAllPersons();

    return personList;
  }

  @RequestMapping(value="/personList", method=RequestMethod.GET)
  public String personList(Model model){
    personList = personService.getAllPersons();
    model.addAttribute("persons", personList);
    return "personList";
  }

  @RequestMapping(value="/addPerson", method=RequestMethod.GET)
  public String showAddPersonPage(Model model){
    PersonForm pForm = new PersonForm();
    model.addAttribute("personForm", pForm);
    return "addPerson";
  }

  @RequestMapping(value="/addPerson", method=RequestMethod.POST)
  public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
    String fName = personForm.getFirstName();
    String lName = personForm.getLastName();

    if (fName != null && fName.length() > 0 && lName != null && lName.length() > 0){
        Person newPerson = new Person(fName, lName);
        personService.addPerson(newPerson);

        return "redirect:/personList";
    }
    else{
      model.addAttribute("errorMessage", errorMessage);
      return "addPerson";
    }
  }

  @GetMapping(value="/editPerson/{id}", produces="text/html")
  public String showEditPersonForm(@PathVariable("id") String id, Model model){
    Person p = personService.getPerson(id);
    System.out.printf("> id: %s\n", id);
    System.out.println(p);

    model.addAttribute("person", p);
    return "editPerson";
 }



   @PostMapping(value="/updatePerson/{id}", produces="text/html")
  public String updatePerson(@PathVariable("id") String id, @ModelAttribute("personForm") PersonForm personForm, Model model){
    Person p = personService.getPerson(id);
    p.setFirstName(personForm.getFirstName());
    p.setLastName(personForm.getLastName());
    personService.updatePerson(p);
    return "redirect:/personList";
  }

  @GetMapping(value="/deletePerson/{id}", produces="text/html")
    public String deletePerson(@PathVariable("id") String id){
      Person p = personService.getPerson(id);
      personService.deletePerson(p);
      return "redirect:/personList";
    }



  // @GetMapping()
  // public boolean addPerson(){
  //   return true;
  // }

  // @PostMapping(consumes="application/www-x-form-urlencoded", produces="text/html")
  // public String processAddPerson(){
  //   return "addPerson";
  // }

}
