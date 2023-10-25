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

    public Person getPerson(Integer id) {
        if (id < (this.people.size())) {
            return this.people.get(id);
        }
        return null;
    }

    public Optional<Person> getPersonById(Integer id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    public List<Person> getAll() {
        return this.people;
    }

    public void update(Integer id, Person person) {
        var foundPerson = getPersonById(id);
        if (foundPerson.isPresent()) {
            var index = people.indexOf(foundPerson);
            System.out.println(index);
            people.set(index, person);
        }
    }

    public void delete(Integer id) {
        this.people.remove(id);
    }

}
