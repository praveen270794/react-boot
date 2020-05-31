package io.praveen.reactbootapp;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.praveen.reactbootapp.model.Event;
import io.praveen.reactbootapp.model.Group;
import io.praveen.reactbootapp.model.GroupRepository;
@Component
public class Initializer implements CommandLineRunner{
	
	private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Noida Events Group", "Banglore Events Group", "Pune Events Group",
                "Hydrabad Events Group").forEach(name ->{
                	
                Group grp = new Group(name);
                grp.setCity(name.split(" ")[0]);
                Event e = Event.builder().title("Full Stack Reactive")
                        .description("Reactive with Spring Boot + React")
                        .date(Instant.parse("2020-05-25T15:00:00.000Z"))
                        .build();
                grp.setEvents(Collections.singleton(e));
                repository.save(grp);
                });

        repository.findAll().forEach(System.out::println);
    }

}
