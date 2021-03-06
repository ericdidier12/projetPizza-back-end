package eu.busi.projetpizza.dataAcces.dao;


import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.dataAcces.repository.AuthorityRepository;
import eu.busi.projetpizza.dataAcces.repository.UserRepository;

import eu.busi.projetpizza.dataAcces.util.UserConverter;
import eu.busi.projetpizza.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Primary
@Service
@Transactional
public class UserDAO {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserDAO(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User saveUser(User userInt) {
        UserEntity userEntity = UserConverter.userModelToUserEntity(userInt);
        return UserConverter.userEntityToUserModel( userRepository.save(userEntity));

    }

     public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity found = userRepository.findByUsername(username);
        if (found == null) {
            throw new UsernameNotFoundException("Username not found"); // Exception il est fourni par Spring
        }
        System.out.println("Login OKAY");
        return found;
    }


    public User findByUsername(String name){
        return UserConverter.userEntityToUserModel(userRepository.findByUsername(name));}

        public List<User>  getAllUsers(){
        List<User> users = new ArrayList<>();
            for (UserEntity userEntity : userRepository.findAll()) {
                users.add(UserConverter.userEntityToUserModel(userEntity));
            }
            return users;
        }
}
