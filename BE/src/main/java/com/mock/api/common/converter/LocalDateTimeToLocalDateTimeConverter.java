package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDateTime;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDateTime} to
 * {@link LocalDateTime}.
 */
public class LocalDateTimeToLocalDateTimeConverter extends DozerConverter<LocalDateTime, LocalDateTime> {

    public LocalDateTimeToLocalDateTimeConverter() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
        return source;
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
        return source;
    }

}
