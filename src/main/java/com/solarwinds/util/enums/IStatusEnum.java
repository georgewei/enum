package com.solarwinds.util.enums;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.lang.reflect.Field;

final class StatusEnumHelper extends ClassValue<Map<Object,Object>> {
    static final StatusEnumHelper INSTANCE = new StatusEnumHelper();

    @Override 
    protected Map<Object, Object> computeValue(Class<?> aClass) {
        Map<Object,Object> m = new HashMap<>();
        for(Field f: aClass.getDeclaredFields()) {
            if(f.isEnumConstant())
            try {
                Object constant = f.get(null);
                IStatus status = f.getAnnotation(IStatus.class);
                m.put(status.value(), constant);
                m.put(constant, status);
            }
            catch(IllegalAccessException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return Collections.unmodifiableMap(m);
    }
}

public interface IStatusEnum {
    String name();
    Class<? extends Enum<?>> getDeclaringClass(); 
    static <T extends Enum<T>&IStatusEnum> T fromDb(Class<T> aClass, int dbValue) {
        return aClass.cast(StatusEnumHelper.INSTANCE.get(aClass).get(dbValue));
    }
    default int toDb() {
        return ((IStatus)StatusEnumHelper.INSTANCE.get(getDeclaringClass()).get(this)).value();
    }
    default boolean equalsToDb(int dbValue) {
        return toDb() == dbValue;
    }
    default String getFullname() {
        return getDeclaringClass().getSimpleName() + "." + name();
    }
    default String getDesc() {
        return ((IStatus)StatusEnumHelper.INSTANCE.get(getDeclaringClass()).get(this)).desc();
    }
}