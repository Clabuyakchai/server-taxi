package com.clabuyakchai.api.security;

import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private LocalRepository localRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        final Local local = localRepository.findLocalByPhone(phoneNumber);

        if (local == null) {
            throw new UsernameNotFoundException("User with '" + phoneNumber + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(phoneNumber)//
                .password("123")//
                .authorities("user")//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
