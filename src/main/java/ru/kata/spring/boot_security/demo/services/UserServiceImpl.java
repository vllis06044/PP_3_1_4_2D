package vllis.PP_3_1_1_springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vllis.PP_3_1_1_springboot.dao.UserDao;
import vllis.PP_3_1_1_springboot.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public java.util.List<User> getAll() {
        return userDao.findAll();
    }
}