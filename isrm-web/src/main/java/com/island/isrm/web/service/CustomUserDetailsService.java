package com.island.isrm.web.service;

import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.service.UserQueryService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserQueryService userQueryService;

    public CustomUserDetailsService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userQueryService.find(username);
        if (!StringUtils.hasText(userDO.getUserName())) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return User.withUsername(userDO.getUserName()).password(userDO.getPassword()).roles(userDO.getRoles())
                .disabled(userDO.isDisabled()).accountLocked(userDO.isAccountLocked()).build();
    }
}
