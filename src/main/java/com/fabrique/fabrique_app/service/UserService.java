package com.fabrique.fabrique_app.service;

import com.fabrique.fabrique_app.model.entity.AppUser;
import com.fabrique.fabrique_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUser> userByLogin = findByLogin(userName);
        if (!userByLogin.isPresent()) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(userByLogin.get().getLogin())
                .password(userByLogin.get().getPassword())
                .roles(userByLogin.get().getRole().toString())
                .build();
        return user;
    }

    public Optional<AppUser> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public Optional<AppUser> findUserById(long id){
        return userRepository.findById(id);
    }

}
