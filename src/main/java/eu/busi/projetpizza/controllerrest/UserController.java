package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;

import eu.busi.projetpizza.dataAcces.util.UserConverter;

import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final UserDetailsService userDetailsService;
    private final UserDAO userDAO ;

    public UserController(UserService userService, UserDetailsService userDetailsService, UserDAO userDAO) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userDAO = userDAO;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User created = userService.register(user, RoleEnum.USER);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @GetMapping("/user/whoami")
    public User whoAmI(Principal principal) {

        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());
        user.setPassword(null);
        return UserConverter.userEntityToUserModel(user);
    }
}
