package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDateTime} to
 * {@link LocalTime}.
 */
public class LocalDateTimeToLocalTimeConverter extends DozerConverter<LocalDateTime, LocalTime> {

    public LocalDateTimeToLocalTimeConverter() {
        super(LocalDateTime.class, LocalTime.class);
    }

    @Override
    public LocalTime convertTo(LocalDateTime source, LocalTime destination) {
        if (source == null) {
            return null;
        }
        
        return source.toLocalTime();
    }

    @Override
    public LocalDateTime convertFrom(LocalTime source, LocalDateTime destination) {
        if (source == null) {
            return null;
        }
        
        return source.atDate(LocalDate.now());
    }

}
