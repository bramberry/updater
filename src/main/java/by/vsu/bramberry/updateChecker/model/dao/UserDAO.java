package by.vsu.bramberry.updateChecker.model.dao;


import by.vsu.bramberry.updateChecker.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Produce methods to work with datasource
 */
public interface UserDAO extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
