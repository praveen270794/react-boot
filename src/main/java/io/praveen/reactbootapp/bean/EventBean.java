package io.praveen.reactbootapp.bean;

import java.time.Instant;
import java.util.Set;

import io.praveen.reactbootapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventBean {
    private Long id;
    private Instant date;
    private String title;
    private String description;
    private Set<User> attendees;
}