package ru.sberdyshev.geekbrains.java.javaspringsecond.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.Role;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.User;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.repository.RoleRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.repository.UserRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.service.UserService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("loadUserByUsername() - Start with args: userName={}", userName);
        Optional<User> optionalUser = userRepository.findOneByUserName(userName);
        if (!optionalUser.isPresent()) {
            log.warn("loadUserByUsername() - User with userName={} wasn't found", userName);
            throw new UsernameNotFoundException("Invalid userName or password, userName=" + userName);
        }
        User user = optionalUser.get();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
        log.debug("loadUserByUsername() - Return value: UserDetails={}", userDetails);
        return userDetails;
    }
}
