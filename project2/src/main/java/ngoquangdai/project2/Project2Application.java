package ngoquangdai.project2;

import lombok.RequiredArgsConstructor;
import ngoquangdai.project2.entity.Person;
import ngoquangdai.project2.entity.Role;
import ngoquangdai.project2.repository.PersonRepository;
import ngoquangdai.project2.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class Project2Application implements CommandLineRunner {

    public static void main(String[] args)   {
        SpringApplication.run(Project2Application.class, args);
    }
    public final PersonRepository personRepository;
    public final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception{
//        Role role = new Role();
//        role.setName("admin");
//
//        Person person = new Person();
//        person.setUsername("daingo");
//        person.setPassword("1234");
//        person.setRole(role);
//
//        role.setPersons(Collections.singleton(person));
//
//        roleRepository.saveAndFlush(role);
//
//        personRepository.findAll().forEach(p -> {
//            System.out.println(p.getUsername());
//            System.out.println(p.getPassword());
//            System.out.println(p.getRole());
//        });

        Role role1 = new Role();
        role1.setName("user");

        Person person1 = new Person();
        person1.setUsername("client");
        person1.setPassword("1234");
        person1.setRole(role1);

        role1.setPersons(Collections.singleton(person1));

        roleRepository.saveAndFlush(role1);

        personRepository.findAll().forEach(p -> {
            System.out.println(p.getUsername());
            System.out.println(p.getPassword());
            System.out.println(p.getRole());
        });
    }
}
