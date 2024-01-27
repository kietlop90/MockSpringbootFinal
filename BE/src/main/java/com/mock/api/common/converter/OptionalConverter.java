package com.mock.api.common.converter;


import com.mock.api.util.ObjectUtil;
import org.dozer.CustomConverter;
import org.dozer.converters.PrimitiveOrWrapperConverter;

import java.util.Optional;

/**
 * <b>Converter Dozer</b> : Convert a {@link Optional} to {@link Object}.
 */
public class OptionalConverter implements CustomConverter {
    private final PrimitiveOrWrapperConverter primitiveOrWrapperConverter = new PrimitiveOrWrapperConverter();
    
    public OptionalConverter() {
    }

    @SuppressWarnings("unchecked")
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return existingDestinationFieldValue;
        }
        if(sourceClass.isAssignableFrom(Optional.class)){
            final Optional<Object> optional = (Optional<Object>) sourceFieldValue;
            
            if (optional.isEmpty()) {
                return null;
            }
            
            final Object optionalValue = optional.get();
            if (primitiveOrWrapperConverter.accepts(destinationClass)) {
                return primitiveOrWrapperConverter.convert(optionalValue, destinationClass, null);
            }
            
            return ObjectUtil.copyObject(optional.get(), destinationClass);
        }
        
        return existingDestinationFieldValue;
    }
}
