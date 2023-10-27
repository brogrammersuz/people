package uz.brogrammers.people.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.people.dao.PersonDao;
import uz.brogrammers.people.model.Person;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDao personDao;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDao.getAll());

        return "people/index";
    }

    @GetMapping("/person/{id}")
    public String getById(@PathVariable() Integer id, Model model) {

        Optional<Person> foundPerson = personDao.getPersonById(id);

        if (foundPerson.isPresent())
            model.addAttribute("person", foundPerson.get());

        return "people/person";
    }

    @GetMapping("/search")
    public String getById(@RequestParam(value = "name") String name, Model model) {

        List<Person> list = personDao.getPeopleByName(name);
        model.addAttribute("people", list);

        return "people/index";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        var person = new Person();
        model.addAttribute("person", person);

        return "people/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {

        System.out.println(bindingResult.getModel().entrySet().toArray());

        if (bindingResult.hasErrors()) {
            return "people/create";
        }
        personDao.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") Integer id, Model model) {
        var person = personDao.getPersonById(id);

        if (person.isPresent())
            model.addAttribute("person", person.get());

        return "people/create";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute("person") Person person) {
        personDao.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        personDao.delete(id);

        return "redirect:/people";
    }

}
