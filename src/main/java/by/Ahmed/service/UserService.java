package by.Ahmed.service;

import by.Ahmed.dao.UserDao;
import by.Ahmed.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private static final UserDao userDao = UserDao.getInstance();

    public UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public List<UserDto> findAll() {
        return userDao.readAll().stream().map(
                user -> new UserDto(
                        user.getId(),
                        user.getIp()
                )
        ).collect(Collectors.toList());
    }
}
