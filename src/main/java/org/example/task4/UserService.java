package org.example.task4;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional
    public User create(User user) {
        return userDao.create(user);
    }

    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public Optional<User> updateUser(Long id, String newUsername) {
        Optional<User> existingUser = userDao.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(newUsername);
            return Optional.of(userDao.update(user));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userDao.delete(id);
    }

    @Transactional
    public boolean deleteAll() {
        return userDao.deleteAll();
    }
}
