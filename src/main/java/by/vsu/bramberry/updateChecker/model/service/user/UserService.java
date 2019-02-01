package by.vsu.bramberry.updateChecker.model.service.user;


import by.vsu.bramberry.updateChecker.model.dao.UserDAO;
import by.vsu.bramberry.updateChecker.model.entity.user.Role;
import by.vsu.bramberry.updateChecker.model.entity.user.User;
import by.vsu.bramberry.updateChecker.model.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@link UserService} provides methods to communicate with {@link UserDAO}
 *
 * @author Andrew
 * @version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {


    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final SequenceGeneratorService sequenceGeneratorService;

    /**
     * Searches all user from a storage
     *
     * @return all users or empty list
     */
    public List<User> getUsers() {
        return userDAO.findAll();
    }


    /**
     * Searches for a user with specified
     *
     * @param username can be null
     * @return {@link User} or null
     */
    public User getUser(String username) {
        return userDAO.findByUsername(username);
    }

    /**
     * Searches for a user with specified
     *
     * @param id can be null
     * @return {@link User} or null
     */
    public User getUser(Long id) {
        return userDAO.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Save user at storage
     * Encrypts user password, and set up date of registration
     *
     * @param user not null. If null {@link NullPointerException} will be thrown
     */
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateRegistered(new Date());
        user.setId(sequenceGeneratorService.getNextSequence(User.SEQUENCE_NAME));
        userDAO.save(user);
    }

    /**
     * Delete user from storage
     *
     * @param id can be null
     */
    public void delete(Long id) {
        userDAO.deleteById(id);
    }

    /**
     * Does user have ADMIN {@link Role}
     *
     * @param user can be null.
     * @return true is user have ADMIN {@link Role}, otherwise false
     */
    public boolean isAdmin(User user) {
        User fromDAO = userDAO.findByUsername(user.getUsername());
        if (fromDAO != null) {
            boolean matches = passwordEncoder.matches(user.getPassword(), fromDAO.getPassword());
            log.debug("passwords matches: {}", matches);
            if (matches) {
                return fromDAO.getRole().equals(Role.ADMIN);
            }
        }
        return false;
    }

    /**
     * Searches user at the storage
     *
     * @param username can be null
     * @return true if user exist, otherwise false
     */
    public boolean isExists(String username) {
        return (userDAO.findByUsername(username) != null);
    }

    /**
     * Updates user at the storage
     *
     * @param user can be null. If null nothing will change
     */
    public void update(User user) {
        userDAO.save(user);
    }

    public User getUpdatedUser(User user) {
        User oldUser = userDAO.findByUsername(user.getUsername());

        if (oldUser == null) {
            throw new IllegalStateException("User with your username was not found.");
        }

        if (user.getPassword() != null) {
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }

        return oldUser;
    }
}
