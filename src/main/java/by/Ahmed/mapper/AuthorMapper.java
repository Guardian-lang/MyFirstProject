package by.Ahmed.mapper;

import by.Ahmed.dto.AuthorDto;
import by.Ahmed.entity.Author;

public class AuthorMapper implements Mapper<Author, AuthorDto> {
    private static final AuthorMapper INSTANCE = new AuthorMapper();

    public static AuthorMapper getInstance() {return INSTANCE;}
    @Override
    public AuthorDto mapFrom(Author object) {
        return AuthorDto.builder()
                .id(object.getId())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .gender(object.getGender())
                .birthDate(object.getBirthDate())
                .occupation(object.getOccupation())
                .jobTitle(object.getJobTitle())
                .checkStatus(object.getCheckStatus())
                .about(object.getAbout())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }
}
