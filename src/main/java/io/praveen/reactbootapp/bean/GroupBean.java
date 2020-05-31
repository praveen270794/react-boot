package io.praveen.reactbootapp.bean;

import java.util.Set;

import io.praveen.reactbootapp.model.Event;
import io.praveen.reactbootapp.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class GroupBean {
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;
    private User user;
    private Set<Event> events;
}

