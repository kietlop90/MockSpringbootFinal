package com.mock.api.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ObjectUtil {

    /**
     * Dozer Mapper (Thread safe)
     */
    private static final Mapper MAPPER = new DozerBeanMapper();

    static {
        List<String> mappingFiles = List.of("dozer-custom-mapping.xml");
        ((DozerBeanMapper) MAPPER).setMappingFiles(mappingFiles);
    }

    private ObjectUtil() {
    }

    /**
     * Get instance of class by class name
     *
     * @param className Class name
     * @return instance
     */
    public static Object getInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return getInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get instance of class by class
     *
     * @param <T>   Optional
     * @param clazz Class
     * @return instance
     */
    public static <T> T getInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copy list object
     *
     * @param <T>   Optional
     * @param froms List from
     * @param clazz Class
     * @return Copied list
     */
    public static <T> List<T> copyProperties(List<?> froms, Class<T> clazz) {
        List<T> records = new ArrayList<T>();

        for (Object form : froms) {
            T t = copyProperties(form, clazz);
            records.add(t);
        }

        return records;
    }

    /**
     * Copy properties of object
     *
     * @param <T>   Optional
     * @param from  From
     * @param clazz Class
     * @return Copied object
     */
    public static <T> T copyProperties(Object from, Class<T> clazz) {
        T t = getInstance(clazz);
        copyProperties(from, t);
        return t;
    }

    /**
     * Constructs new instance of destinationClass and performs mapping between from source
     *
     * @param <T>              Optional
     * @param source           The source object
     * @param destinationClazz The destination class
     * @return Copied object
     */
    public static <T> T copyObject(Object source, Class<T> destinationClazz) {
        return MAPPER.map(source, destinationClazz);
    }

    /**
     * Copy object
     *
     * @param <T>       Optional
     * @param from      From
     * @param className Class name
     * @return Copied object
     */
    @SuppressWarnings("unchecked")
    public static <T> T copyProperties(Object from, String className) {
        T o = (T) getInstance(getClass(className));
        copyProperties(from, o);
        MAPPER.map(from, o);
        return o;
    }

    /**
     * Copy object by mapper
     *
     * @param from From
     * @param to   To
     */
    public static void copyProperties(Object from, Object to) {
        if (from == null) {
            return;
        }
        MAPPER.map(from, to);
    }

    /**
     * Convert object to Map
     *
     * @param bean Object
     * @return Map
     */
    public static Map<String, Object> toMap(Object bean) {
        Map<String, Object> describe;
        String CLASS_KEY = "class";
        try {
            describe = PropertyUtils.describe(bean);
            describe.remove(CLASS_KEY);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return describe;
    }

    /**
     * Get class from class name
     *
     * @param className Class Name
     * @return Instance
     */
    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check class is not a primitive or primitive wrapper ({@link Boolean},
     * {@link Byte}, {@link Character}, {@link Short}, {@link Integer},
     * {@link Long}, {@link Double}, {@link Float}), or {@link String}.
     *
     * @param clazz type for check
     * @return true: is POJO, false: otherwise
     */
    public static boolean isPojoClass(Class<?> clazz) {
        return !ClassUtils.isPrimitiveOrWrapper(clazz) && !ClassUtils.isAssignable(clazz, String.class)
                && !ClassUtils.isAssignable(clazz, Timestamp.class) && !ClassUtils.isAssignable(clazz, List.class)
                && !ClassUtils.isAssignable(clazz, LocalDate.class);
    }

    /**
     * Determines if the specified Object is assignment-compatible with the object
     * represented by specific target classes.
     *
     * @param object       The object to check
     * @param checkTargets The target classes to check
     * @return true if the specific object is compatible with specific target
     * classes
     */
    public static boolean isInstanceOf(Throwable object, List<Class<? extends Throwable>> checkTargets) {
        if (object == null || CollectionUtils.isEmpty(checkTargets)) {
            return false;
        }
        for (Class<?> clazz : checkTargets) {
            if (clazz.isInstance(object)) {
                return true;
            }
        }

        return false;
    }

}
