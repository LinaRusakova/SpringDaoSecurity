package ru.geekbrains.DaoSecurity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.DaoSecurity.entities.Authority;
import ru.geekbrains.DaoSecurity.entities.Role;
import ru.geekbrains.DaoSecurity.entities.User;
import ru.geekbrains.DaoSecurity.repositories.RoleRepository;
import ru.geekbrains.DaoSecurity.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository=userRepository;
    }
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles(), user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles, Collection<Authority> authorities) {
        Collection<SimpleGrantedAuthority> resultAuthority= roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        resultAuthority.addAll(authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList()));
        return resultAuthority;
    };

}
