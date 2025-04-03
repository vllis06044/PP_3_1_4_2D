package vllis.PP_3_1_1_springboot.services;

import vllis.PP_3_1_1_springboot.entities.User;
import java.util.List;

public interface UserService {
    void create(User user);
    void update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
}