package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDate;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDate} to {@link LocalDate}.
 */
public class LocalDateToLocalDateConverter extends DozerConverter<LocalDate, LocalDate> {

    public LocalDateToLocalDateConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        return source;
    }

    @Override
    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        return source;
    }

}
