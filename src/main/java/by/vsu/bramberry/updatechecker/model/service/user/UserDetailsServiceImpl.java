package by.vsu.bramberry.updatechecker.model.service.user;


import by.vsu.bramberry.updatechecker.model.dao.UserDAO;
import by.vsu.bramberry.updatechecker.model.entity.user.Role;
import by.vsu.bramberry.updatechecker.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDao;


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
        log.info("current user id : {}", user.getId());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
