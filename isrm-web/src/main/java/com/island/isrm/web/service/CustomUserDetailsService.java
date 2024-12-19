package com.island.isrm.web.service;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.service.UserQueryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserQueryService userQueryService;

    public CustomUserDetailsService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDO userDO = userQueryService.find(username);
            return User.withUsername(userDO.getUserCode()).password(userDO.getPassword()).roles(userDO.getRoles()).disabled(userDO.isDisabled()).build();
        } catch (DataNotFoundException e) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
    }
}
