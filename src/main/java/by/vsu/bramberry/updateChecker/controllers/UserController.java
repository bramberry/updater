package by.vsu.bramberry.updateChecker.controllers;


import by.vsu.bramberry.updateChecker.model.entity.user.Role;
import by.vsu.bramberry.updateChecker.model.entity.user.User;
import by.vsu.bramberry.updateChecker.model.security.JwtTokenProvider;
import static by.vsu.bramberry.updateChecker.model.security.SecurityConstants.HEADER_STRING;
import static by.vsu.bramberry.updateChecker.model.security.SecurityConstants.TOKEN_PREFIX;
import by.vsu.bramberry.updateChecker.model.service.user.UserService;
import by.vsu.bramberry.updateChecker.model.service.user.UserValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;


/**
 * Handles requests to /users
 *
 * @author Roman
 * @version 1.0
 */
@RestController
@RequestMapping("users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserValidationService validatorService;
    private final UserService userService;

    /**
     * User sign up method. Includes user validation
     *
     * @return new {@link User} with Authentication token, or Map of errors
     */
    @PostMapping
    public ResponseEntity signUp(@RequestBody User user) {
        log.info("{}", user);
        Map<String, String> err = validatorService.validate(user);

        if (userService.isExists(user.getUsername())) {
            err.put("username", "Username already exists");
        }
        if (err.size() > 0) {
            log.warn("Validation failed for user {}", user.getUsername());
            return ResponseEntity.badRequest().body("Validation failed for user {}" + err);
        }
        log.debug("Try to register new User {}", user.getUsername());
        String actualPassword = user.getPassword();
        userService.save(user);

        log.debug("Authenticate User {}", user.getUsername());
        auth(user.getUsername(), actualPassword);


        log.debug("Generate token for User {}", user.getUsername());
        String token = tokenProvider.generateToken(user.getUsername());

        return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + token).body(user);
    }

    /**
     * User edit method. Includes user validation. User could edit only himself
     *
     * @param username Editable username. not null
     * @param user     User form with params that will be changed.
     * @return Success if user has been updated
     * @throws NullPointerException If user is null
     */
    @PutMapping("/{username}")
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity edit(@PathVariable("username") String username, @RequestBody User user) {

        String authUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
      if (!authUsername.equals(username)
          && !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is allowed to only edit himself! Or current user haven't ADMIN permissions");
        }

        user.setUsername(username);

      if (user.getRole().equals(Role.ADMIN)
          && !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin allowed to set ADMIN role!");
      }

        User updatedUser = userService.getUpdatedUser(user);
        Map<String, String> err = validatorService.validate(updatedUser);

        if (err.size() > 0) {
            log.warn("Validation failed for user {}", user.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed for user:\n" + err);
        }

        userService.update(updatedUser);

        if (user.getPassword() != null) {
            log.debug("Authenticate User {}", user.getUsername());
            auth(user.getUsername(), user.getPassword());

            log.debug("Generate token for User {}", user.getUsername());
            String token = tokenProvider.generateToken(user.getUsername());

          return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + token).body(updatedUser);
        }

      return ResponseEntity.ok().body(updatedUser);
    }

    /**
     * User login method. A token is generated in the process.
     *
     * @param username not null
     * @param password not null
     * @return Authentication error or new token in Header
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@PathParam("username") String username, @PathParam("password") String password) {
        auth(username, password);
        log.warn("User {} successfully authenticated", username);

        log.debug("Generate token for User {}", username);
        String token = tokenProvider.generateToken(username);

      return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + token).body(userService.getUser(username));
    }

    /**
     * Check does user exist and return them
     *
     * @param username can be null
     * @return the requested {@link User}
     */
    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/{username}")
    public ResponseEntity getSpecificUser(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        if (user == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok().body(user);
    }

  @PreAuthorize("hasAuthority('ADMIN') and isFullyAuthenticated()")
  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    List<User> users = userService.getUsers();
    if (users == null) {
      ResponseEntity.notFound();
    }
    return ResponseEntity.ok().body(users);
  }

    /**
     * Only user with {@link Role} 'ADMIN' can delete user
     *
     * @param id can be null
     * @return OK if user successfully deleted
     * NOT_FOUND if user not found
     * @throws NullPointerException if username is null
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') and isFullyAuthenticated()")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        String username = user.getUsername();
      if (username.equals(SecurityContextHolder.getContext().getAuthentication().getName())
          || SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
        userService.delete(id);
        }

      return ResponseEntity.ok().body("Delete success!");

    }

    /**
     * Authenticate user using {@link AuthenticationManager}
     *
     * @param username if null return Authentication error
     * @param password if null return Authentication error
     */
    private void auth(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                ));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
