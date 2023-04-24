package by.Ahmed.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
