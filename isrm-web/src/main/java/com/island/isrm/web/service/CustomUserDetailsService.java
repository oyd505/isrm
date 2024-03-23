package com.island.isrm.web.service;

import com.island.isrm.core.common.exception.DataNotFoundException;
import com.island.isrm.core.idaccess.domain.dp.UserCode;
import com.island.isrm.core.idaccess.domain.entity.CustomUser;
import com.island.isrm.core.idaccess.domain.repo.CustomUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CustomUser customUser = customUserRepository.find(new UserCode(username));
            if (customUser.getUserType().isExternal()) {
                return User.withUsername(customUser.getUserCode().getValue())
                        .password(customUser.getPassword().getValue())
                        .roles("QUOTE").build();
            } else {
                if (customUser.getUserType().isAdmin()) {
                    return User.withUsername(customUser.getUserCode().getValue())
                            .password(customUser.getPassword().getValue())
                            .roles("INQUIRY", "QUOTE").build();
                }
                return User.withUsername(customUser.getUserCode().getValue())
                        .password(customUser.getPassword().getValue())
//                        .authorities("ROLE_INQUIRY", "INQUIRY:REQUEST:SAVE").build();
                        .roles("INQUIRY").build();
            }
        } catch (DataNotFoundException e) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
    }
}
