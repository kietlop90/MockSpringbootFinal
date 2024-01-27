package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDate} to
 * {@link LocalDateTime}.
 */
public class LocalDateToLocalDateTimeConverter extends DozerConverter<LocalDate, LocalDateTime> {

    public LocalDateToLocalDateTimeConverter() {
        super(LocalDate.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertTo(LocalDate source, LocalDateTime destination) {

        if (source == null) {
            return null;
        }

        return source.atStartOfDay();
    }

    @Override
    public LocalDate convertFrom(LocalDateTime source, LocalDate destination) {
        if (source == null) {
            return null;
        }

        return source.toLocalDate();
    }

}
