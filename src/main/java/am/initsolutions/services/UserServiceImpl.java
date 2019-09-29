package am.initsolutions.services;

import am.initsolutions.models.User;
import am.initsolutions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
