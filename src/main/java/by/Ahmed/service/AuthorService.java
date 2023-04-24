package by.Ahmed.service;

import by.Ahmed.dao.AuthorDao;
import by.Ahmed.dto.CreateAuthorDto;
import by.Ahmed.exceptions.ValidationException;
import by.Ahmed.mapper.CreateAuthorMapper;
import by.Ahmed.validate.CreateAuthorValidator;
import lombok.NoArgsConstructor;


import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AuthorService {
    private static final AuthorService INSTANCE = new AuthorService();
    private final CreateAuthorMapper createAuthorMapper = CreateAuthorMapper.getInstance();
    private final CreateAuthorValidator createAuthorValidator = CreateAuthorValidator.getInstance();
    private static final AuthorDao authorDao = AuthorDao.getInstance();

    public static AuthorService getInstance() {
        return INSTANCE;
    }

    public Long create(CreateAuthorDto authorDto) {
        var validationResult = createAuthorValidator.isValid(authorDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var authorEntity = createAuthorMapper.mapFrom(authorDto);
        authorDao.create(authorEntity);
        return authorEntity.getId();
    }
}
