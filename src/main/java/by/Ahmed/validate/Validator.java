package by.Ahmed.validate;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
