package by.vsu.bramberry.updatechecker.model.service.user;

import by.vsu.bramberry.updatechecker.model.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for validation user data
 *
 * @author Roman
 * @version 1.0
 */
@Service
@Slf4j
public class UserValidationService {

    /**
     * User validation method.
     * Username must be unique and at least 6 symbols long
     * Password must be at least 6 symbols long
     * First and last names must be validated to contain only text symbols and no numbers,
     * punctuation or other special symbols. Do not validate length.
     * Date of birth must be sane. No earlier then ${eldest.date.year}. No later then now
     *
     * @param target not null, if null {@link NullPointerException} will be thrown
     * @return
     */
    public Map<String, String> validate(Object target) {
        Map<String, String> errors = new HashMap<>();
        User user = (User) target;
        if (user.getUsername().length() < 6) {
            errors.put("username", "Username length less then 6");
        }

        if (user.getPassword().length() < 6) {
            errors.put("password", "Password length less then 6");
        }

      Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я]*");
        Matcher matcher = pattern.matcher(user.getFirstName());

        if (!matcher.matches()) {
            errors.put("firstName", "First Name must contain only text symbols");
        }
        matcher = pattern.matcher(user.getLastName());
        if (!matcher.matches()) {
            errors.put("lastName", "Last Name must contain only text symbols");
        }
        return errors;
    }
}
