
package com.adminportal.sevices.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.Repository.RoleRepository;
import com.adminportal.Repository.UserRepository;
import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;
import com.adminportal.sevices.UserServices;


@Service

public class UserServiceImpl implements UserServices {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	
	@Override
	@Transactional
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = userRepository.findByUsername(user.getUsername());
        
        if (localUser != null) {
            LOG.info("User {} already exists.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRole().addAll(userRoles);

            localUser = userRepository.save(user);
        }
        return localUser;
    }


	@Override
	public User save(User user){
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
	
	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
