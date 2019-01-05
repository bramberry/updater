package by.vsu.bramberry.updateChecker.model.service;


import by.vsu.bramberry.updateChecker.model.dao.UserDAO;
import by.vsu.bramberry.updateChecker.model.entity.user.Role;
import by.vsu.bramberry.updateChecker.model.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserDetailsService}. Using for get {@link UserDetails} from database.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserDAO userDao;


    @Autowired
    public UserDetailsServiceImpl(UserDAO userDao) {
        this.userDao = userDao;

    }

    /**
     * Load user from database by username
     *
     * @param username can be null
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException if username doesn't exist in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<Role> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        logger.info("current user id : {}", user.getId());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
