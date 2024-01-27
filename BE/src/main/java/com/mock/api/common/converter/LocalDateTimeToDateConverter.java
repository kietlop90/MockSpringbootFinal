package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDateTime} to {@link Date}.
 */
public class LocalDateTimeToDateConverter extends DozerConverter<LocalDateTime, Date> {

    public LocalDateTimeToDateConverter() {
        super(LocalDateTime.class, Date.class);
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        if (source == null) {
            return null;
        }

        destination = Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        return destination;
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        if (source == null) {
            return null;
        }

        return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

}
