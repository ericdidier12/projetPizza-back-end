package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.PizzaEntity;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;

import eu.busi.projetpizza.dataAcces.repository.UserRepository;
import eu.busi.projetpizza.dataAcces.util.PizzaConveter;
import eu.busi.projetpizza.dataAcces.util.UserConverter;

import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.Pizza;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * created by  eric.nyandwi on dec,01/12/2018
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserDAO userDAO ;
    private final PizzaDAO pizzaDAO ;
    private  final UserRepository userRepository;

    public UserController(UserService userService, UserDAO userDAO, PizzaDAO pizzaDAO, UserRepository userRepository) {
        this.userService = userService;
        this.userDAO = userDAO;
        this.pizzaDAO = pizzaDAO;
        this.userRepository = userRepository;
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




    @RequestMapping(value = "/user/addPizzafavorie/{idPizza}", method = RequestMethod.GET)
    public User addPizzaFavorie(@PathVariable(value = "idPizza") Long idPizza  ) {
        Pizza pizza = null;
        boolean exist = false;
        User currentUser = UserConverter.userEntityToUserModel(
                userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        UserEntity user = (UserEntity) userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (pizzaDAO.findPizzaById(idPizza) != null && user.getPizzasFavorites() != null) {
            pizza = pizzaDAO.findPizzaById(idPizza);
            for (int i = 0; i < user.getPizzasFavorites().size() && !exist; i++) {
                exist = (user.getPizzasFavorites().get(i).getId() == pizza.getId());

            }
        }

        if (!exist) {
            user.getPizzasFavorites().add(PizzaConveter.pizzaModelTopizzaEntity(pizza));
            currentUser = UserConverter.userEntityToUserModel(userRepository.save(user));
        }


        return currentUser;
    }



    @RequestMapping(value = "/user/deletePizzafavorie/{idPizza}", method = RequestMethod.GET)
    public User deletePizzaFavorie(@PathVariable(value = "idPizza") Long idPizza) {
        Pizza pizza = null;

        User currentUser = UserConverter.userEntityToUserModel(
                userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        UserEntity userEntity = (UserEntity) userDAO.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (pizzaDAO.findPizzaById(idPizza) != null && userEntity.getPizzasFavorites() != null) {
            pizza = pizzaDAO.findPizzaById(idPizza);

            List<PizzaEntity> toRemove = new ArrayList<>();
            List<PizzaEntity> listPizzaUser = userEntity.getPizzasFavorites() ;

            for(PizzaEntity  pizzaEntity : listPizzaUser){
                if(  pizzaEntity.getId() == pizza.getId()){
                    toRemove.add(pizzaEntity);
                }
            }

            if( userEntity.getPizzasFavorites().removeAll(toRemove)) {
                currentUser = UserConverter.userEntityToUserModel(userRepository.save(userEntity));

            }

        }
        return currentUser;
    }







}
