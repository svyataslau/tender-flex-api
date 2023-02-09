package com.tenderflex.tenderflex.converter;

import java.util.Collection;
import java.util.List;

public interface Converter<T, U> {
    List<U> convertToEntities(Collection<T> t);

    U convertToEntity(T t);

    T convertToDto(U u);
}
