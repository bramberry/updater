package by.vsu.bramberry.updatechecker.model.entity.user;


import org.springframework.security.core.GrantedAuthority;

/**
 * Authorities that can be granted to a {@link User}.
 *
 * @author Roman
 */
public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
