package io.praveen.reactbootapp.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import io.praveen.reactbootapp.bean.GroupBean;

public interface IGroupService {

	List<GroupBean> findAllGroups(Principal principal);
	Optional<GroupBean> findGroup(Long id);
	Optional<GroupBean> createGroup(GroupBean groupBean);
	Optional<GroupBean> updateGroup(GroupBean groupBean);
	void deleteGroup(Long id);
	
}
