package ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.Role;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.User;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.repository.UserRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    //    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;

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

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String userName) {
        log.debug("getUser() - Start with args: userName={}", userName);
        Optional<User> optionalUser = userRepository.findOneByUserName(userName);
        if (!optionalUser.isPresent()) {
            log.warn("getUser() - User with userName={} wasn't found", userName);
            throw new UsernameNotFoundException("No user found, userName=" + userName);
        }
        User user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        log.debug("getUser() - Return value: UserDto={}", userDto);
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(UUID userId) {
        log.debug("getUser() - Start with args: userId={}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.warn("getUser() - User with userId={} wasn't found", userId);
            throw new UsernameNotFoundException("No user found, userId=" + userId);
        }
        User user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        log.debug("getUser() - Return value: UserDto={}", userDto);
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        log.debug("getCurrentUser() - Start");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("getCurrentUser() - Requested by user: userName={}", userName);
        Optional<User> optionalUser = userRepository.findOneByUserName(userName);
        if (!optionalUser.isPresent()) {
            log.warn("getCurrentUser() - User with userName={} wasn't found", userName);
            throw new UsernameNotFoundException("No user found, userName=" + userName);
        }
        User user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        log.debug("getCurrentUser() - Return value: UserDto={}", userDto);
        return userDto;
    }
}
