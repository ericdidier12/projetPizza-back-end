package eu.busi.projetpizza.dataAcces.service;

import eu.busi.projetpizza.dataAcces.dao.OderDAO;
import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.dataAcces.util.UserConverter;
import eu.busi.projetpizza.model.Oder;
import eu.busi.projetpizza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OderDAO orderDAO;
    public void createOrderIfHeNotExist(String userName){

       UserEntity userEntity= userDAO.loadUserByUsername(userName);
       User user= UserConverter.userEntityToUserModel(userEntity);
       if (userEntity!=null){
           Optional<OderEntity> orderEntity= userEntity.getOderEntities().stream().filter(x->x.isIs_paid()==false).findFirst();
         if (!orderEntity.isPresent()){
             Oder order = new Oder();
             order.setUser(user);
             order.setIs_paid(false);
             order.setDate_order(LocalDateTime.now());
             orderDAO.save(order);
         }
       }

    }
}
