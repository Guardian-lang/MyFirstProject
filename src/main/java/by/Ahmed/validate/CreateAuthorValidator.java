package by.Ahmed.validate;

import by.Ahmed.dto.CreateAuthorDto;
import by.Ahmed.entity.Gender;
import by.Ahmed.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateAuthorValidator implements Validator<CreateAuthorDto> {
    private static final CreateAuthorValidator INSTANCE = new CreateAuthorValidator();

    @Override
    public ValidationResult isValid(CreateAuthorDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthDate())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if (Gender.find(object.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return validationResult;
    }

    public static CreateAuthorValidator getInstance() {
        return INSTANCE;
    }
}
