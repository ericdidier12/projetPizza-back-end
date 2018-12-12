package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.OderDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.dataAcces.util.UserConverter;
import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.Oder;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins="*")
public class InscriptionRestController {

    @Autowired
    UserService userService;

    @Autowired
    private OderDAO orderDAO;

    @Autowired
    private  UserDAO userDAO;

    @PostMapping(value="")
    public void createNewUser(@RequestBody User user){

        userService.register(user, RoleEnum.USER);
        UserEntity userEntity = userDAO.loadUserByUsername(user.getUsername());
        User userEnDb= UserConverter.userEntityToUserModel(userEntity);

        Oder order = new Oder();
        order.setUser(userEnDb);
        order.setIs_paid(false);
        order.setDate_order(LocalDateTime.now());
        orderDAO.save(order);

    }
}
