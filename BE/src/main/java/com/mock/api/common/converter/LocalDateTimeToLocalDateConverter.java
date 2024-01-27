package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDateTime} to
 * {@link LocalDate}.
 */
public class LocalDateTimeToLocalDateConverter extends DozerConverter<LocalDateTime, LocalDate> {

    public LocalDateTimeToLocalDateConverter() {
        super(LocalDateTime.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(LocalDateTime source, LocalDate destination) {
        if (source == null) {
            return null;
        }
        
        return source.toLocalDate();
    }

    @Override
    public LocalDateTime convertFrom(LocalDate source, LocalDateTime destination) {
        if (source == null) {
            return null;
        }
        
        return source.atStartOfDay();
    }

}
