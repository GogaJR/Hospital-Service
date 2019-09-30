package am.initsolutions.services;

import am.initsolutions.models.User;

public interface UserService {
    User getByEmail(String email);
}
