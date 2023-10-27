package uz.brogrammers.people.dao;

import org.springframework.stereotype.Service;
import uz.brogrammers.people.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDao {

    public static int PEOPLE_COUNT = 0;
    private List<Person> people = new ArrayList<>(); //[Person(3, "Lily"), Person(1, "Bob")]

    {
        people.add(new Person(++PEOPLE_COUNT, "Tom", "John", "tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", "Rock", "bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", "Freeman", "mike@hotmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", "Angel", "katy@gmail.com"));
    }

    public void addPerson(Person person) {
        person.setId(++PEOPLE_COUNT);
        this.people.add(person);
    }

    public Optional<Person> getPersonById(Integer id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    public Optional<Person> getPersonByName(String name) {
        return people.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst();
    }

    public List<Person> getPeopleByName(String name) {
        return people.stream()
                .filter(person -> person.getName().equals(name))
                .toList();
    }

    public List<Person> getAll() {
        return this.people;
    }

    public void update(Person person) {
        var optionalPerson = getPersonById(person.getId());
        if (optionalPerson.isPresent()) {
            var foundPerson = optionalPerson.get();
            var index = people.indexOf(foundPerson);
            people.set(index, person);
        }
    }

    public void delete(Integer id) {
        this.people.removeIf(person -> person.getId() == id);
    }

    public void delete(Person person) {
        this.people.remove(person);
    }

}
