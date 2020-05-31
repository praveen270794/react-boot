package io.praveen.reactbootapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
	
    private String id;
    private String name;
    private String email;
}
