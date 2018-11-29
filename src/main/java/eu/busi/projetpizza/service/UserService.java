package eu.busi.projetpizza.service;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import eu.busi.projetpizza.dataAcces.entity.Authority;
import eu.busi.projetpizza.dataAcces.repository.AuthorityRepository;
import eu.busi.projetpizza.dataAcces.repository.UserRepository;
import eu.busi.projetpizza.enums.RoleEnum;
import eu.busi.projetpizza.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by  eric.nyandwi on Nov,11/11/2018
 */

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO ;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    public User register(User userInt ,RoleEnum whoAmI) {

        // ajouté de(s) role(e)s à un utilisateur.
        switch (whoAmI.getValue()) {
            case "ROLE_USER":
                userInt.setAuthorities(new ArrayList<>(Collections.singletonList(createOrGetAuthority(RoleEnum.USER.getValue()))));
                break;
            case "ROLE_ADMIN":
                userInt.setAuthorities(new ArrayList<>());
                userInt.getAuthorities().add(createOrGetAuthority(RoleEnum.ADMIN.getValue()));
                break;
        }

        userInt.setAccountNonExpired(true);
        userInt.setAccountNonLocked(true);
        userInt.setCredentialsNonExpired(true);
        userInt.setEnabled(true);
        String passeword =  passwordEncoder.encode(userInt.getPassword());
        userInt.setPassword(passeword);

        return userDAO.saveUser(userInt);
    }

    public Authority createOrGetAuthority(String authority) {

        Authority found = authorityRepository.findByAuthority(authority);

        if (found == null) {
            found = new Authority(authority);
            authorityRepository.save(found);
        }
        return found;
    }



}
