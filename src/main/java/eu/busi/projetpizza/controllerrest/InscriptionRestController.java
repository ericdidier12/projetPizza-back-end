package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.User;
import eu.busi.projetpizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.Date;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins="*")
public class InscriptionRestController {

    @Autowired
    UserService userService;

    @PostMapping(value="")
    public void createNewUser(@RequestBody User user){

        System.out.println(user.getUsername() + user.getBirth_date());
        userService.register(user, RoleEnum.USER);

    }
}
