package com.mock.api.common.converter;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * <b>Converter Dozer</b> : Convert a {@link LocalDate} to {@link Date}.
 */
public class LocalDateToDateConverter extends DozerConverter<LocalDate, Date> {

    public LocalDateToDateConverter() {
        super(LocalDate.class, Date.class);
    }

    @Override
    public Date convertTo(LocalDate source, Date destination) {
        if (source == null) {
            return null;
        }

        return Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDate convertFrom(Date source, LocalDate destination) {
        if (source == null) {
            return null;
        }

        return LocalDate.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

}
