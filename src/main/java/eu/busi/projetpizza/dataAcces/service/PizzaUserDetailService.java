package eu.busi.projetpizza.dataAcces.service;

import eu.busi.projetpizza.dataAcces.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * created by  eric.nyandwi on Dec,05/12/2018
 */
@Service
public class PizzaUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(s);
    }
}
