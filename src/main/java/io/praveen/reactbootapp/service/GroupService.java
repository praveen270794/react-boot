package io.praveen.reactbootapp.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.praveen.reactbootapp.bean.GroupBean;
import io.praveen.reactbootapp.model.Group;
import io.praveen.reactbootapp.model.GroupRepository;

@Service
public class GroupService implements IGroupService{
	private final Logger log = LoggerFactory.getLogger(GroupService.class);
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public List<GroupBean> findAllGroups(Principal principal) {
		
		return groupRepository.findAllByUserId(principal.getName())
		.stream()
		.map(this::convertToBean)
		.collect(Collectors.toList());
	}

	@Override
	public Optional<GroupBean> findGroup(Long id) {
		log.info("Group for ID {} requested", id);
		return groupRepository
				.findById(id)
				.map(this::convertToBean);
	}

	@Override
	public Optional<GroupBean> createGroup(GroupBean groupBean) {
		return Optional.of(groupRepository.save(this.convertToEntity(groupBean)))
				.map(this::convertToBean);
	}

	@Override
	public Optional<GroupBean> updateGroup(GroupBean groupBean) {
		return Optional.of(groupRepository.save(this.convertToEntity(groupBean)))
				.map(this::convertToBean);
	}

	@Override
	public void deleteGroup(Long id) {
		 groupRepository.deleteById(id);
	}
	
	private GroupBean convertToBean(Group group) {
	    return modelMapper.map(group, GroupBean.class);
	}
	
	private Group convertToEntity(GroupBean bean) {
	    return modelMapper.map(bean, Group.class);
	}

}
