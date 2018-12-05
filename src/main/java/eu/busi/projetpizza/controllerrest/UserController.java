package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;

import eu.busi.projetpizza.dataAcces.util.UserConverter;

import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

/**
 * created by  eric.nyandwi on dec,01/12/2018
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserDAO userDAO ;

    public UserController(UserService userService, UserDAO userDAO) {
        this.userService = userService;
        this.userDAO = userDAO;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User created = userService.register(user, RoleEnum.USER);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @GetMapping(value = "/user/whoami",produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> whoAmI() {
        UserEntity user = (UserEntity) userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setPassword(null);
        return new ResponseEntity<User>(UserConverter.userEntityToUserModel(user), HttpStatus.OK);
    }
}
