package by.Ahmed.service;

import by.Ahmed.dao.ThemeDao;
import by.Ahmed.dto.ThemeDto;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeService {

    private static final ThemeService INSTANCE = new ThemeService();
    private static final ThemeDao themeDao = ThemeDao.getInstance();

    public ThemeService() {

    }

    public static ThemeService getInstance() {
        return INSTANCE;
    }

    public List<ThemeDto> findAll() {
        return themeDao.readAll().stream().map(
                theme -> new ThemeDto(
                        theme.getId(),
                        theme.getName(),
                        new File(theme.getDescription())
                )
        ).collect(Collectors.toList());
    }
}
