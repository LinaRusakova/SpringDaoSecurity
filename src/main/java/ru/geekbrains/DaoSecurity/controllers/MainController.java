package ru.geekbrains.DaoSecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.DaoSecurity.entities.User;
import ru.geekbrains.DaoSecurity.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers() {
        return "secured part of web service";
    }

    @GetMapping("/admin")
    public String pageForAdminRoles() {
        return "Admin's page of web service";
    }

    @GetMapping("/user")
    public String pageForUserRoles(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();


    }
    @GetMapping("/read")
    public String pageForReadDataAuthority() {
        return "read data service";
    }
    @GetMapping("/manage")
    public String pageForManageDataAuthority() {
        return "manage data service";
    }
}
