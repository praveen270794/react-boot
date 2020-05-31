package io.praveen.reactbootapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.praveen.reactbootapp.bean.GroupBean;
import io.praveen.reactbootapp.model.User;
import io.praveen.reactbootapp.service.GroupService;
import io.praveen.reactbootapp.service.UserService;
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class GroupController {

	private final Logger log = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/groups")
    public Collection<GroupBean> groups(Principal principal) {
    	return groupService.findAllGroups(principal);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupBean> getGroup(@PathVariable Long id) {
        return groupService
        		.findGroup(id)
        		.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/group")
    public ResponseEntity<?> createGroup(@Validated @RequestBody GroupBean groupBean,
    		@AuthenticationPrincipal OAuth2User principal){
        log.info("Request to create group: {}", groupBean);
        Map<String, Object> details = principal.getAttributes();
        String userId = details.get("sub").toString();
        Optional<User> user =userService.findById(userId);
        groupBean.setUser(user.orElse(new User(userId,
                details.get("name").toString(), details.get("email").toString())));
        return groupService
        		.createGroup(groupBean)
        		.map(response -> 
    				{
						try {
							return ResponseEntity
							.created(new URI("/api/group/" + response.getId()))
							.body(response);
						} catch (URISyntaxException e) {
							log.info("Request to create group failed for: {}", groupBean, e);
						}
						return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
					})
        		.orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<?> updateGroup(@Validated @RequestBody GroupBean groupBean) {
        log.info("Request to update group: {}", groupBean);
        return groupService
        		.updateGroup(groupBean)
        		.map(response -> 
        		ResponseEntity.ok().body(response))
        		.orElse(new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete group: {}", id);
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
