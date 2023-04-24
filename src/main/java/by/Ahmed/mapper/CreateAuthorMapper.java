package by.Ahmed.mapper;

import by.Ahmed.dto.CreateAuthorDto;
import by.Ahmed.entity.Author;
import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import by.Ahmed.utils.LocalDateFormatter;

public class CreateAuthorMapper implements Mapper<CreateAuthorDto, Author> {
    private static final CreateAuthorMapper INSTANCE = new CreateAuthorMapper();
    
    public static CreateAuthorMapper getInstance() {return INSTANCE;}

    @Override
    public Author mapFrom(CreateAuthorDto object) {
        return Author.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .gender(Gender.valueOf(object.getGender()))
                .birthDate(LocalDateFormatter.format(object.getBirthDate()))
                .occupation(object.getOccupation())
                .jobTitle(object.getJobTitle())
                .checkStatus(CheckStatus.valueOf(object.getCheckStatus()))
                .about(object.getAbout())
                .authorizationId(Long.valueOf(object.getAuthorizationId()))
                .build();
    }
}