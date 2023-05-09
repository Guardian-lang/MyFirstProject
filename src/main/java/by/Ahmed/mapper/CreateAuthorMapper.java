package by.Ahmed.mapper;

import by.Ahmed.dto.CreateAuthorDto;
import by.Ahmed.entity.Author;
import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import by.Ahmed.utils.LocalDateFormatter;

import java.sql.Date;

public class CreateAuthorMapper implements Mapper<CreateAuthorDto, Author> {
    private static final CreateAuthorMapper INSTANCE = new CreateAuthorMapper();
    
    public static CreateAuthorMapper getInstance() {return INSTANCE;}

    @Override
    public Author mapFrom(CreateAuthorDto object) {
        return Author.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .gender(Gender.valueOf(object.getGender()))
                .birthDate(Date.valueOf(object.getBirthDate()))
                .occupation(object.getOccupation())
                .jobTitle(object.getJobTitle())
                .checkStatus(CheckStatus.valueOf(object.getCheckStatus()))
                .about(object.getAbout())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }
}
