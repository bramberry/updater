package by.vsu.bramberry.updatechecker.model.dao;


import by.vsu.bramberry.updatechecker.model.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Produce methods to work with datasource
 */
@Repository
public interface UserDAO extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
