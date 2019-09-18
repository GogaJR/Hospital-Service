package am.initsolutions.security;


import am.initsolutions.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.AuthorityUtils;
@Scope("session")
public class SpringUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public SpringUser(User user) {
        super(user.getEmail(), user.getHashPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
